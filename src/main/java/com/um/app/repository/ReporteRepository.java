package com.um.app.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.um.app.models.database.Reporte;

import reactor.core.publisher.Flux;

@Repository
public interface ReporteRepository extends R2dbcRepository<Reporte, Integer>{
    @Query("SELECT * FROM reporte WHERE id_maestro = :id;")
    Flux<Reporte> getReportes(@Param("id") int id);
}
