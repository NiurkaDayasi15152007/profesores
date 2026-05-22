package com.niurka.profesores.controller;

import com.niurka.profesores.model.Asignatura;
import com.niurka.profesores.model.Profesor;
import com.niurka.profesores.service.AsignaturaService;
import com.niurka.profesores.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web/asignaturas")
public class AsignaturaWebController {

    @Autowired
    private AsignaturaService asignaturaService;

    @Autowired
    private ProfesorService profesorService;

    @GetMapping
    public String listar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) String tipo,
            Model model) {
        model.addAttribute("asignaturas", asignaturaService.listarTodas(nombre, curso, tipo));
        model.addAttribute("nombre", nombre);
        model.addAttribute("curso", curso);
        model.addAttribute("tipo", tipo);
        return "asignaturas/lista";
    }

    @GetMapping("/nueva")
    public String formularioNuevo(Model model) {
        model.addAttribute("asignatura", new Asignatura());
        model.addAttribute("profesores", profesorService.listarTodos(null, null));
        return "asignaturas/nueva";
    }

    @PostMapping("/nueva")
    public String guardarNueva(@ModelAttribute Asignatura asignatura,
            @RequestParam Long profesorId) {
        profesorService.obtenerPorId(profesorId).ifPresent(asignatura::setProfesor);
        asignaturaService.guardar(asignatura);
        return "redirect:/web/asignaturas";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        return asignaturaService.obtenerPorId(id).map(a -> {
            model.addAttribute("asignatura", a);
            model.addAttribute("profesores", profesorService.listarTodos(null, null));
            return "asignaturas/editar";
        }).orElse("redirect:/web/asignaturas");
    }

    @PostMapping("/editar/{id}")
    public String guardarEdicion(@PathVariable Long id,
            @ModelAttribute Asignatura asignatura,
            @RequestParam Long profesorId) {
        profesorService.obtenerPorId(profesorId).ifPresent(asignatura::setProfesor);
        asignaturaService.actualizar(id, asignatura);
        return "redirect:/web/asignaturas";
    }

    @PostMapping("/borrar/{id}")
    public String borrar(@PathVariable Long id) {
        asignaturaService.eliminar(id);
        return "redirect:/web/asignaturas";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        return asignaturaService.obtenerPorId(id).map(a -> {
            model.addAttribute("asignatura", a);
            return "asignaturas/detalle";
        }).orElse("redirect:/web/asignaturas");
    }
}
