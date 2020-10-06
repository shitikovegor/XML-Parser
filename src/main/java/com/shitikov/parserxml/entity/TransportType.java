package com.shitikov.parserxml.entity;

public enum TransportType {
    BUS("Bus"),
    PLANE("Plane"),
    SHIP("Ship"),
    TRAIN("Train");

    private String name;

    TransportType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
