package com.shitikov.parserxml.entity;

public enum VoucherXmlTag {
    TOURIST_VOUCHERS("Tourist-vouchers"),
    VOUCHER("Voucher"),
    ONE_DAY_VOUCHER("One-day-voucher"),
    SEVERAL_DAY_VOUCHER("Several-day-voucher"),
    NAME("name"),
    TOUR_TYPE("type"),
    COUNTRY("country"),
    TRANSPORT("transport"),
    DATE("date"),
    COST("cost"),
    IS_WITH_LUNCH("is-with-lunch"),
    IS_WITH_GUIDE("is-with-guide"),
    DAYS("days"),
    NIGHTS("nights"),
    STARS("stars"),
    FOOD_TYPE("food-type"),
    GUESTS_NUMBER("guests-number"),
    TV("tv"),
    AIR_CONDITIONING("air-conditioning"),
    WIFI("wifi"),
    REFRIGERATOR("refrigerator"),
    HOTEL_CHARACTERISTIC("hotel-characteristic"),
    HOTEL_ROOM("hotel-room");

    private String name;

    private VoucherXmlTag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
