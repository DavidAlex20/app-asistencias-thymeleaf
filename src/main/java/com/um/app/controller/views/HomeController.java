package com.um.app.controller.views;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.um.app.models.UserDetailsCustom;
import com.um.app.models.database.Paselista;
import com.um.app.models.database.Reporte;
import com.um.app.models.dto.Calendario;
import com.um.app.service.AsigaulaService;
import com.um.app.service.PaselistaService;
import com.um.app.service.ReporteService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Controller
@RequestMapping("/")
public class HomeController {
    
    @Autowired AsigaulaService asigaulaService;
    @Autowired PaselistaService paselistaService;
    @Autowired ReporteService reporteService;

    @GetMapping
    public String index(@AuthenticationPrincipal UserDetailsCustom user, Model model) {
        //System.out.println(user.toString());
        model.addAttribute("currentUser", user);
        model.addAttribute("titulo", "Index");
        model.addAttribute("pagina", "index");
        return "index";
    }

    @GetMapping("/reportes")
    public String reportes(@AuthenticationPrincipal UserDetailsCustom user, Model model) {
        Flux<Reporte> reportes = reporteService.findAll();
        Flux<Paselista> paselista = paselistaService.findAll();

        model.addAttribute("currentUser", user);
        model.addAttribute("paselista", paselista);
        model.addAttribute("reportes", reportes);
        model.addAttribute("titulo", "Reportes");
        model.addAttribute("pagina", "reporte");
        return "reporte";
    }

    @GetMapping("/calendario")
	public Mono<String> calendario(@AuthenticationPrincipal UserDetailsCustom user, Model model) {
        Flux<Calendario> calendario = asigaulaService.getCalendario(user.getId_maestro());

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
	}
    
}
