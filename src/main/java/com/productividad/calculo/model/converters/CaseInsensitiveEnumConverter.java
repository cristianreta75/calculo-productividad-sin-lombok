package com.productividad.calculo.model.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public abstract class CaseInsensitiveEnumConverter<E extends Enum<E>> implements AttributeConverter<E, String> {

    private final Class<E> enumClass;

    protected CaseInsensitiveEnumConverter(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public String convertToDatabaseColumn(E attribute) {
        return attribute == null ? null : attribute.name();
    }

    @Override
    public E convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        String normalized = dbData.trim().toUpperCase();
        return Enum.valueOf(enumClass, normalized);
    }
}
