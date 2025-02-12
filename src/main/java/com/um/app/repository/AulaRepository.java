package com.um.app.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.um.app.models.database.Aula;

//import reactor.core.publisher.Flux;

@Repository
public interface AulaRepository extends R2dbcRepository<Aula, Integer>{
	//Flux<Aula> findByDeleted(boolean deleted);
}
