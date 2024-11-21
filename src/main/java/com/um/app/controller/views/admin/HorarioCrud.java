package com.um.app.controller.views.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.um.app.models.Asigaula;
import com.um.app.models.Aula;
import com.um.app.models.Horario;
import com.um.app.models.Materias;
import com.um.app.models.UserDetailsCustom;
import com.um.app.repository.AulaRepository;
import com.um.app.service.AsigaulaService;
import com.um.app.service.AulaService;
import com.um.app.service.MateriasService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/admin/horario")
public class HorarioCrud {
    @Autowired
	AsigaulaService asigaulaService;
	
	@Autowired
	MateriasService materiasService;
	
	@Autowired
	AulaRepository aulaRepository;

	private static final Logger log = LoggerFactory.getLogger(HorarioCrud.class);

    // CRUD HORARIO (ASIGNACION DE AULAS)
	@GetMapping
	public String horario(@AuthenticationPrincipal UserDetailsCustom user, Model model) {
		Flux<Horario> asigaulas = asigaulaService.getHorarios();
		//Flux<Asigaula> asigaulas = asigaulaService.findAll();
		
		model.addAttribute("currentUser", user);
		model.addAttribute("asigaulas", asigaulas);
		model.addAttribute("titulo", "Listado de horarios");
		log.info("Loading: Admin - Horario");
		return "admin/horario";
	}

	@GetMapping("/crear")
	public String horarioCrear(@AuthenticationPrincipal UserDetailsCustom user, Model model) {
		Asigaula asigaula = new Asigaula();
		Flux<Materias> materias = materiasService.findAll();
		Flux<Aula> aulas = aulaRepository.findAll();
		
		model.addAttribute("currentUser", user);
		model.addAttribute("asigaula", asigaula);
		model.addAttribute("materias", materias);
		model.addAttribute("aulas", aulas);
		model.addAttribute("titulo", "Guardar horario");
		return "admin/horario-form";
	}
	
	@GetMapping("/editar/{id}")
	public String horarioEditar(@AuthenticationPrincipal UserDetailsCustom user, @PathVariable int id, Model model) {
		Mono<Asigaula> asigaula = asigaulaService.findById(id).doOnNext(item -> {
			log.info("Cargando aula :: " + item.toString());
		}).defaultIfEmpty(new Asigaula());
		Flux<Materias> materias = materiasService.findAll();
		Flux<Aula> aulas = aulaRepository.findAll();

		model.addAttribute("currentUser", user);
		model.addAttribute("asigaula", asigaula);
		model.addAttribute("materias", materias);
		model.addAttribute("aulas", aulas);
		model.addAttribute("titulo", "Actualizar horario");
		return "admin/horario-form";
	}

	@PostMapping("/guardar")
	public Mono<String> horarioGuardar(Asigaula asigaula) {
		return asigaulaService.save(asigaula).doOnNext(item -> {
			log.info("Aula guardada :: " + item.toString());
		}).thenReturn("redirect:/admin/horario");
	}

	@GetMapping("/eliminar/{id}")
	public Mono<String> horarioEliminar(@PathVariable int id) {
		return asigaulaService.findById(id).flatMap(item -> {
			log.info("Eliminando horario :: " + item.toString());
			return asigaulaService.deleteById(id);
		}).thenReturn("redirect:/admin/horario");
	}
}
