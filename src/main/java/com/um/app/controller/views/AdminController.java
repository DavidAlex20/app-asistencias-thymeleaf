package com.um.app.controller.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.um.app.models.Asigaula;
import com.um.app.models.Aula;
import com.um.app.models.Materias;
import com.um.app.service.AsigaulaService;
import com.um.app.service.AulaService;
import com.um.app.service.MateriasService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AulaService aulaService;

	@Autowired
	MateriasService materiasService;

	@Autowired
	AsigaulaService asigaulaService;

	private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	
	// CRUD AULAS
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

	// CRUD HORARIO (ASIGNACION DE AULAS)
	@GetMapping("/horario")
	public String horario(Model model) {
		Flux<Asigaula> asigaulas = asigaulaService.findAll();
		

		model.addAttribute("asigaulas", asigaulas);
		model.addAttribute("titulo", "Listado de horarios");
		return "admin/horario";
	}

	@GetMapping("/horario/crear")
	public String horarioCrear(Model model) {
		Aula aula = new Aula();

		model.addAttribute("aula", aula);
		model.addAttribute("titulo", "Guardar aula");
		return "admin/horario-form";
	}
	
	@GetMapping("/horario/editar/{id}")
	public String horarioEditar(@PathVariable int id, Model model) {
		Mono<Aula> aula = aulaService.findById(id).doOnNext(item -> {
			log.info("Cargando aula :: " + item.toString());
		}).defaultIfEmpty(new Aula());

		model.addAttribute("aula", aula);
		model.addAttribute("titulo", "Actualizar aula");
		return "admin/horario-form";
	}

	@PostMapping("/horario/guardar")
	public Mono<String> horarioGuardar(Aula aula) {
		return aulaService.save(aula).doOnNext(item -> {
			log.info("Aula guardada :: " + item.toString());
		}).thenReturn("redirect:/admin/horario");
	}

	@GetMapping("/horario/eliminar/{id}")
	public Mono<String> horarioEliminar(@PathVariable int id) {
		return aulaService.findById(id).flatMap(item -> {
			log.info("Eliminando aula :: " + item.toString());
			return aulaService.deleteById(id);
		}).thenReturn("redirect:/admin/horario");
	}

	// CRUD MATERIAS
	@GetMapping("/materias")
	public String materia(Model model) {
		Flux<Materias> materias = materiasService.findAll();

		model.addAttribute("materias", materias);
		model.addAttribute("titulo", "Listado de materias");
		return "admin/materias";
	}

	@GetMapping("/materias/crear")
	public String materiaCrear(Model model) {
		Materias materia = new Materias("", 0, 0);

		model.addAttribute("materia", materia);
		model.addAttribute("titulo", "Guardar materia");
		return "admin/materias-form";
	}
	
	@GetMapping("/materias/editar/{id}")
	public String materiaEditar(@PathVariable int id, Model model) {
		Mono<Materias> materia = materiasService.findById(id).doOnNext(item -> {
			log.info("Cargando materia :: " + item.toString());
		}).defaultIfEmpty(new Materias("", 0, 0));

		model.addAttribute("materia", materia);
		model.addAttribute("titulo", "Actualizar materia");
		return "admin/materias-form";
	}

	@PostMapping("/materias/guardar")
	public Mono<String> materiaGuardar(Materias materia) {
		return materiasService.save(materia).doOnNext(item -> {
			log.info("Materia guardada :: " + item.toString());
		}).thenReturn("redirect:/admin/materias");
	}

	@GetMapping("/materias/eliminar/{id}")
	public Mono<String> materiaEliminar(@PathVariable int id) {
		return materiasService.findById(id).flatMap(item -> {
			log.info("Eliminando materia :: " + item.toString());
			return materiasService.deleteById(id);
		}).thenReturn("redirect:/admin/materias");
	}
	
	
}
