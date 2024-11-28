package com.um.app.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.um.app.models.database.Paselista;
import com.um.app.models.dto.PaselistaData;

import groovyjarjarantlr4.v4.runtime.atn.SemanticContext.AND;
import reactor.core.publisher.Flux;

@Repository
public interface PaselistaRepository extends R2dbcRepository<Paselista, Integer>{
    @Query(
        "SELECT pas.id, pas.geolocal, pas.hora_entrada AS entrada, pas.hora_salida AS salida, pas.fecha, pas.falta, m.nombre "+
        "FROM paselista AS pas "+
        "INNER JOIN asigaula AS aa "+
            "ON pas.id_asigmateria = aa.id "+
        "INNER JOIN materias AS m "+
            "ON aa.id_materias = m.id "+
        "INNER JOIN asigmateria AS am "+
	        "ON am.id_materia = aa.id_materias "+
        "WHERE am.id_maestro = :id "+
        "ORDER BY pas.fecha DESC, pas.hora_entrada DESC;"
    )
    Flux<PaselistaData> paselistaDto(@Param("id") int id);

    @Query(
        "SELECT * "+
        "FROM public.paselista "+
        "WHERE id_asigmateria = :id AND fecha = :date "+
        ";"
    )
    Flux<Paselista> paselistaHoy(@Param("id") int id, @Param("date") String date);
}
