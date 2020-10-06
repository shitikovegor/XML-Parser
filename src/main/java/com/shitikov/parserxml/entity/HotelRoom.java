package com.shitikov.parserxml.entity;

public class HotelRoom {
    private int guestsNumber;
    private boolean tv;
    private boolean airConditioning;
    private boolean wifi;
    private boolean refrigerator;

    public HotelRoom() {
    }

    public HotelRoom(int guestsNumber, boolean tv, boolean airConditioning, boolean wifi, boolean refrigerator) {
        this.guestsNumber = guestsNumber;
        this.tv = tv;
        this.airConditioning = airConditioning;
        this.wifi = wifi;
        this.refrigerator = refrigerator;
    }

    public int getGuestsNumber() {
        return guestsNumber;
    }

    public HotelRoom setGuestsNumber(int guestsNumber) {
        this.guestsNumber = guestsNumber;
        return this;
    }

    public boolean isTv() {
        return tv;
    }

    public HotelRoom setTv(boolean tv) {
        this.tv = tv;
        return this;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public HotelRoom setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
        return this;
    }

    public boolean isWifi() {
        return wifi;
    }

    public HotelRoom setWifi(boolean wifi) {
        this.wifi = wifi;
        return this;
    }

    public boolean isRefrigerator() {
        return refrigerator;
    }

    public HotelRoom setRefrigerator(boolean refrigerator) {
        this.refrigerator = refrigerator;
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

        HotelRoom other = (HotelRoom) obj;

        if (guestsNumber != other.guestsNumber) {
            return false;
        }
        if (tv != other.tv) {
            return false;
        }
        if (airConditioning != other.airConditioning) {
            return false;
        }
        if (wifi != other.wifi) {
            return false;
        }
        return refrigerator == other.refrigerator;
    }

    @Override
    public int hashCode() {
        int result = guestsNumber;
        result = 31 * result + (tv ? 1 : 0);
        result = 31 * result + (airConditioning ? 1 : 0);
        result = 31 * result + (wifi ? 1 : 0);
        result = 31 * result + (refrigerator ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HotelRoom{");
        sb.append("guestsNumber=").append(guestsNumber);
        sb.append(", tv=").append(tv);
        sb.append(", airConditioning=").append(airConditioning);
        sb.append(", wifi=").append(wifi);
        sb.append(", refrigerator=").append(refrigerator);
        sb.append('}');
        return sb.toString();
    }
}
