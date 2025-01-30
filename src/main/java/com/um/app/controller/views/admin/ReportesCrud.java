package com.um.app.controller.views.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.um.app.models.UserDetailsCustom;
import com.um.app.models.database.Maestros;
import com.um.app.models.database.Reporte;
import com.um.app.models.dto.DetailedReportes;
import com.um.app.service.MaestrosService;
import com.um.app.service.ReporteService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/admin/reportes")
public class ReportesCrud {
    @Autowired private ReporteService reporteService;
    @Autowired private MaestrosService maestrosService;
    private static final Logger log = LoggerFactory.getLogger(ReportesCrud.class);

    @GetMapping
    public String reportes(@AuthenticationPrincipal UserDetailsCustom user, Model model) {
        Flux<DetailedReportes> reportes = reporteService.getAllDetailedReportes();

        model.addAttribute("currentUser", user);
        model.addAttribute("reportes", reportes);
        model.addAttribute("titulo", "Listado de reportes semanales");
        return "admin/reportes";
    }

    @GetMapping("/crear")
    public String reportesCrear(@AuthenticationPrincipal UserDetailsCustom user, Model model) {
        Reporte reporte = new Reporte(0, "", "", 0, 0, 0);
        Flux<Maestros> maestros = maestrosService.findAll();

        model.addAttribute("currentUser", user);
        model.addAttribute("reporte", reporte);
        model.addAttribute("maestros", maestros);
        model.addAttribute("titulo", "Guardar reporte");
        return "admin/reportes-form";
    }
    
    @GetMapping("/editar/{id}")
    public String reportesEditar(@AuthenticationPrincipal UserDetailsCustom user, @PathVariable int id, Model model) {
        Mono<Reporte> reporte = reporteService.findById(id);
        Flux<Maestros> maestros = maestrosService.findAll();

        model.addAttribute("currentUser", user);
        model.addAttribute("reporte", reporte);
        model.addAttribute("maestros", maestros);
        model.addAttribute("titulo", "Actualizar reporte");
        return "admin/reportes-form";
    }

    @PostMapping("/guardar")
    public Mono<String> reportesGuardar(Reporte reporte) {
        return reporteService.save(reporte).doOnNext(item -> {
            log.info("Reporte guardado :: " + item.toString());
        }).thenReturn("redirect:/admin/reportes");
    }

    @GetMapping("/eliminar/{id}")
    public Mono<String> reportesEliminar(@PathVariable int id) {
        return reporteService.findById(id).flatMap(item -> {
            log.info("Eliminando reporte :: " + item.toString());
            return reporteService.deleteById(id);
        }).thenReturn("redirect:/admin/reportes");
    }
    
    
}
