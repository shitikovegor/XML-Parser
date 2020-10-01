package com.shitikov.parserxml.entity;

public enum FoodType {
    ALL_INCLUSIVE("AI"),
    BREAKFAST("BB"),
    BREAKFAST_DINNER("HB"),
    BREAKFAST_LUNCH_DINNER("FB"),
    WITHOUT_FOOD("SC");

    String name;

    FoodType(String name) {
        this.name = name;
    }
}
