package com.productividad.calculo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "hu_productividad_agrupador_prestaciones")
public class AgrupadorPrestaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "agrupadorPrestaciones")
    @JsonManagedReference
    private List<Prestacion> prestaciones;

	public AgrupadorPrestaciones() {
		super();
	}

	public AgrupadorPrestaciones(Integer id, String name, Boolean active, List<Prestacion> prestaciones) {
		super();
		this.id = id;
		this.name = name;
		this.active = active;
		this.prestaciones = prestaciones;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Prestacion> getPrestaciones() {
		return prestaciones;
	}

	public void setPrestaciones(List<Prestacion> prestaciones) {
		this.prestaciones = prestaciones;
	}

}
