package com.um.app.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.um.app.models.Asigmateria;

@Repository
public interface AsigmateriaRepository extends R2dbcRepository<Asigmateria, Integer>{

}
