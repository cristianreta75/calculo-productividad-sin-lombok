package com.productividad.calculo.model.converters;

import com.productividad.calculo.model.FormaCalculo;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class FormaCalculoConverter extends CaseInsensitiveEnumConverter<FormaCalculo> {
    public FormaCalculoConverter() { super(FormaCalculo.class); }
}
