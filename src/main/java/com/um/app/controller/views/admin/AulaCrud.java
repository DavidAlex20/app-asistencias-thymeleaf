package com.um.app.controller.views.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.um.app.models.Aula;
import com.um.app.service.AulaService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/admin")
public class AulaCrud {

    @Autowired
	AulaService aulaService;

    private static final Logger log = LoggerFactory.getLogger(AulaCrud.class);

	@GetMapping("/aulas")
	public String aula(Model model) {
		Flux<Aula> aulas = aulaService.findAll();

		model.addAttribute("aulas", aulas);
		model.addAttribute("titulo", "Listado de aulas");
		return "admin/aula";
	}

	@GetMapping("/aulas/crear")
	public String aulaCrear(Model model) {
		Aula aula = new Aula();

		model.addAttribute("aula", aula);
		model.addAttribute("titulo", "Guardar aula");
		return "admin/aula-form";
	}
	
	@GetMapping("/aulas/editar/{id}")
	public String aulaEditar(@PathVariable int id, Model model) {
		Mono<Aula> aula = aulaService.findById(id).doOnNext(item -> {
			log.info("Cargando aula :: " + item.toString());
		}).defaultIfEmpty(new Aula());

		model.addAttribute("aula", aula);
		model.addAttribute("titulo", "Actualizar aula");
		return "admin/aula-form";
	}

	@PostMapping("/aulas/guardar")
	public Mono<String> aulaGuardar(Aula aula) {
		return aulaService.save(aula).doOnNext(item -> {
			log.info("Aula guardada :: " + item.toString());
		}).thenReturn("redirect:/admin/aulas");
	}

	@GetMapping("/aulas/eliminar/{id}")
	public Mono<String> aulaEliminar(@PathVariable int id) {
		return aulaService.findById(id).flatMap(item -> {
			log.info("Eliminando aula :: " + item.toString());
			return aulaService.deleteById(id);
		}).thenReturn("redirect:/admin/aulas");
	}
}