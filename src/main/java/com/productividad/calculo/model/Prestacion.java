package com.productividad.calculo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hu_productividad_prestacion")
public class Prestacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "agrupador_prestaciones_id")
    @JsonBackReference
    private AgrupadorPrestaciones agrupadorPrestaciones;

    @Column(name = "es_receta")
    private Boolean esReceta;

	public Prestacion() {
		super();
	}

	public Prestacion(Integer id, String name, String codigo, Boolean active,
			AgrupadorPrestaciones agrupadorPrestaciones, Boolean esReceta) {
		super();
		this.id = id;
		this.name = name;
		this.codigo = codigo;
		this.active = active;
		this.agrupadorPrestaciones = agrupadorPrestaciones;
		this.esReceta = esReceta;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public AgrupadorPrestaciones getAgrupadorPrestaciones() {
		return agrupadorPrestaciones;
	}

	public void setAgrupadorPrestaciones(AgrupadorPrestaciones agrupadorPrestaciones) {
		this.agrupadorPrestaciones = agrupadorPrestaciones;
	}

	public Boolean getEsReceta() {
		return esReceta;
	}

	public void setEsReceta(Boolean esReceta) {
		this.esReceta = esReceta;
	}

}
