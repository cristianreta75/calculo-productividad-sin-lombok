package com.productividad.calculo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "hu_productividad_metodo_calculo")
public class MetodoCalculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "metodoCalculo", fetch = FetchType.LAZY)
    private List<MetodoCalculoVariable> variables;

    public MetodoCalculo() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public boolean isActive(){ return Boolean.TRUE.equals(active); }

    public List<MetodoCalculoVariable> getVariables() { return variables; }
    public void setVariables(List<MetodoCalculoVariable> variables) { this.variables = variables; }
}
