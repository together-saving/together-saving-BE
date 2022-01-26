package com.savle.togethersaving.config;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.savle.togethersaving.entity.Mode;
import com.savle.togethersaving.entity.Role;

@Converter
public class RoleConverter implements AttributeConverter<Role, String> {

	@Override
	public String convertToDatabaseColumn(Role attribute) {
		return attribute.toDbValue();
	}

	@Override
	public Role convertToEntityAttribute(String dbData) {
		return Role.from(dbData);
	}
}
