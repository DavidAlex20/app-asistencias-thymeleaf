package com.um.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.um.app.models.database.Users;
import com.um.app.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    public Flux<Users> findAll() {
        return userRepository.findAll();
    }

    public Mono<Users> register(Users user) {
        return userRepository.save(user);
    }

    public Mono<Users> save(Users user) {
        return userRepository.save(user);
    }

}
