package com.productividad.calculo.model;

import com.productividad.calculo.model.converters.EstadoProductividadConverter;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "hu_productividad_productividad")
public class Productividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "mes")
    private Integer mes;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "importe_total")
    private Integer importeTotal;

    @Convert(converter = EstadoProductividadConverter.class)
    @Column(name = "estado")
    private EstadoProductividad estado = EstadoProductividad.EN_CALCULO;

    @OneToMany(mappedBy = "productividad", fetch = FetchType.LAZY)
    private List<ProductividadEmpleado> empleados;

    public Productividad() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getMes() { return mes; }
    public void setMes(Integer mes) { this.mes = mes; }

    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }

    public Integer getImporteTotal() { return importeTotal; }
    public void setImporteTotal(Integer importeTotal) { this.importeTotal = importeTotal; }

    public EstadoProductividad getEstado() { return estado; }
    public void setEstado(EstadoProductividad estado) { this.estado = estado; }

    public List<ProductividadEmpleado> getEmpleados() { return empleados; }
    public void setEmpleados(List<ProductividadEmpleado> empleados) { this.empleados = empleados; }
}
