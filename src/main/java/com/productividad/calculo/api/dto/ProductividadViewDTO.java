package com.productividad.calculo.api.dto;

import java.util.List;

public class ProductividadViewDTO {
    private Integer anio;
    private Integer mes;
    private String estado;
    private Integer importeTotal;
    private List<EmpleadoViewDTO> empleados;

    public ProductividadViewDTO() {}

    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }
    public Integer getMes() { return mes; }
    public void setMes(Integer mes) { this.mes = mes; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Integer getImporteTotal() { return importeTotal; }
    public void setImporteTotal(Integer importeTotal) { this.importeTotal = importeTotal; }
    public List<EmpleadoViewDTO> getEmpleados() { return empleados; }
    public void setEmpleados(List<EmpleadoViewDTO> empleados) { this.empleados = empleados; }
}
