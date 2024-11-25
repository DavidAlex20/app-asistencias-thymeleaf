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

import com.um.app.models.database.Aula;
import com.um.app.service.AulaService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/aula")
public class AulaController {
	@Autowired
	AulaService aulaService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Flux<Aula> getItems() {
		return aulaService.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Aula> getOne(@PathVariable("id") int id) {
		return aulaService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Aula> createItem(@RequestBody Aula aula) {
		return aulaService.save(aula);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Aula> updateItem(@PathVariable("id") int id, @RequestBody Aula aula) {
		return aulaService.update(id, aula);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Void> deleteItem(@PathVariable("id") int id) {
		return aulaService.deleteById(id);
	}
	
	
}
