package com.niurka.profesores.repository;

import com.niurka.profesores.model.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {
    List<Asignatura> findByNombreContainingIgnoreCase(String nombre);
    List<Asignatura> findByCursoContainingIgnoreCase(String curso);
    List<Asignatura> findByTipo(String tipo);
    List<Asignatura> findByProfesorId(Long profesorId);
    @Query("SELECT COALESCE(SUM(a.horasSemanales), 0) FROM Asignatura a WHERE a.profesor.id = :profesorId")
    int calcularHorasTotalesPorProfesor(@Param("profesorId") Long profesorId);
}