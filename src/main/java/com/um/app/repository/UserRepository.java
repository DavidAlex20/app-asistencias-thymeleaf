package com.um.app.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.um.app.models.Users;

import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends R2dbcRepository<Users, Integer>{
    Mono<Users> findByUsername(String username);
}
