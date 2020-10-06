package com.shitikov.parserxml.entity;

public enum FoodType {
    AI("AI"),
    BB("BB"),
    HB("HB"),
    FB("FB"),
    SC("SC");

    private String name;

    FoodType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
