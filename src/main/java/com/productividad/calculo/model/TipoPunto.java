package com.productividad.calculo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hu_productividad_tipo_punto")
public class TipoPunto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "active")
    private Boolean active;

    public TipoPunto() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
