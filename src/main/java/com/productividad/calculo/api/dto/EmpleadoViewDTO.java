package com.productividad.calculo.api.dto;

import java.util.List;

public class EmpleadoViewDTO {
    private Integer empleadoId;
    private String empleadoNombre;
    private Double importe;
    private List<DetalleViewDTO> detalles;

    public EmpleadoViewDTO() {}

    public Integer getEmpleadoId() { return empleadoId; }
    public void setEmpleadoId(Integer empleadoId) { this.empleadoId = empleadoId; }
    public String getEmpleadoNombre() { return empleadoNombre; }
    public void setEmpleadoNombre(String empleadoNombre) { this.empleadoNombre = empleadoNombre; }
    public Double getImporte() { return importe; }
    public void setImporte(Double importe) { this.importe = importe; }
    public List<DetalleViewDTO> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleViewDTO> detalles) { this.detalles = detalles; }
}
