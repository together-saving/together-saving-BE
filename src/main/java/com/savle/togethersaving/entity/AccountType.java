package com.savle.togethersaving.entity;

import java.util.Locale;

public enum AccountType {
	PHYSICAL, CMA;



	public String toDbValue() {
		return this.name().toLowerCase();
	}
	public static AccountType from(String dbData) {
		return AccountType.valueOf(dbData.toUpperCase());
	}

}
