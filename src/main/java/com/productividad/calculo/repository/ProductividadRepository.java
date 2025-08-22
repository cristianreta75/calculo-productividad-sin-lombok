package com.productividad.calculo.repository;


import java.util.Optional;
import com.productividad.calculo.model.Productividad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductividadRepository extends JpaRepository<Productividad, Integer> {
    Optional<Productividad> findByAnioAndMes(Integer anio, Integer mes);
}
