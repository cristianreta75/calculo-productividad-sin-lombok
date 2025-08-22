package com.productividad.calculo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hu_productividad_metodo_calculo_employee")
public class MetodoCalculoEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private RhEmpleado empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metodo_calculo_id")
    private MetodoCalculo metodoCalculo;

    @Column(name = "especialidad")
    private String especialidad;

    @Column(name = "horario_especifico")
    private Boolean horarioEspecifico;

    @Column(name = "dia")
    private String dia;

    @Column(name = "hora_desde")
    private Double horaDesde;

    @Column(name = "hora_hasta")
    private Double horaHasta;

    public MetodoCalculoEmployee() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public RhEmpleado getEmpleado() { return empleado; }
    public void setEmpleado(RhEmpleado empleado) { this.empleado = empleado; }

    public MetodoCalculo getMetodoCalculo() { return metodoCalculo; }
    public void setMetodoCalculo(MetodoCalculo metodoCalculo) { this.metodoCalculo = metodoCalculo; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public Boolean getHorarioEspecifico() { return horarioEspecifico; }
    public void setHorarioEspecifico(Boolean horarioEspecifico) { this.horarioEspecifico = horarioEspecifico; }

    public String getDia() { return dia; }
    public void setDia(String dia) { this.dia = dia; }

    public Double getHoraDesde() { return horaDesde; }
    public void setHoraDesde(Double horaDesde) { this.horaDesde = horaDesde; }

    public Double getHoraHasta() { return horaHasta; }
    public void setHoraHasta(Double horaHasta) { this.horaHasta = horaHasta; }

    public boolean isHorarioEspecifico(){
        return Boolean.TRUE.equals(horarioEspecifico);
    }

    public String nombreHorario(){
        if (!isHorarioEspecifico()) return "GENERAL";
        return (dia != null ? dia : "") + " " + (horaDesde != null ? horaDesde : 0) + "-" + (horaHasta != null ? horaHasta : 24);
    }
}
