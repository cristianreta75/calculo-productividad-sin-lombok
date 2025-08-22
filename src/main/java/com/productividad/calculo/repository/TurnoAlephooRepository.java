package com.productividad.calculo.repository;

import com.productividad.calculo.model.TurnoAlephoo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TurnoAlephooRepository extends JpaRepository<TurnoAlephoo, Integer> {
    Optional<TurnoAlephoo> findByItemTurnoIdAndPrestacionCodigo(Integer itemTurnoId, String prestacionCodigo);
    List<TurnoAlephoo> findByEmpleadoIdAndFechaBetween(Integer empleadoId, java.time.LocalDate desde, java.time.LocalDate hasta);
}
