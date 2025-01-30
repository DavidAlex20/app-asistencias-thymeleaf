package com.um.app.controller.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.um.app.models.database.Users;
import com.um.app.service.UserService;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {
    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    @PostMapping("/register")
    public Mono<String> register(@RequestBody Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userService.save(user)
            .doOnNext(item -> {
                log.info("Usuario registrado :: " + item.toString());
            }).thenReturn("redirect:/admin/usuarios")
        ;
    }

    @GetMapping("/login")
    public String login(@Param("error") String error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }
    
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
