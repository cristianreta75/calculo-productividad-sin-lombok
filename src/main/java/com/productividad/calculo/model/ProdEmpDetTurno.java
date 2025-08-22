package com.productividad.calculo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hu_productividad_prod_empleado_det_turno_alephoo")
public class ProdEmpDetTurno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productividad_emp_detalle_id")
    private ProductividadEmpleadoDetalle detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turno_alephoo_id")
    private TurnoAlephoo turno;

    @Column(name = "incluido")
    private Boolean incluido;

    public ProdEmpDetTurno() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public ProductividadEmpleadoDetalle getDetalle() { return detalle; }
    public void setDetalle(ProductividadEmpleadoDetalle detalle) { this.detalle = detalle; }

    public TurnoAlephoo getTurno() { return turno; }
    public void setTurno(TurnoAlephoo turno) { this.turno = turno; }

    public Boolean getIncluido() { return incluido; }
    public void setIncluido(Boolean incluido) { this.incluido = incluido; }

    public boolean isIncluido(){ return Boolean.TRUE.equals(incluido); }
}
