package com.um.app.controller.views.admin;

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
import com.um.app.models.database.Asigmateria;
import com.um.app.models.database.Maestros;
import com.um.app.models.database.Materias;
import com.um.app.models.database.Users;
import com.um.app.models.dto.MateriasAsignadas;
import com.um.app.service.AsigmateriaService;
import com.um.app.service.MaestrosService;
import com.um.app.service.MateriasService;
import com.um.app.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/admin/maestros")
public class MaestrosCrud {
    @Autowired MaestrosService maestrosService;
    @Autowired MateriasService materiasService;
    @Autowired UserService userService;
    @Autowired AsigmateriaService asigmateriaService;

    private static final Logger log = LoggerFactory.getLogger(MaestrosCrud.class);

    @GetMapping
    public String maestros(@AuthenticationPrincipal UserDetailsCustom user, Model model) {
        Flux<Maestros> maestros = maestrosService.findAll();

        model.addAttribute("currentUser", user);
        model.addAttribute("maestros", maestros);
        model.addAttribute("titulo", "Listado de maestros");
        return "admin/maestros";
    }

    @GetMapping("/crear")
    public String maestrosCrear(@AuthenticationPrincipal UserDetailsCustom user, Model model) {
        Maestros maestro = new Maestros("", "", "", "", 0);
        Flux<Users> users = userService.findAll();

        model.addAttribute("currentUser", user);
        model.addAttribute("maestros", maestro);
        model.addAttribute("users", users);
        model.addAttribute("titulo", "Guardar maestro");
        return "admin/maestros-form";
    }

    @GetMapping("/editar/{id}")
    public String maestrosEditar(@AuthenticationPrincipal UserDetailsCustom user, @PathVariable int id, Model model) {
        Mono<Maestros> maestro = maestrosService.findById(id).doOnNext(item -> {
            log.info("Cargando maestro :: " + item.toString());
        }).defaultIfEmpty(new Maestros("", "", "", "", 0));
        Flux<Users> users = userService.findAll();

        model.addAttribute("currentUser", user);
        model.addAttribute("maestros", maestro);
        model.addAttribute("users", users);
        model.addAttribute("titulo", "Actualizar maestro");
        return "admin/maestros";
    }

    @PostMapping("/guardar")
    public Mono<String> maestrosGuardar(Maestros maestro) {
        return maestrosService.save(maestro).doOnNext(item -> {
            log.info("Maestro guardado :: " + item.toString());
        }).thenReturn("redirect:/admin/maestros");
    }

    @GetMapping("/eliminar/{id}")
    public Mono<String> maestroEliminar(@PathVariable int id) {
        return maestrosService.findById(id).flatMap(item -> {
            log.info("Eliminando maestro :: " + item.toString());
            return maestrosService.deleteById(id);
        }).thenReturn("redirect:/admin/maestros");
    }

    @GetMapping("/asignacion/{id}")
    public String asignacion(@PathVariable int id, Model model) {
        Flux<MateriasAsignadas> materias = asigmateriaService.getMateriasMaestro(id);
        model.addAttribute("materias", materias);
        model.addAttribute("id", id);
        model.addAttribute("titulo", "Materias asignadas al maestro");
        return "admin/asignacion";
    }
    
    @GetMapping("/asignacion/crear/{id}")
    public String asignacionCrear(@PathVariable int id, Model model) {
        // el ID obtenido es el valor de la columna id_maestro
        Asigmateria asig = new Asigmateria(id, 0, "", "");
        Flux<Materias> materias = materiasService.findAll();

        model.addAttribute("asig", asig);
        model.addAttribute("materias", materias);
        model.addAttribute("titulo", "Asignar materia");
        return "admin/asignacion-form";
    }

    @GetMapping("/asignacion/editar/{id}")
    public String asignacionEditar(@PathVariable int id, Model model) {
        // el ID obtenido es el valor de la columna id
        Mono<Asigmateria> asig = asigmateriaService.findById(id).doOnNext(item -> {
            log.info("Cargando maestro :: " + item.toString());
        }).defaultIfEmpty(new Asigmateria(0, 0, "", ""));
        Flux<Materias> materias = materiasService.findAll();

        model.addAttribute("asig", asig);
        model.addAttribute("materias", materias);
        model.addAttribute("titulo", "Editar asignacion de materia");
        return "admin/asignacion-form";
    }

    @PostMapping("/asignacion/guardar")
    public Mono<String> asignacionGuardar(Asigmateria asig) {
        return asigmateriaService.save(asig).doOnNext(item -> {
            log.info("Asigmateria guardado :: " + item.toString());
        }).thenReturn("redirect:/admin/maestros/asignacion/" + asig.getId_maestro());
    }
    
    @GetMapping("/asignacion/eliminar/{id}")
    public Mono<String> asignacionEliminar(@PathVariable int id) {
        return asigmateriaService.findById(id).flatMap(item -> {
            log.info("Eliminando maestro :: " + item.toString());
            return asigmateriaService.deleteById(id);
        }).thenReturn("redirect:/admin/maestros");
    }
}
