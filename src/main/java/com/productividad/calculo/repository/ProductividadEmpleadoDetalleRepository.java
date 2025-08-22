package com.productividad.calculo.repository;

import com.productividad.calculo.model.ProductividadEmpleadoDetalle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductividadEmpleadoDetalleRepository extends JpaRepository<ProductividadEmpleadoDetalle, Integer> {
	List<ProductividadEmpleadoDetalle> findByProductividadEmpleadoId(Integer prodEmpId);
}
