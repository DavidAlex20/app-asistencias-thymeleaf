package com.um.app.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.um.app.models.database.Materias;

@Repository
public interface MateriasRepository extends R2dbcRepository<Materias, Integer>{

}
