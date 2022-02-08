package com.savle.togethersaving.config;

import com.savle.togethersaving.entity.Mode;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ModeConverter implements AttributeConverter<Mode, String> {

	@Override
	public String convertToDatabaseColumn(Mode attribute) {
		return attribute.toDbValue();
	}

	@Override
	public Mode convertToEntityAttribute(String dbData) {
		return Mode.from(dbData);
	}
}
