package com.um.app.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.um.app.models.Reporte;
import com.um.app.service.ReporteService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/reporte")
public class ReporteController {
	@Autowired
	ReporteService reporteService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Flux<Reporte> getItems() {
		return reporteService.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Reporte> getOne(@PathVariable("id") int id) {
		return reporteService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Reporte> createItem(@RequestBody Reporte reporte) {
		return reporteService.save(reporte);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Reporte> updateItem(@PathVariable("id") int id, @RequestBody Reporte reporte) {
		return reporteService.update(id, reporte);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Void> deleteItem(@PathVariable("id") int id) {
		return reporteService.deleteById(id);
	}
}