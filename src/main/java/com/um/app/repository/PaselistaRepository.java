package com.um.app.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.um.app.models.database.Paselista;

@Repository
public interface PaselistaRepository extends R2dbcRepository<Paselista, Integer>{

}
