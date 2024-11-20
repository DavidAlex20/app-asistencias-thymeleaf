package com.um.app.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

import com.um.app.models.UserDetailsCustom;
import com.um.app.models.Users;
import com.um.app.models.UsersMaestro;
import com.um.app.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserDetailsCustomService implements ReactiveUserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByUsername(username)
            .switchIfEmpty(Mono.error(new UsernameNotFoundException("Username not foud: " + username)))
            .map(user -> {
                UserBuilder builder = User.withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole());
                return builder.build();
            });
    }

}
