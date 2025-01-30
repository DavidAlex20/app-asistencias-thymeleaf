package com.um.app.controller.views.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.um.app.models.UserDetailsCustom;
import com.um.app.models.database.Users;
import com.um.app.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuariosCrud {
    @Autowired private UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UsuariosCrud.class);
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    @GetMapping
    public String usuarios(@AuthenticationPrincipal UserDetailsCustom user, Model model) {
        Flux<Users> usuarios = userService.findAll();

        model.addAttribute("currentUser", user);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("titulo", "Listado de usuarios");
        return "admin/usuarios";
    }

    @GetMapping("/crear")
    public String usuariosCrear(@AuthenticationPrincipal UserDetailsCustom user, Model model) {
        Users usuario = new Users("", "", "", true);

        model.addAttribute("currentUser", user);
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Guardar usuario");
        return "admin/usuarios-form";
    }

    @GetMapping("/editar/{id}")
    public String usuariosEditar(@AuthenticationPrincipal UserDetailsCustom user, @PathVariable int id, Model model) {
        Mono<Users> usuario = userService.findById(id);

        model.addAttribute("currentUser", user);
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Actualizar usuario");
        return "admin/usuarios-form";
    }

    /* @PostMapping("/guardar")
    public Mono<String> usuariosGuardar(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userService.save(user)
            .doOnNext(item -> {
                log.info("Usuario registrado :: " + item.toString());
            }).thenReturn("redirect:/admin/usuarios");
    } */

    @GetMapping("/eliminar/{id}")
    public String usuariosEliminar(@PathVariable int id) {
        Mono<Users> usuarioMono = userService.findById(id);
        /* Users usuario = usuarioMono.block();
        usuario.setActivo(false); */

        return "redirect:/admin/usuarios";
    }
}
