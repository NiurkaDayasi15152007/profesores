package com.niurka.profesores.service;

import com.niurka.profesores.model.Profesor;
import com.niurka.profesores.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    public Profesor guardar(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    public List<Profesor> listarTodos(String nombre, String especialidad) {
        if (nombre != null && !nombre.isBlank()) {
            return profesorRepository.findByNombreContainingIgnoreCase(nombre);
        } else if (especialidad != null && !especialidad.isBlank()) {
            return profesorRepository.findByEspecialidadContainingIgnoreCase(especialidad);
        }
        return profesorRepository.findAll();
    }

    public Optional<Profesor> obtenerPorId(Long id) {
        return profesorRepository.findById(id);
    }

    public Profesor actualizar(Long id, Profesor datos) {
        Profesor existente = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        existente.setNombre(datos.getNombre());
        existente.setEspecialidad(datos.getEspecialidad());
        existente.setAnosExperiencia(datos.getAnosExperiencia());
        return profesorRepository.save(existente);
    }

    public void eliminar(Long id) {
        profesorRepository.deleteById(id);
    }
}