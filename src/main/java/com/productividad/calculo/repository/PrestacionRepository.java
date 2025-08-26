package com.productividad.calculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productividad.calculo.model.Prestacion;

public interface PrestacionRepository extends JpaRepository<Prestacion, Integer> {
	
}