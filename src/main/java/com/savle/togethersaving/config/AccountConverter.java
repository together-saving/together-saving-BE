package com.savle.togethersaving.config;

import com.savle.togethersaving.entity.AccountType;
import com.savle.togethersaving.entity.Mode;
import com.savle.togethersaving.entity.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AccountConverter implements AttributeConverter<AccountType, String> {
    @Override
    public String convertToDatabaseColumn(AccountType attribute) {
        return attribute.toDbValue();
    }

    @Override
    public AccountType convertToEntityAttribute(String dbData) {
        return AccountType.from(dbData);
    }
}
