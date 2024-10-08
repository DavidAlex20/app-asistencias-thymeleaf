package com.um.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.um.app.models.Usuario;
import com.um.app.repository.UsuarioRepository;

import reactor.core.publisher.Mono;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public Mono<Usuario> save(Usuario usuario){
        return usuarioRepository.save(usuario);        
    }
}
