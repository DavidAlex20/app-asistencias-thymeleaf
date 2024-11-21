package com.um.app.controller.views;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.um.app.models.UserDetailsCustom;


@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String index(@AuthenticationPrincipal UserDetailsCustom user, Model model) {
        System.out.println(user.toString());
        model.addAttribute("currentUser", user);
        model.addAttribute("titulo", "Index");
        model.addAttribute("pagina", "index");
        return "index";
    }

    @GetMapping("/calendario")
    public String calendario(@AuthenticationPrincipal UserDetailsCustom user, Model model) {
        model.addAttribute("currentUser", user);

        return "calendario";
    }
    
}
