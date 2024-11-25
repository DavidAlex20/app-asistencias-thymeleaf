package com.um.app.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.um.app.models.database.Asigmateria;
import com.um.app.models.dto.MateriasAsignadas;

import reactor.core.publisher.Flux;

@Repository
public interface AsigmateriaRepository extends R2dbcRepository<Asigmateria, Integer>{
    @Query(
        "SELECT asigmateria.id, materias.nombre, asigmateria.fecha_inicio, asigmateria.fecha_fin FROM asigmateria "+
	    "INNER JOIN materias "+
	    "ON asigmateria.id_materia = materias.id "+
	    //"WHERE asigmateria.id_maestro = :idMaestro "+
        ";"
    )
    Flux<MateriasAsignadas> getMateriasMaestro(@Param("idMaestro") int idMaestro);

}
