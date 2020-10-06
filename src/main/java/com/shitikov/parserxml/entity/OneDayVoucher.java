package com.shitikov.parserxml.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;

public class OneDayVoucher extends Voucher {
    private boolean isWithLunch;
    private boolean isWithGuide;

    public OneDayVoucher() {
    }

    public OneDayVoucher(String name, TourType tourType, String country
            , TransportType transportType, LocalDate date
            , BigDecimal cost, boolean isWithLunch, boolean isWithGuide) {
        super(name, tourType, country, transportType, date, cost);
        this.isWithLunch = isWithLunch;
        this.isWithGuide = isWithGuide;
    }

    public boolean isWithLunch() {
        return isWithLunch;
    }

    public OneDayVoucher setWithLunch(boolean isWithLunch) {
        this.isWithLunch = isWithLunch;
        return this;
    }

    public boolean isWithGuide() {
        return isWithGuide;
    }

    public OneDayVoucher setWithGuide(boolean withGuide) {
        isWithGuide = withGuide;
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

        OneDayVoucher other = (OneDayVoucher) obj;

        if (isWithLunch != other.isWithLunch) {
            return false;
        }
        return isWithGuide == other.isWithGuide;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (isWithLunch ? 1 : 0);
        result = 31 * result + (isWithGuide ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OneDayVoucher{");
        sb.append("name='").append(super.getName()).append('\'');
        sb.append(", tourType=").append(super.getTourType());
        sb.append(", country='").append(super.getCountry()).append('\'');
        sb.append(", transportType=").append(super.getTransportType());
        sb.append(", date=").append(super.getDate());
        sb.append(", isWithLunch=").append(isWithLunch);
        sb.append(", isWithGuide=").append(isWithGuide);
        sb.append(", cost=").append(super.getCost());
        sb.append('}');
        return sb.toString();
    }
}
