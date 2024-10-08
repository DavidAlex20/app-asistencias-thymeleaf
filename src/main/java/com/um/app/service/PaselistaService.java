package com.um.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.um.app.models.Paselista;
import com.um.app.repository.PaselistaRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PaselistaService {
	@Autowired
	PaselistaRepository paselistaRepository;
	
	public Flux<Paselista> findAll(){
		return paselistaRepository.findAll();
	}
	
	public Mono<Paselista> findById(int id) {
		return paselistaRepository.findById(id);
	}
	
	public Mono<Paselista> save(Paselista paselista){
		return paselistaRepository.save(paselista);
	}
	
	public Mono<Paselista> update(int id, Paselista paselista){
		Mono<Paselista> au = paselistaRepository.findById(id);
		return au.map(Optional::of).defaultIfEmpty(Optional.empty())
		.flatMap(opt -> {
			if(opt.isPresent()) {
				paselista.setId(id);
				return paselistaRepository.save(paselista);
			}
			return Mono.empty();
		});
	}
	
	public Mono<Void> deleteById(int id){
		return paselistaRepository.deleteById(id);
	}
}
