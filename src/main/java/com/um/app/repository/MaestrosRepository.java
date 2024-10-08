package com.um.app.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.um.app.models.Maestros;

@Repository
public interface MaestrosRepository extends R2dbcRepository<Maestros, Integer>{

}
