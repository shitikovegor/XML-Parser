package com.shitikov.parserxml.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Voucher {
    private String name;
    private TourType tourType;
    private String country;
    private TransportType transportType;
    private LocalDate date;
    private BigDecimal cost;

    public Voucher() {
    }

    public Voucher(String name, TourType tourType, String country, TransportType transportType,
                   LocalDate date, BigDecimal cost) {
        this.name = name;
        this.tourType = tourType;
        this.country = country;
        this.transportType = transportType;
        this.date = date;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public Voucher setName(String name) {
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

    public TransportType getTransportType() {
        return transportType;
    }

    public Voucher setTransportType(TransportType transportType) {
        this.transportType = transportType;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public Voucher setDate(LocalDate date) {
        this.date = date;
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

        if (name != null ? !name.equals(other.name) : other.name != null) {
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
        if (date != null ? !date.equals(other.date) : other.date != null) {
            return false;
        }
        return cost != null ? cost.equals(other.cost) : other.cost == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (tourType != null ? tourType.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (transportType != null ? transportType.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Voucher{");
        sb.append("name='").append(name).append('\'');
        sb.append(", tourType=").append(tourType);
        sb.append(", country='").append(country).append('\'');
        sb.append(", transportType=").append(transportType);
        sb.append(", date=").append(date);
        sb.append(", cost=").append(cost);
        sb.append('}');
        return sb.toString();
    }
}
