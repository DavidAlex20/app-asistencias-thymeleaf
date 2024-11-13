package com.um.app.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.um.app.models.Asigaula;
import com.um.app.models.Horario;

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
}
