package com.um.app.controller.views;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.um.app.models.UserDetailsCustom;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {

	@GetMapping
	public String index(@AuthenticationPrincipal UserDetailsCustom user, Model model) {
		model.addAttribute("currentUser", user);
		model.addAttribute("titulo", "Panel de administrador");
		model.addAttribute("pagina", "admin");
		return "admin/index";
	}
	
}
