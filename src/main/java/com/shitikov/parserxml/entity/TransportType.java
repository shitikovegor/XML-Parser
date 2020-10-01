package com.shitikov.parserxml.entity;

public enum TransportType {
    BUS ("Bus"),
    PLANE ("Plain"),
    SHIP ("Ship"),
    TRAIN ("Train");

    String name;

    TransportType(String name) {
        this.name = name;
    }
}
