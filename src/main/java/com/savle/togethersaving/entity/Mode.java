package com.savle.togethersaving.entity;

import java.util.Locale;

public enum Mode {

    FREE,COMPETITION;

    public String toDbValue() {
        return this.name().toLowerCase();
    }
    public static Mode from(String dbData) {
        return Mode.valueOf(dbData.toUpperCase());
    }
}
