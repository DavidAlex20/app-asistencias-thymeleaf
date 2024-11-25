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

import com.um.app.models.database.Maestros;
import com.um.app.service.MaestrosService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/maestros")
public class MaestrosController {
	@Autowired
	MaestrosService maestrosService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Flux<Maestros> getItems() {
		return maestrosService.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Maestros> getOne(@PathVariable("id") int id) {
		return maestrosService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Maestros> createItem(@RequestBody Maestros maestros) {
		return maestrosService.save(maestros);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Maestros> updateItem(@PathVariable("id") int id, @RequestBody Maestros maestros) {
		return maestrosService.update(id, maestros);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Void> deleteItem(@PathVariable("id") int id) {
		return maestrosService.deleteById(id);
	}
}
