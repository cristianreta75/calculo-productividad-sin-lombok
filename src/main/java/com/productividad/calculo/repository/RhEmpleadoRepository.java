package com.productividad.calculo.repository;

import com.productividad.calculo.model.RhEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RhEmpleadoRepository extends JpaRepository<RhEmpleado, Integer> {
    @Query("SELECT e.idAlephoo FROM RhEmpleado e WHERE e.id = :id")
    Integer findIdAlephooById(@Param("id") Integer id);
}
