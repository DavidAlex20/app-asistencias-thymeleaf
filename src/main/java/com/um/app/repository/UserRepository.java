package com.um.app.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.um.app.models.Users;
import com.um.app.models.UsersMaestro;

import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends R2dbcRepository<Users, Integer>{
    Mono<Users> findByUsername(String username);

    @Query(
        "SELECT us.id, us.username, us.password, us.role, mae.numempleado, mae.nombre, mae.apellido, mae.status "+
	    "FROM users AS us INNER JOIN maestros AS mae "+
	    "ON us.id = mae.id_users "+
	    "WHERE us.username = :username "+
        ";"
    )
    Mono<UsersMaestro> getUserMaestro(@Param("username") String username);

}
