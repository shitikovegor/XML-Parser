package com.shitikov.parserxml.entity;

import java.math.BigDecimal;

public class Voucher {
    private int name;
    private TourType tourType;
    private String country;
    private int days;
    private int nights;
    private TransportType transportType;
    private HotelCharacteristic hotelCharacteristic;
    private BigDecimal cost;

    public int getName() {
        return name;
    }

    public Voucher setName(int name) {
        this.name = name;
        return this;
    }

    public TourType getTourType() {
        return tourType;
    }

    public Voucher setTourType(TourType tourType) {
        this.tourType = tourType;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Voucher setCountry(String country) {
        this.country = country;
        return this;
    }

    public int getDays() {
        return days;
    }

    public Voucher setDays(int days) {
        this.days = days;
        return this;
    }

    public int getNights() {
        return nights;
    }

    public Voucher setNights(int nights) {
        this.nights = nights;
        return this;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public Voucher setTransportType(TransportType transportType) {
        this.transportType = transportType;
        return this;
    }

    public HotelCharacteristic getHotelCharacteristic() {
        return hotelCharacteristic;
    }

    public Voucher setHotelCharacteristic(HotelCharacteristic hotelCharacteristic) {
        this.hotelCharacteristic = hotelCharacteristic;
        return this;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public Voucher setCost(BigDecimal cost) {
        this.cost = cost;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Voucher other = (Voucher) obj;

        if (name != other.name) {
            return false;
        }
        if (days != other.days) {
            return false;
        }
        if (nights != other.nights) {
            return false;
        }
        if (tourType != other.tourType) {
            return false;
        }
        if (country != null ? !country.equals(other.country) : other.country != null) {
            return false;
        }
        if (transportType != other.transportType) {
            return false;
        }
        if (hotelCharacteristic != null ? !hotelCharacteristic.equals(other.hotelCharacteristic) : other.hotelCharacteristic != null) {
            return false;
        }
        return cost != null ? cost.equals(other.cost) : other.cost == null;
    }

    @Override
    public int hashCode() {
        int result = name;
        result = 31 * result + (tourType != null ? tourType.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + days;
        result = 31 * result + nights;
        result = 31 * result + (transportType != null ? transportType.hashCode() : 0);
        result = 31 * result + (hotelCharacteristic != null ? hotelCharacteristic.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Voucher{");
        sb.append("name=").append(name);
        sb.append(", tourType=").append(tourType);
        sb.append(", country='").append(country).append('\'');
        sb.append(", days=").append(days);
        sb.append(", nights=").append(nights);
        sb.append(", transportType=").append(transportType);
        sb.append(", hotelCharacteristic=").append(hotelCharacteristic);
        sb.append(", cost=").append(cost);
        sb.append('}');
        return sb.toString();
    }
}
