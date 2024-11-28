package com.um.app.controller.views;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.um.app.models.UserDetailsCustom;
import com.um.app.models.database.Paselista;
import com.um.app.models.database.Reporte;
import com.um.app.models.dto.Calendario;
import com.um.app.models.dto.PaselistaData;
import com.um.app.service.AsigaulaService;
import com.um.app.service.PaselistaService;
import com.um.app.service.ReporteService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Controller
@RequestMapping("/")
public class HomeController {
    
    @Autowired AsigaulaService asigaulaService;
    @Autowired ReporteService reporteService;
    @Autowired PaselistaService paselistaService;

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping
    public Mono<String> index(@AuthenticationPrincipal UserDetailsCustom user, Model model) {
        log.info("HomeController.index | User: " + user.getUsername());
        
        try {
            Flux<Calendario> calendario = asigaulaService.getMateriasHoy(user.getId_maestro());

            calendario.subscribe(
                System.out::println,
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Listado completado!\n")
            );

            model.addAttribute("currentUser", user);
            model.addAttribute("calendario", calendario);
            model.addAttribute("titulo", "Index");
            model.addAttribute("pagina", "index");
            return Mono.just("index");
        } catch (Exception e) {
            return Mono.just("Error: " + e.getMessage());
        }
    }

    @GetMapping("/listar/{id}")
    public Mono<String> guardarPaseLista(@PathVariable int id) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int today = LocalDate.now().getDayOfWeek().getValue();
        LocalTime currentTime = LocalTime.now();

        return asigaulaService.findById(id)
            .filter(asig -> asig.getDia() == today) //asegurarse que el registro es del dia correcto
            .flatMap(asig -> {
                LocalTime asigInicio = LocalTime.parse(asig.getInicio(), DateTimeFormatter.ofPattern("HH:mm"));
                LocalTime asigFin = LocalTime.parse(asig.getFin(), DateTimeFormatter.ofPattern("HH:mm"));

                // verificar que la hora actual está dentro de las horas asignadas
                if(currentTime.isBefore(asigInicio) || currentTime.isAfter(asigFin)) {
                    return Mono.error(new IllegalArgumentException("No se puede iniciar o finalizar el pase de lista tras su tiempo límite!"));
                }

                // verificar que no haya registros otro pase de lista
                return paselistaService.getPaselistaHoy(id, date)
                    .filter(listadoExistente -> {
                        LocalTime existInicio = LocalTime.parse(listadoExistente.getHora_entrada(), DateTimeFormatter.ofPattern("HH:mm"));
                        LocalTime existFin = listadoExistente.getHora_salida() != null ? LocalTime.parse(listadoExistente.getHora_salida(), DateTimeFormatter.ofPattern("HH:mm")) : null;
                        
                        // checar si el pase de lista actual choca con otro
                        return existFin == null || (currentTime.isAfter(existInicio) && currentTime.isBefore(existFin));
                    })
                    .hasElements() // retorna "true" si haya registro(s) que choquen con los tiempos del pase de lista actual
                    .flatMap(sobre -> {
                        // if (sobre) {
                        //     return Mono.error(new IllegalStateException("Ya existe un pase de lista entre estas horas!"));
                        // }

                        // si no hay registro existente completado, proceder con el registro de pase de lista
                        return paselistaService.getPaselistaHoy(id, date)
                            .collectList()
                            .flatMap(asignatura -> {
                                if(asignatura.isEmpty()) { // si no existe registro, crear uno
                                    Paselista pase = new Paselista();
                                    pase.setId_asigmateria(id);
                                    pase.setGeolocal("");
                                    pase.setHora_entrada(currentTime.format(DateTimeFormatter.ofPattern("HH:mm")));
                                    pase.setHora_salida(null);
                                    pase.setFecha(date);
                                    return paselistaService.save(pase);
                                } else { // si existe registro, terminar el pase de lista
                                    Paselista pase = asignatura.get(0);
                                    if (pase.getHora_salida() == null) {
                                        pase.setHora_salida(currentTime.format(DateTimeFormatter.ofPattern("HH:mm")));
                                        return paselistaService.save(pase);
                                    } else {
                                        return Mono.just(pase);
                                    }
                                }
                            });
                    });
            })
            .thenReturn("redirect:/");
    }
    

    @GetMapping("/reportes")
    public Mono<String> reportes(@AuthenticationPrincipal UserDetailsCustom user, Model model) {
        log.info("HomeController.reportes | User: " + user.getUsername());

        try {
            Flux<Reporte> reportes = reporteService.getReportes(user.getId_maestro());
            Flux<PaselistaData> paselista = paselistaService.getPaselista(user.getId_maestro());

            paselista.subscribe(
                System.out::println,
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Listado completado!\n")
            );

            model.addAttribute("currentUser", user);
            model.addAttribute("reportes", reportes);
            model.addAttribute("paselistaData", paselista);
            model.addAttribute("titulo", "Reportes");
            model.addAttribute("pagina", "reporte");
            return Mono.just("reporte");
        } catch (Exception e) {
            return Mono.just("Error: " + e.getMessage());
        }
    }

    @GetMapping("/calendario")
	public Mono<String> calendario(@AuthenticationPrincipal UserDetailsCustom user, Model model) {
        log.info("HomeController.calendario | User: " + user.getUsername());
        Flux<Calendario> calendario = asigaulaService.getCalendario(user.getId_maestro());
        try {
            return calendario
                .collectList()
                .map(tarea -> {
                    Map<String, Map<Integer, String>> organizado = new LinkedHashMap<>();
                    String[] horas = {"13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};
            
                    for (String hora : horas) {
                        Map<Integer, String> diarios = new HashMap<>();
                        for (int i = 1; i <= 5; i++) {
                            diarios.put(i, null);
                        }
                        // System.out.println(hora);
                        organizado.put(hora, diarios);
                    }
    
                    // AGREGANDO TAREA AL CALENDARIO ORGANIZADO
                    for (Calendario tar : tarea) {
                        if (organizado.get(tar.getInicio()) != null) {
                            System.out.println("Adding task: " + tar.getMateria_nombre() + " at " + tar.getInicio() + ", Day " + tar.getDia());
                            organizado.get(tar.getInicio()).put(tar.getDia(), tar.getMateria_nombre());
                        }
                    }
    
                    // // VERIFICANDO AGREGADO DE TAREAS
                    // System.out.println("Organized Calendar:");
                    // organizado.forEach((hour, dailyTasks) -> {
                    //     System.out.println("Hour: " + hour);
                    //     dailyTasks.forEach((day, title) -> {
                    //         System.out.println("  Day " + day + ": " + (title != null ? title : "No Task"));
                    //     });
                    // });
            
                    model.addAttribute("currentUser", user);
                    model.addAttribute("calendario", organizado);
                    model.addAttribute("titulo", "Clases semanales");
                    model.addAttribute("pagina", "calendario");
                    return "calendario";
                });
        } catch (Exception e) {
            return Mono.just("Error: " + e.getMessage());
        }
	}
    
}
