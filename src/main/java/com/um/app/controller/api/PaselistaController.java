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

import com.um.app.models.Paselista;
import com.um.app.service.PaselistaService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/paselista")
public class PaselistaController {
	@Autowired
	PaselistaService paselistaService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Flux<Paselista> getItems() {
		return paselistaService.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Paselista> getOne(@PathVariable("id") int id) {
		return paselistaService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Paselista> createItem(@RequestBody Paselista paselista) {
		return paselistaService.save(paselista);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Paselista> updateItem(@PathVariable("id") int id, @RequestBody Paselista paselista) {
		return paselistaService.update(id, paselista);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Void> deleteItem(@PathVariable("id") int id) {
		return paselistaService.deleteById(id);
	}
}
