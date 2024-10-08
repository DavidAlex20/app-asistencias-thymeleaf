package com.um.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.um.app.models.Asigmateria;
import com.um.app.repository.AsigmateriaRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AsigmateriaService {
	@Autowired
	AsigmateriaRepository asigmateriaRepository;
	
	public Flux<Asigmateria> findAll(){
		return asigmateriaRepository.findAll();
	}
	
	public Mono<Asigmateria> findById(int id) {
		return asigmateriaRepository.findById(id);
	}
	
	public Mono<Asigmateria> save(Asigmateria asigmateria){
		return asigmateriaRepository.save(asigmateria);
	}
	
	public Mono<Asigmateria> update(int id, Asigmateria asigmateria){
		Mono<Asigmateria> au = asigmateriaRepository.findById(id);
		return au.map(Optional::of).defaultIfEmpty(Optional.empty())
		.flatMap(opt -> {
			if(opt.isPresent()) {
				asigmateria.setId(id);
				return asigmateriaRepository.save(asigmateria);
			}
			return Mono.empty();
		});
	}
	
	public Mono<Void> deleteById(int id){
		return asigmateriaRepository.deleteById(id);
	}
}
