package com.um.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.um.app.models.database.Materias;
import com.um.app.repository.MateriasRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MateriasService {
	@Autowired
	MateriasRepository materiasRepository;
	
	public Flux<Materias> findAll(){
		return materiasRepository.findAll();
	}
	
	public Mono<Materias> findById(int id) {
		return materiasRepository.findById(id);
	}
	
	public Mono<Materias> save(Materias materias){
		return materiasRepository.save(materias);
	}
	
	public Mono<Materias> update(int id, Materias materias){
		Mono<Materias> au = materiasRepository.findById(id);
		return au.map(Optional::of).defaultIfEmpty(Optional.empty())
		.flatMap(opt -> {
			if(opt.isPresent()) {
				materias.setId(id);
				return materiasRepository.save(materias);
			}
			return Mono.empty();
		});
	}
	
	public Mono<Void> deleteById(int id){
		return materiasRepository.deleteById(id);
	}
}
