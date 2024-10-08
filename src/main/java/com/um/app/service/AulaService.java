package com.um.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.um.app.models.Aula;
import com.um.app.repository.AulaRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AulaService {
	@Autowired
	AulaRepository aulaRepository;
	
	public Flux<Aula> findAll(){
		return aulaRepository.findAll();
	}
	
	public Mono<Aula> findById(int id) {
		return aulaRepository.findById(id);
	}
	
	public Mono<Aula> save(Aula aula){
		return aulaRepository.save(aula);
	}
	
	public Mono<Aula> update(int id, Aula aula){
		Mono<Aula> au = aulaRepository.findById(id);
		return au.map(Optional::of).defaultIfEmpty(Optional.empty())
		.flatMap(opt -> {
			if(opt.isPresent()) {
				aula.setId(id);
				return aulaRepository.save(aula);
			}
			return Mono.empty();
		});
	}
	
	public Mono<Void> deleteById(int id){
		return aulaRepository.deleteById(id);
	}
}
