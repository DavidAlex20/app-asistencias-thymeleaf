package com.um.app.controller.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.um.app.models.Users;
import com.um.app.service.UserService;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
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
            }).thenReturn("redirect:/admin")
        ;
    }
    
}
