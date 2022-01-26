package com.savle.togethersaving.entity;

import java.util.Locale;

public enum Role {
    ADMIN, USER;

    public String toDbValue() {
        return this.name().toLowerCase();
    }
    public static Role from(String dbData) {
        return Role.valueOf(dbData.toUpperCase(Locale.ROOT));
    }
}
