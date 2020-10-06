package com.shitikov.parserxml.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SeveralDayVoucher extends Voucher {
    private int days;
    private int nights;
    private HotelCharacteristic hotelCharacteristic = new HotelCharacteristic();

    public SeveralDayVoucher() {
    }

    public SeveralDayVoucher(String name, TourType tourType, String country
            , TransportType transportType, LocalDate date, BigDecimal cost
            , int days, int nights, HotelCharacteristic hotelCharacteristic) {
        super(name, tourType, country, transportType, date, cost);
        this.days = days;
        this.nights = nights;
        this.hotelCharacteristic = hotelCharacteristic;
    }

    public int getDays() {
        return days;
    }

    public SeveralDayVoucher setDays(int days) {
        this.days = days;
        return this;
    }

    public int getNights() {
        return nights;
    }

    public SeveralDayVoucher setNights(int nights) {
        this.nights = nights;
        return this;
    }

    public HotelCharacteristic getHotelCharacteristic() {
        return hotelCharacteristic;
    }

    public SeveralDayVoucher setHotelCharacteristic(HotelCharacteristic hotelCharacteristic) {
        this.hotelCharacteristic = hotelCharacteristic;
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
        if (!super.equals(obj)) {
            return false;
        }

        SeveralDayVoucher other = (SeveralDayVoucher) obj;

        if (days != other.days) {
            return false;
        }
        if (nights != other.nights) {
            return false;
        }
        return hotelCharacteristic != null
                ? hotelCharacteristic.equals(other.hotelCharacteristic) : other.hotelCharacteristic == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + days;
        result = 31 * result + nights;
        result = 31 * result + (hotelCharacteristic != null ? hotelCharacteristic.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SeveralDayVoucher{");
        sb.append("name='").append(super.getName()).append('\'');
        sb.append(", tourType=").append(super.getTourType());
        sb.append(", country='").append(super.getCountry()).append('\'');
        sb.append(", transportType=").append(super.getTransportType());
        sb.append(", date=").append(super.getDate());
        sb.append(", days=").append(days);
        sb.append(", nights=").append(nights);
        sb.append(", ").append(hotelCharacteristic);
        sb.append(", cost=").append(super.getCost());
        sb.append('}');
        return sb.toString();
    }
}
