package com.niurka.profesores.controller;

import com.niurka.profesores.model.Profesor;
import com.niurka.profesores.service.AsignaturaService;
import com.niurka.profesores.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/profesores")
public class ProfesorWebController {

    @Autowired
    private ProfesorService profesorService;
    @Autowired
    private AsignaturaService asignaturaService;

    @GetMapping
    public String listar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String especialidad,
            Model model) {
        model.addAttribute("profesores", profesorService.listarTodos(nombre, especialidad));
        model.addAttribute("nombre", nombre);
        model.addAttribute("especialidad", especialidad);
        return "profesores/lista";
    }

    @GetMapping("/nueva")
    public String formularioNuevo(Model model) {
        model.addAttribute("profesor", new Profesor());
        return "profesores/nueva";
    }

    @PostMapping("/nueva")
    public String guardarNuevo(@ModelAttribute Profesor profesor) {
        profesorService.guardar(profesor);
        return "redirect:/web/profesores";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        return profesorService.obtenerPorId(id).map(p -> {
            model.addAttribute("profesor", p);
            return "profesores/editar";
        }).orElse("redirect:/web/profesores");
    }

    @PostMapping("/editar/{id}")
    public String guardarEdicion(@PathVariable Long id, @ModelAttribute Profesor profesor) {
        profesorService.actualizar(id, profesor);
        return "redirect:/web/profesores";
    }

    @PostMapping("/borrar/{id}")
    public String borrar(@PathVariable Long id) {
        profesorService.eliminar(id);
        return "redirect:/web/profesores";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        return profesorService.obtenerPorId(id).map(p -> {
            model.addAttribute("profesor", p);
            model.addAttribute("asignaturas", asignaturaService.listarPorProfesor(id));
            model.addAttribute("horasTotales", asignaturaService.calcularHorasTotales(id));
            return "profesores/detalle";
        }).orElse("redirect:/web/profesores");
    }
}