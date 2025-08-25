package com.productividad.calculo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "hu_productividad_turno_alephoo")
public class TurnoAlephoo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "employee_id")
    private Integer empleadoId;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "prestacion_cantidad")
    private Integer prestacionCantidad;

    @Column(name = "importe_total")
    private Double importeTotal;

    @Column(name = "prestacion_codigo")
    private String prestacionCodigo;

    @Column(name = "turno_id")
    private Integer turnoId;

    @Column(name = "mes")
    private String mes;

    @Column(name = "hora")
    private Double hora;

    @Column(name = "estado")
    private String estado;

    @Column(name = "paciente_nombre")
    private String pacienteNombre;

    @Column(name = "paciente_dni")
    private String pacienteDni;

    @Column(name = "paciente_fecha_nacimiento")
    private LocalDate pacienteFechaNacimiento;

    @Column(name = "paciente_edad")
    private String pacienteEdad;

    @Column(name = "paciente_numero_hc")
    private Integer pacienteNumeroHc;

    @Column(name = "medico_nombre")
    private String medicoNombre;

    @Column(name = "medico_id")
    private Integer medicoId;

    @Column(name = "prestacion_nombre")
    private String prestacionNombre;

    @Column(name = "importe")
    private Double importe;

    @Column(name = "importe_coseguro")
    private Double importeCoseguro;

    @Column(name = "factura_nro")
    private Integer facturaNro;

    @Column(name = "computado_en_productividad")
    private Boolean computadoEnProductividad;

    @Column(name = "create_uid")
    private Integer createUid;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "write_uid")
    private Integer writeUid;

    @Column(name = "write_date")
    private LocalDateTime writeDate;

    @Column(name = "dia")
    private String dia;

    @Column(name = "especialidad")
    private String especialidad;

    @Column(name = "item_turno_id")
    private Integer itemTurnoId;

    @Column(name = "agregado_manualmente")
    private Boolean agregadoManualmente;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(Integer empleadoId) {
		this.empleadoId = empleadoId;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Integer getPrestacionCantidad() {
		return prestacionCantidad;
	}

	public void setPrestacionCantidad(Integer prestacionCantidad) {
		this.prestacionCantidad = prestacionCantidad;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public String getPrestacionCodigo() {
		return prestacionCodigo;
	}

	public void setPrestacionCodigo(String prestacionCodigo) {
		this.prestacionCodigo = prestacionCodigo;
	}

	public Integer getTurnoId() {
		return turnoId;
	}

	public void setTurnoId(Integer turnoId) {
		this.turnoId = turnoId;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Double getHora() {
		return hora;
	}

	public void setHora(Double hora) {
		this.hora = hora;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPacienteNombre() {
		return pacienteNombre;
	}

	public void setPacienteNombre(String pacienteNombre) {
		this.pacienteNombre = pacienteNombre;
	}

	public String getPacienteDni() {
		return pacienteDni;
	}

	public void setPacienteDni(String pacienteDni) {
		this.pacienteDni = pacienteDni;
	}

	public LocalDate getPacienteFechaNacimiento() {
		return pacienteFechaNacimiento;
	}

	public void setPacienteFechaNacimiento(LocalDate pacienteFechaNacimiento) {
		this.pacienteFechaNacimiento = pacienteFechaNacimiento;
	}

	public String getPacienteEdad() {
		return pacienteEdad;
	}

	public void setPacienteEdad(String pacienteEdad) {
		this.pacienteEdad = pacienteEdad;
	}

	public Integer getPacienteNumeroHc() {
		return pacienteNumeroHc;
	}

	public void setPacienteNumeroHc(Integer pacienteNumeroHc) {
		this.pacienteNumeroHc = pacienteNumeroHc;
	}

	public String getMedicoNombre() {
		return medicoNombre;
	}

	public void setMedicoNombre(String medicoNombre) {
		this.medicoNombre = medicoNombre;
	}

	public Integer getMedicoId() {
		return medicoId;
	}

	public void setMedicoId(Integer medicoId) {
		this.medicoId = medicoId;
	}

	public String getPrestacionNombre() {
		return prestacionNombre;
	}

	public void setPrestacionNombre(String prestacionNombre) {
		this.prestacionNombre = prestacionNombre;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Double getImporteCoseguro() {
		return importeCoseguro;
	}

	public void setImporteCoseguro(Double importeCoseguro) {
		this.importeCoseguro = importeCoseguro;
	}

	public Integer getFacturaNro() {
		return facturaNro;
	}

	public void setFacturaNro(Integer facturaNro) {
		this.facturaNro = facturaNro;
	}

	public Boolean getComputadoEnProductividad() {
		return computadoEnProductividad;
	}

	public void setComputadoEnProductividad(Boolean computadoEnProductividad) {
		this.computadoEnProductividad = computadoEnProductividad;
	}

	public Integer getCreateUid() {
		return createUid;
	}

	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public Integer getWriteUid() {
		return writeUid;
	}

	public void setWriteUid(Integer writeUid) {
		this.writeUid = writeUid;
	}

	public LocalDateTime getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(LocalDateTime writeDate) {
		this.writeDate = writeDate;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public Integer getItemTurnoId() {
		return itemTurnoId;
	}

	public void setItemTurnoId(Integer itemTurnoId) {
		this.itemTurnoId = itemTurnoId;
	}

	public Boolean getAgregadoManualmente() {
		return agregadoManualmente;
	}

	public void setAgregadoManualmente(Boolean agregadoManualmente) {
		this.agregadoManualmente = agregadoManualmente;
	}

}
