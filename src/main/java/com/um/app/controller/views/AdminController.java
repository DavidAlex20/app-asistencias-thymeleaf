package com.um.app.controller.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger log = LoggerFactory.getLogger(AdminController.class);

	@GetMapping
	public String index(Model model) {
		model.addAttribute("titulo", "Panel de administrador");
		model.addAttribute("pagina", "admin");
		log.info("Loading: Admin - Index");
		return "admin/index";
	}
	
}
