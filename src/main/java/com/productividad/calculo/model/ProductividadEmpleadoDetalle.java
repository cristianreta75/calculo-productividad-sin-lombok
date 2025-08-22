package com.productividad.calculo.model;

import com.productividad.calculo.model.converters.FormaCalculoConverter;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "hu_productividad_productividad_empleado_detalle")
public class ProductividadEmpleadoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productividad_empleado_id")
    private ProductividadEmpleado productividadEmpleado;

    @Column(name = "importe")
    private Double importe;

    @Column(name = "cantidad_practicas_realizadas")
    private Integer cantidadPracticasRealizadas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metodo_calculo_id")
    private MetodoCalculo metodoCalculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metodo_calculo_variable_id")
    private MetodoCalculoVariable metodoCalculoVariable;

    @Column(name = "horario")
    private String horario;

    @Convert(converter = FormaCalculoConverter.class)
    @Column(name = "forma_calculo")
    private FormaCalculo formaCalculo;

    @Column(name = "base")
    private Integer baseValor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_punto_id")
    private TipoPunto tipoPunto;

    @Column(name = "valor_punto")
    private Double valorPunto;

    @Column(name = "porcentaje")
    private Double porcentaje;

    @Column(name = "valor_monto_fijo")
    private Double valorMontoFijo;

    @OneToMany(mappedBy = "detalle", fetch = FetchType.LAZY)
    private List<ProdEmpDetTurno> turnos;

    public ProductividadEmpleadoDetalle() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public ProductividadEmpleado getProductividadEmpleado() { return productividadEmpleado; }
    public void setProductividadEmpleado(ProductividadEmpleado productividadEmpleado) { this.productividadEmpleado = productividadEmpleado; }

    public Double getImporte() { return importe; }
    public void setImporte(Double importe) { this.importe = importe; }

    public Integer getCantidadPracticasRealizadas() { return cantidadPracticasRealizadas; }
    public void setCantidadPracticasRealizadas(Integer cantidadPracticasRealizadas) { this.cantidadPracticasRealizadas = cantidadPracticasRealizadas; }

    public MetodoCalculo getMetodoCalculo() { return metodoCalculo; }
    public void setMetodoCalculo(MetodoCalculo metodoCalculo) { this.metodoCalculo = metodoCalculo; }

    public MetodoCalculoVariable getMetodoCalculoVariable() { return metodoCalculoVariable; }
    public void setMetodoCalculoVariable(MetodoCalculoVariable metodoCalculoVariable) { this.metodoCalculoVariable = metodoCalculoVariable; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public FormaCalculo getFormaCalculo() { return formaCalculo; }
    public void setFormaCalculo(FormaCalculo formaCalculo) { this.formaCalculo = formaCalculo; }

    public Integer getBaseValor() { return baseValor; }
    public void setBaseValor(Integer baseValor) { this.baseValor = baseValor; }

    public TipoPunto getTipoPunto() { return tipoPunto; }
    public void setTipoPunto(TipoPunto tipoPunto) { this.tipoPunto = tipoPunto; }

    public Double getValorPunto() { return valorPunto; }
    public void setValorPunto(Double valorPunto) { this.valorPunto = valorPunto; }

    public Double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(Double porcentaje) { this.porcentaje = porcentaje; }

    public Double getValorMontoFijo() { return valorMontoFijo; }
    public void setValorMontoFijo(Double valorMontoFijo) { this.valorMontoFijo = valorMontoFijo; }

    public List<ProdEmpDetTurno> getTurnos() { return turnos; }
    public void setTurnos(List<ProdEmpDetTurno> turnos) { this.turnos = turnos; }
}
