package com.savle.togethersaving.config;

import com.savle.togethersaving.entity.AccountType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AccountTypeConverter implements AttributeConverter<AccountType, String> {

    @Override
    public String convertToDatabaseColumn(AccountType attribute) {

        return attribute.toDbValue();
    }

    @Override
    public AccountType convertToEntityAttribute(String dbData) {

        return AccountType.from(dbData);
    }
}