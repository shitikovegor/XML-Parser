package com.shitikov.parserxml.entity;

public enum TourType {
    CRUISE("Cruise"),
    EXCURSION("Excursion"),
    NEW_YEAR("New-Year"),
    RECREATION("Recreation"),
    SHOPPING("Shopping"),
    THERAPY("Therapy");

    private String name;

    TourType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
