package com.productividad.calculo.model.converters;

import com.productividad.calculo.model.EstadoProductividad;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EstadoProductividadConverter extends CaseInsensitiveEnumConverter<EstadoProductividad> {
    public EstadoProductividadConverter() { super(EstadoProductividad.class); }
}
