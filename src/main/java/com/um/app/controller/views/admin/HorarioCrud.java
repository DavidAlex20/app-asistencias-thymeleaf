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

import com.um.app.models.Asigaula;
import com.um.app.models.Aula;
import com.um.app.service.AsigaulaService;
import com.um.app.service.AulaService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/admin")
public class HorarioCrud {
    @Autowired
	AsigaulaService asigaulaService;

    @Autowired
	AulaService aulaService;

	private static final Logger log = LoggerFactory.getLogger(HorarioCrud.class);

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
}
