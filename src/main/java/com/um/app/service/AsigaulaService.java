package com.um.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.um.app.models.database.Asigaula;
import com.um.app.models.dto.Calendario;
import com.um.app.models.dto.Horario;
import com.um.app.repository.AsigaulaRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AsigaulaService {
	@Autowired
	AsigaulaRepository asigaulaRepository;
	
	public Flux<Asigaula> findAll(){
		return asigaulaRepository.findAll();
	}
	
	public Mono<Asigaula> findById(int id) {
		return asigaulaRepository.findById(id);
	}
	
	public Mono<Asigaula> save(Asigaula asigaula){
		return asigaulaRepository.save(asigaula);
	}
	
	public Mono<Asigaula> update(int id, Asigaula asigaula){
		Mono<Asigaula> au = asigaulaRepository.findById(id);
		return au.map(Optional::of).defaultIfEmpty(Optional.empty())
		.flatMap(opt -> {
			if(opt.isPresent()) {
				asigaula.setId(id);
				return asigaulaRepository.save(asigaula);
			}
			return Mono.empty();
		});
	}
	
	public Mono<Void> deleteById(int id){
		return asigaulaRepository.deleteById(id);
	}

	public Flux<Horario> getHorarios() {
		return asigaulaRepository.getHorario();
	}

	public Flux<Calendario> getCalendario(int id) {
		return asigaulaRepository.calendarioDto(id);
	}
}
