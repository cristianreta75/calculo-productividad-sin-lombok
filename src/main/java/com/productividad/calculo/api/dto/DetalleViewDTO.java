
package com.productividad.calculo.api.dto;

public class DetalleViewDTO {
    private Integer detalleId;
    private String metodo;
    private String formaCalculo;
    private Integer baseValor;
    private Double valorPunto;
    private Double porcentaje;
    private Double valorMontoFijo;
    private String horario;
    private Integer cantidadPracticas;
    private Double importe;

    public DetalleViewDTO() {}

    public Integer getDetalleId() { return detalleId; }
    public void setDetalleId(Integer detalleId) { this.detalleId = detalleId; }
    public String getMetodo() { return metodo; }
    public void setMetodo(String metodo) { this.metodo = metodo; }
    public String getFormaCalculo() { return formaCalculo; }
    public void setFormaCalculo(String formaCalculo) { this.formaCalculo = formaCalculo; }
    public Integer getBaseValor() { return baseValor; }
    public void setBaseValor(Integer baseValor) { this.baseValor = baseValor; }
    public Double getValorPunto() { return valorPunto; }
    public void setValorPunto(Double valorPunto) { this.valorPunto = valorPunto; }
    public Double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(Double porcentaje) { this.porcentaje = porcentaje; }
    public Double getValorMontoFijo() { return valorMontoFijo; }
    public void setValorMontoFijo(Double valorMontoFijo) { this.valorMontoFijo = valorMontoFijo; }
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
    public Integer getCantidadPracticas() { return cantidadPracticas; }
    public void setCantidadPracticas(Integer cantidadPracticas) { this.cantidadPracticas = cantidadPracticas; }
    public Double getImporte() { return importe; }
    public void setImporte(Double importe) { this.importe = importe; }
}
