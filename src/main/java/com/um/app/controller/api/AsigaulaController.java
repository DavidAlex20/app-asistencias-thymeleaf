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

import com.um.app.models.database.Asigaula;
import com.um.app.service.AsigaulaService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/horario")
public class AsigaulaController {
	@Autowired
	AsigaulaService asigaulaService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Flux<Asigaula> getItems() {
		return asigaulaService.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Asigaula> getOne(@PathVariable("id") int id) {
		return asigaulaService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Asigaula> createItem(@RequestBody Asigaula asigaula) {
		return asigaulaService.save(asigaula);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Asigaula> updateItem(@PathVariable("id") int id, @RequestBody Asigaula asigaula) {
		return asigaulaService.update(id, asigaula);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Void> deleteItem(@PathVariable("id") int id) {
		return asigaulaService.deleteById(id);
	}
}
