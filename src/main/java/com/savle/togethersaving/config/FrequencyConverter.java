package com.savle.togethersaving.config;

import com.savle.togethersaving.entity.Frequency;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class FrequencyConverter implements AttributeConverter<Frequency, String> {

    @Override
    public String convertToDatabaseColumn(Frequency attribute) {
        return attribute.toDbValue();
    }

    @Override
    public Frequency convertToEntityAttribute(String dbData) {
        return Frequency.from(dbData);
    }
}
