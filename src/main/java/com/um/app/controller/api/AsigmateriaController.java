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

import com.um.app.models.Asigmateria;
import com.um.app.service.AsigmateriaService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/asigmateria")
public class AsigmateriaController {
	@Autowired
	AsigmateriaService asigmateriaService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Flux<Asigmateria> getItems() {
		return asigmateriaService.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Asigmateria> getOne(@PathVariable("id") int id) {
		return asigmateriaService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Asigmateria> createItem(@RequestBody Asigmateria asigmateria) {
		return asigmateriaService.save(asigmateria);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Asigmateria> updateItem(@PathVariable("id") int id, @RequestBody Asigmateria asigmateria) {
		return asigmateriaService.update(id, asigmateria);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Void> deleteItem(@PathVariable("id") int id) {
		return asigmateriaService.deleteById(id);
	}
}
