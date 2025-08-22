package com.productividad.calculo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "hu_productividad_productividad_empleado")
public class ProductividadEmpleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productividad_id")
    private Productividad productividad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private RhEmpleado empleado;

    @Column(name = "importe")
    private Double importe;

    @OneToMany(mappedBy = "productividadEmpleado", fetch = FetchType.LAZY)
    private List<ProductividadEmpleadoDetalle> detalles;

    public ProductividadEmpleado() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Productividad getProductividad() { return productividad; }
    public void setProductividad(Productividad productividad) { this.productividad = productividad; }

    public RhEmpleado getEmpleado() { return empleado; }
    public void setEmpleado(RhEmpleado empleado) { this.empleado = empleado; }

    public Double getImporte() { return importe; }
    public void setImporte(Double importe) { this.importe = importe; }

    public List<ProductividadEmpleadoDetalle> getDetalles() { return detalles; }
    public void setDetalles(List<ProductividadEmpleadoDetalle> detalles) { this.detalles = detalles; }
}
