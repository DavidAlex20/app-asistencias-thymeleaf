package com.um.app.controller.views.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.um.app.models.UserDetailsCustom;
import com.um.app.models.database.Asigmateria;
import com.um.app.models.database.Paselista;
import com.um.app.service.AsigmateriaService;
import com.um.app.service.PaselistaService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/admin/paselista")
public class PaselistaCrud {
    @Autowired private PaselistaService paselistaService;
    @Autowired private AsigmateriaService asigmateriaService;
    private static final Logger log = LoggerFactory.getLogger(PaselistaCrud.class);

    @GetMapping
    public String paselista(@AuthenticationPrincipal UserDetailsCustom user, Model model) {
        Flux<Paselista> paselista = paselistaService.findAll();

        model.addAttribute("currentUser", user);
        model.addAttribute("paselista", paselista);
        model.addAttribute("titulo", "Listado de pases de lista diarios");
        return "admin/paselista";
    }

    @GetMapping("/crear")
    public String paselistaCrear(@AuthenticationPrincipal UserDetailsCustom user, Model model) {
        Paselista pase = new Paselista(0, "", "", "", "", false);
        Flux<Asigmateria> asigmaterias = asigmateriaService.findAll();

        model.addAttribute("currentUser", user);
        model.addAttribute("pase", pase);
        model.addAttribute("asigmaterias", asigmaterias);
        model.addAttribute("titulo", "Guardar pases de lista");
        return "admin/paselista-form";
    }

    @GetMapping("/editar/{id}")
    public String paselistaCrear(@AuthenticationPrincipal UserDetailsCustom user, @PathVariable int id, Model model) {
        Mono<Paselista> pase = paselistaService.findById(id);
        Flux<Asigmateria> asigmaterias = asigmateriaService.findAll();

        model.addAttribute("currentUser", user);
        model.addAttribute("pase", pase);
        model.addAttribute("asigmaterias", asigmaterias);
        model.addAttribute("titulo", "Actualizar pases de lista");
        return "admin/paselista-form";
    }

    @PostMapping("/guardar")
    public Mono<String> paselistaGuardar(Paselista pase) {
        return paselistaService.save(pase).doOnNext(item -> {
            log.info("Pase de lista guardado :: " + item.toString());
        }).thenReturn("redirect:/admin/paselista");
    }

    @GetMapping("/eliminar/{id}")
    public Mono<String> paselistaEliminar(@PathVariable int id) {
        return paselistaService.findById(id).flatMap(item -> {
            log.info("Eliminando pase de lista :: " + item.toString());
            return paselistaService.deleteById(id);
        }).thenReturn("redirect:/admin/paselista");
    }
    
    
}
