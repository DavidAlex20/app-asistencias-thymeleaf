package com.um.app.controller.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.um.app.models.Usuario;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/registrar")
public class RegisterController {
    Usuario usuario = new Usuario("", "", "", false, "USER");
    @GetMapping
    public String getMethodName(Model model) {
        model.addAttribute("titulo", "Register");
        model.addAttribute("usuario", usuario);
        return "register";
    }
    
}
