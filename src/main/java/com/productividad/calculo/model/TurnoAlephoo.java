package com.productividad.calculo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "hu_productividad_turno_alephoo")
public class TurnoAlephoo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "item_turno_id")
    private Integer itemTurnoId;

    @Column(name = "employee_id")
    private Integer empleadoId;

    @Column(name = "prestacion_codigo")
    private String prestacionCodigo;

    @Column(name = "prestacion_nombre")
    private String prestacionNombre;

    @Column(name = "prestacion_cantidad")
    private Integer prestacionCantidad;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "dia")
    private String dia;

    @Column(name = "hora")
    private Double hora;

    @Column(name = "especialidad")
    private String especialidad;

    @Column(name = "paciente_nombre")
    private String pacienteNombre;

    @Column(name = "paciente_dni")
    private String pacienteDni;

    @Column(name = "medico_nombre")
    private String medicoNombre;

    @Column(name = "medico_id")
    private Integer medicoId;

    @Column(name = "importe")
    private Double importe;

    @Column(name = "importe_coseguro")
    private Double importeCoseguro;

    @Column(name = "importe_total")
    private Double importeTotal;

    @Column(name = "factura_nro")
    private Integer facturaNro;

    @Column(name = "computado_en_productividad")
    private Boolean computadoEnProductividad;

    public TurnoAlephoo() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getItemTurnoId() { return itemTurnoId; }
    public void setItemTurnoId(Integer itemTurnoId) { this.itemTurnoId = itemTurnoId; }

    public Integer getEmpleadoId() { return empleadoId; }
    public void setEmpleadoId(Integer empleadoId) { this.empleadoId = empleadoId; }

    public String getPrestacionCodigo() { return prestacionCodigo; }
    public void setPrestacionCodigo(String prestacionCodigo) { this.prestacionCodigo = prestacionCodigo; }

    public String getPrestacionNombre() { return prestacionNombre; }
    public void setPrestacionNombre(String prestacionNombre) { this.prestacionNombre = prestacionNombre; }

    public Integer getPrestacionCantidad() { return prestacionCantidad; }
    public void setPrestacionCantidad(Integer prestacionCantidad) { this.prestacionCantidad = prestacionCantidad; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getDia() { return dia; }
    public void setDia(String dia) { this.dia = dia; }

    public Double getHora() { return hora; }
    public void setHora(Double hora) { this.hora = hora; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getPacienteNombre() { return pacienteNombre; }
    public void setPacienteNombre(String pacienteNombre) { this.pacienteNombre = pacienteNombre; }

    public String getPacienteDni() { return pacienteDni; }
    public void setPacienteDni(String pacienteDni) { this.pacienteDni = pacienteDni; }

    public String getMedicoNombre() { return medicoNombre; }
    public void setMedicoNombre(String medicoNombre) { this.medicoNombre = medicoNombre; }

    public Integer getMedicoId() { return medicoId; }
    public void setMedicoId(Integer medicoId) { this.medicoId = medicoId; }

    public Double getImporte() { return importe; }
    public void setImporte(Double importe) { this.importe = importe; }

    public Double getImporteCoseguro() { return importeCoseguro; }
    public void setImporteCoseguro(Double importeCoseguro) { this.importeCoseguro = importeCoseguro; }

    public Double getImporteTotal() { return importeTotal; }
    public void setImporteTotal(Double importeTotal) { this.importeTotal = importeTotal; }

    public Integer getFacturaNro() { return facturaNro; }
    public void setFacturaNro(Integer facturaNro) { this.facturaNro = facturaNro; }

    public Boolean getComputadoEnProductividad() { return computadoEnProductividad; }
    public void setComputadoEnProductividad(Boolean computadoEnProductividad) { this.computadoEnProductividad = computadoEnProductividad; }
}
