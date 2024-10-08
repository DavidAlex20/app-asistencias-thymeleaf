package com.um.app.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.um.app.models.Asigaula;

@Repository
public interface AsigaulaRepository extends R2dbcRepository<Asigaula, Integer>{

}
