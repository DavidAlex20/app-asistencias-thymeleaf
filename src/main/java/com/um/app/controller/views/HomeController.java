package com.um.app.controller.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("titulo", "Index");
        model.addAttribute("pagina", "index");
        return "index";
    }
    
}
