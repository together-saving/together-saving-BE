package com.savle.togethersaving.config;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.Mode;

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
