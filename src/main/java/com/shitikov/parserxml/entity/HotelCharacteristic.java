package com.shitikov.parserxml.entity;

public class HotelCharacteristic {
    private int stars;
    private FoodType foodType;
    private HotelRoom hotelRoom;

    public HotelCharacteristic(int stars, FoodType foodType, HotelRoom hotelRoom) {
        this.stars = stars;
        this.foodType = foodType;
        this.hotelRoom = hotelRoom;
    }

    public int getStars() {
        return stars;
    }

    public HotelCharacteristic setStars(int stars) {
        this.stars = stars;
        return this;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public HotelCharacteristic setFoodType(FoodType foodType) {
        this.foodType = foodType;
        return this;
    }

    public HotelRoom getHotelRoom() {
        return hotelRoom;
    }

    public HotelCharacteristic setHotelRoom(HotelRoom hotelRoom) {
        this.hotelRoom = hotelRoom;
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

        HotelCharacteristic other = (HotelCharacteristic) obj;

        if (stars != other.stars) {
            return false;
        }
        if (foodType != other.foodType) {
            return false;
        }
        return hotelRoom != null ? hotelRoom.equals(other.hotelRoom) : other.hotelRoom == null;
    }

    @Override
    public int hashCode() {
        int result = stars;
        result = 31 * result + (foodType != null ? foodType.hashCode() : 0);
        result = 31 * result + (hotelRoom != null ? hotelRoom.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HotelCharacteristic{");
        sb.append("stars=").append(stars);
        sb.append(", foodType=").append(foodType);
        sb.append(", hotelRoom=").append(hotelRoom);
        sb.append('}');
        return sb.toString();
    }
}
