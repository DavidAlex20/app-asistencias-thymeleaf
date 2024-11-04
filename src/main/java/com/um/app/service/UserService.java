package com.um.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.um.app.models.Users;
import com.um.app.repository.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    public Mono<Users> register(Users user) {
        return userRepository.save(user);
    }

}
