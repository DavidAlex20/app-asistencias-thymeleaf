package com.um.app.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.um.app.models.database.Asigaula;
import com.um.app.models.dto.Calendario;
import com.um.app.models.dto.Horario;

import reactor.core.publisher.Flux;

@Repository
public interface AsigaulaRepository extends R2dbcRepository<Asigaula, Integer>{
    @Query(
        "SELECT asigaula.id, materias.nombre, aula.numaula, asigaula.dia, asigaula.inicio, asigaula.fin "+
        "FROM asigaula "+
        "INNER JOIN materias ON asigaula.id_materias = materias.id "+
        "INNER JOIN aula ON asigaula.id_aula = aula.id;"
    )
    Flux<Horario> getHorario();

    @Query(
        "SELECT "+
            "id_materias AS materia_id, "+
            "id_aula AS aula_id, "+
            "dia, inicio, fin, numaula, alias, "+
            "nombre AS materia_nombre "+
        "FROM asigaula "+
            "INNER JOIN aula ON asigaula.id_aula = aula.id "+
            "INNER JOIN asigmateria ON asigaula.id_materias = asigmateria.id_materia "+
            "INNER JOIN materias ON asigmateria.id_materia = materias.id "+
        "WHERE asigmateria.id_maestro = :id "+
        ";"
    )
    Flux<Calendario> calendarioDto(@Param("id") int id);
}
