package com.niurka.profesores.repository;

import com.niurka.profesores.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    List<Profesor> findByNombreContainingIgnoreCase(String nombre);
    List<Profesor> findByEspecialidadContainingIgnoreCase(String especialidad);
}