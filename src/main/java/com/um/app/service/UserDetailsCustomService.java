package com.um.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.um.app.models.UserDetailsCustom;
import com.um.app.models.Users;
import com.um.app.repository.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class UserDetailsCustomService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Mono<Users> userMono = userRepository.findByUsername(username);
        Users user = userMono.block();
        if(user == null){
            throw new UsernameNotFoundException("Username not found: " + username);
        }
        user.toString();
        return new UserDetailsCustom(user);
    }
}
