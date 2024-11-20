package com.um.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.um.app.models.UserDetailsCustom;
import com.um.app.repository.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class UserDetailsCustomService implements ReactiveUserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.getUserMaestro(username)
            .switchIfEmpty(Mono.error(new UsernameNotFoundException("Username not foud: " + username)))
            .map(user -> new UserDetailsCustom(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                user.getNumempleado(),
                user.getNombre(),
                user.getApellido(),
                user.getStatus()
            ));
    }

}
