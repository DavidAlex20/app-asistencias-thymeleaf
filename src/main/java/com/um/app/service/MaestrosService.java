package com.um.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.um.app.models.database.Maestros;
import com.um.app.repository.MaestrosRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MaestrosService {
	@Autowired
	MaestrosRepository maestrosRepository;
	
	public Flux<Maestros> findAll(){
		return maestrosRepository.findAll();
	}
	
	public Mono<Maestros> findById(int id) {
		return maestrosRepository.findById(id);
	}
	
	public Mono<Maestros> save(Maestros maestros){
		return maestrosRepository.save(maestros);
	}
	
	public Mono<Maestros> update(int id, Maestros maestros){
		Mono<Maestros> au = maestrosRepository.findById(id);
		return au.map(Optional::of).defaultIfEmpty(Optional.empty())
		.flatMap(opt -> {
			if(opt.isPresent()) {
				maestros.setId(id);
				return maestrosRepository.save(maestros);
			}
			return Mono.empty();
		});
	}
	
	public Mono<Void> deleteById(int id){
		return maestrosRepository.deleteById(id);
	}
}
