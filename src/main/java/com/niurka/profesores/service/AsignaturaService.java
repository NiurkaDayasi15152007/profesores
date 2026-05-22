package com.niurka.profesores.service;

import com.niurka.profesores.model.Asignatura;
import com.niurka.profesores.model.Profesor;
import com.niurka.profesores.repository.AsignaturaRepository;
import com.niurka.profesores.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    public Asignatura guardar(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    public List<Asignatura> listarTodas(String nombre, String curso, String tipo) {
        if (nombre != null && !nombre.isBlank()) {
            return asignaturaRepository.findByNombreContainingIgnoreCase(nombre);
        } else if (curso != null && !curso.isBlank()) {
            return asignaturaRepository.findByCursoContainingIgnoreCase(curso);
        } else if (tipo != null && !tipo.isBlank()) {
            return asignaturaRepository.findByTipo(tipo);
        }
        return asignaturaRepository.findAll();
    }

    public Optional<Asignatura> obtenerPorId(Long id) {
        return asignaturaRepository.findById(id);
    }

    public Asignatura actualizar(Long id, Asignatura datos) {
        Asignatura existente = asignaturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
        existente.setNombre(datos.getNombre());
        existente.setCurso(datos.getCurso());
        existente.setHorasSemanales(datos.getHorasSemanales());
        existente.setTipo(datos.getTipo());
        return asignaturaRepository.save(existente);
    }

    public void eliminar(Long id) {
        asignaturaRepository.deleteById(id);
    }

    public int calcularHorasTotales(Long profesorId) {
        return asignaturaRepository.calcularHorasTotalesPorProfesor(profesorId);
    }

    public Asignatura reasignarProfesor(Long asignaturaId, Long nuevoProfesorId) {
        Asignatura asignatura = asignaturaRepository.findById(asignaturaId)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
        Profesor nuevoProfesor = profesorRepository.findById(nuevoProfesorId)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        asignatura.setProfesor(nuevoProfesor);
        return asignaturaRepository.save(asignatura);
    }

    public List<Asignatura> listarPorProfesor(Long profesorId) {
        return asignaturaRepository.findByProfesorId(profesorId);
    }
}