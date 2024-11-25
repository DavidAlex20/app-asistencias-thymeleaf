package com.um.app.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.um.app.models.database.Reporte;

@Repository
public interface ReporteRepository extends R2dbcRepository<Reporte, Integer>{

}
