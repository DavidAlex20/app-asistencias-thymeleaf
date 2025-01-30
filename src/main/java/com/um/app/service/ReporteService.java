package com.um.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.um.app.models.database.Reporte;
import com.um.app.models.dto.DetailedReportes;
import com.um.app.repository.ReporteRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReporteService {
	@Autowired
	ReporteRepository reporteRepository;
	
	public Flux<Reporte> findAll(){
		return reporteRepository.findAll();
	}
	
	public Mono<Reporte> findById(int id) {
		return reporteRepository.findById(id);
	}
	
	public Mono<Reporte> save(Reporte reporte){
		return reporteRepository.save(reporte);
	}
	
	public Mono<Reporte> update(int id, Reporte reporte){
		Mono<Reporte> au = reporteRepository.findById(id);
		return au.map(Optional::of).defaultIfEmpty(Optional.empty())
		.flatMap(opt -> {
			if(opt.isPresent()) {
				reporte.setId(id);
				return reporteRepository.save(reporte);
			}
			return Mono.empty();
		});
	}
	
	public Mono<Void> deleteById(int id){
		return reporteRepository.deleteById(id);
	}

	public Flux<Reporte> getReportes(int id) {
		return reporteRepository.getReportes(id);
	}

	public Flux<DetailedReportes> getAllDetailedReportes() {
		return reporteRepository.getAllDetailedReportes();
	}
}
