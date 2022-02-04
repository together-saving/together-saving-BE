package com.savle.togethersaving.entity;

public enum Frequency {
    MON,TUE,WED,THU,FRI,SAT,SUN;

    public static Frequency from(String dbData) {
        return Frequency.valueOf(dbData.toUpperCase());
    }

    public String toDbValue() {
        return this.name().toLowerCase();
    }
}
