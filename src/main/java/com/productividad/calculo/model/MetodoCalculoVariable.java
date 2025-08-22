package com.productividad.calculo.model;

import com.productividad.calculo.model.converters.FormaCalculoConverter;
import jakarta.persistence.*;

@Entity
@Table(name = "hu_productividad_metodo_calculo_variable")
public class MetodoCalculoVariable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metodo_calculo_id")
    private MetodoCalculo metodoCalculo;

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

    public MetodoCalculoVariable() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public MetodoCalculo getMetodoCalculo() { return metodoCalculo; }
    public void setMetodoCalculo(MetodoCalculo metodoCalculo) { this.metodoCalculo = metodoCalculo; }

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
}
