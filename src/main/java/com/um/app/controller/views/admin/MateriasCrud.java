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

import com.um.app.models.Materias;
import com.um.app.service.MateriasService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/admin")
public class MateriasCrud {

    @Autowired
	MateriasService materiasService;

    private static final Logger log = LoggerFactory.getLogger(MateriasCrud.class);
    
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
