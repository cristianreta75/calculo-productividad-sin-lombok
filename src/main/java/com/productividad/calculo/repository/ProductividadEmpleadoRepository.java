package com.productividad.calculo.repository;

import com.productividad.calculo.model.ProductividadEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductividadEmpleadoRepository extends JpaRepository<ProductividadEmpleado, Integer> {
	List<ProductividadEmpleado> findByProductividadId(Integer productividadId);
    Optional<ProductividadEmpleado> findByProductividadIdAndEmpleadoId(Integer productividadId, Integer empleadoId);
}
