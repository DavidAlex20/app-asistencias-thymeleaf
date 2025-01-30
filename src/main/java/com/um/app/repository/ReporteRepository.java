package com.um.app.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.um.app.models.database.Reporte;
import com.um.app.models.dto.DetailedReportes;

import reactor.core.publisher.Flux;

@Repository
public interface ReporteRepository extends R2dbcRepository<Reporte, Integer>{
    @Query("SELECT * FROM reporte WHERE id_maestro = :id;")
    Flux<Reporte> getReportes(@Param("id") int id);

    @Query(
        "SELECT rep.id, mae.numempleado, mae.nombre, mae.apellido, rep.semana_inicio AS iniciosemana, rep.semana_fin AS finsemana, "+
        "rep.horas_puntuales AS horaspuntuales, rep.horas_impuntuales AS horasimpuntuales, rep.inasistencias "+
        "FROM reporte AS rep "+
	    "INNER JOIN maestros AS mae ON mae.id = rep.id_maestro;")
    Flux<DetailedReportes> getAllDetailedReportes();
}
