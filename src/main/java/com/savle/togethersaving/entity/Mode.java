package com.savle.togethersaving.entity;

public enum Mode {

    FREE,COMPETITION;

    public String toDbValue() {
        return this.name().toLowerCase();
    }
    public static Mode from(String dbData) {
        return Mode.valueOf(dbData.toUpperCase());
    }
}
