package com.shitikov.parserxml.dataprovider;

import com.shitikov.parserxml.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class VoucherDataProvider {
    public static final Set<Voucher> VOUCHERS;

    static {
        HotelRoom hr1 = new HotelRoom(2, true, true, false, false);
        HotelCharacteristic hc1 = new HotelCharacteristic(4, FoodType.BB, hr1);
        Voucher voucher1 = new SeveralDayVoucher("Spain-tour", TourType.RECREATION
        ,"Spain", TransportType.BUS, LocalDate.parse("2020-10-05")
                ,new BigDecimal(500), 3, 4, hc1);

        HotelRoom hr2 = new HotelRoom(2, true, true, true, true);
        HotelCharacteristic hc2 = new HotelCharacteristic(5, FoodType.AI, hr2);
        Voucher voucher2 = new SeveralDayVoucher("Turkey-tour", TourType.RECREATION
                ,"Turkey", TransportType.PLANE, LocalDate.parse("2020-10-09")
                ,new BigDecimal(650), 7, 8, hc2);

        HotelRoom hr3 = new HotelRoom(2, false, true, true, false);
        HotelCharacteristic hc3 = new HotelCharacteristic(3, FoodType.SC, hr3);
        Voucher voucher3 = new SeveralDayVoucher("Lviv-weekend", TourType.EXCURSION
                ,"Ukraine", TransportType.BUS, LocalDate.parse("2020-10-15")
                ,new BigDecimal(150), 2, 3, hc3);

        HotelRoom hr4 = new HotelRoom(1, true, true, true, false);
        HotelCharacteristic hc4 = new HotelCharacteristic(3, FoodType.FB, hr4);
        Voucher voucher4 = new SeveralDayVoucher("Sanatorium", TourType.THERAPY
                ,"Belarus", TransportType.BUS, LocalDate.parse("2020-10-16")
                ,new BigDecimal(350), 4, 5, hc4);

        HotelRoom hr5 = new HotelRoom(2, true, true, true, true);
        HotelCharacteristic hc5 = new HotelCharacteristic(2, FoodType.BB, hr5);
        Voucher voucher5 = new SeveralDayVoucher("New-Year-in-Warsaw", TourType.NEW_YEAR
                ,"Poland", TransportType.BUS, LocalDate.parse("2020-10-06")
                ,new BigDecimal(199), 2, 2, hc5);

        HotelRoom hr6 = new HotelRoom(2, true, true, true, true);
        HotelCharacteristic hc6 = new HotelCharacteristic(5, FoodType.AI, hr6);
        Voucher voucher6 = new SeveralDayVoucher("Vacation-tour", TourType.RECREATION
                ,"Egypt", TransportType.PLANE, LocalDate.parse("2020-10-25")
                ,new BigDecimal(1500), 7, 8, hc6);

        HotelRoom hr7 = new HotelRoom(2, true, true, true, true);
        HotelCharacteristic hc7 = new HotelCharacteristic(3, FoodType.FB, hr7);
        Voucher voucher7 = new SeveralDayVoucher("Weekend-tour", TourType.CRUISE
                ,"Greece", TransportType.SHIP, LocalDate.parse("2020-10-09")
                ,new BigDecimal(352), 2, 3, hc7);

        Voucher voucher8 = new OneDayVoucher("One-day-shopping", TourType.SHOPPING
                ,"Italy", TransportType.BUS, LocalDate.parse("2020-10-09")
                ,new BigDecimal(99), true, false);

        HotelRoom hr9 = new HotelRoom(2, true, false, true, true);
        HotelCharacteristic hc9 = new HotelCharacteristic(5, FoodType.FB, hr9);
        Voucher voucher9 = new SeveralDayVoucher("Sea-tour", TourType.RECREATION
                ,"Spain", TransportType.PLANE, LocalDate.parse("2020-10-19")
                ,new BigDecimal(785), 6, 7, hc9);

        Voucher voucher10 = new OneDayVoucher("Excursion-tour", TourType.EXCURSION
                ,"Belarus", TransportType.BUS, LocalDate.parse("2020-10-06")
                ,new BigDecimal(65), true, true);

        HotelRoom hr11 = new HotelRoom(1, true, true, true, true);
        HotelCharacteristic hc11 = new HotelCharacteristic(4, FoodType.FB, hr11);
        Voucher voucher11 = new SeveralDayVoucher("Therapy-tour", TourType.THERAPY
                ,"Israel", TransportType.TRAIN, LocalDate.parse("2020-10-20")
                ,new BigDecimal(1865), 3, 4, hc11);

        HotelRoom hr12 = new HotelRoom(1, false, false, false, false);
        HotelCharacteristic hc12 = new HotelCharacteristic(1, FoodType.FB, hr12);
        Voucher voucher12 = new SeveralDayVoucher("Therapy-tour-1", TourType.THERAPY
                ,"Belarus", TransportType.BUS, LocalDate.parse("2020-10-30")
                ,new BigDecimal(150), 15, 15, hc12);

        HotelRoom hr13 = new HotelRoom(2, true, true, true, true);
        HotelCharacteristic hc13 = new HotelCharacteristic(4, FoodType.AI, hr13);
        Voucher voucher13 = new SeveralDayVoucher("Cyprus-tour", TourType.RECREATION
                ,"Cyprus", TransportType.PLANE, LocalDate.parse("2020-10-12")
                ,new BigDecimal(840), 10, 11, hc13);

        Voucher voucher14 = new OneDayVoucher("Minsk-excursion", TourType.EXCURSION
                ,"Belarus", TransportType.BUS, LocalDate.parse("2020-10-12")
                ,new BigDecimal(25), false, true);

        Voucher voucher15 = new OneDayVoucher("Shopping-tour-Poland", TourType.SHOPPING
                ,"Poland", TransportType.BUS, LocalDate.parse("2020-10-05")
                ,new BigDecimal(86), true, false);

        HotelRoom hr16 = new HotelRoom(2, true, true, true, true);
        HotelCharacteristic hc16 = new HotelCharacteristic(3, FoodType.HB, hr16);
        Voucher voucher16 = new SeveralDayVoucher("New-year-in-Prague", TourType.NEW_YEAR
                ,"Czech", TransportType.BUS, LocalDate.parse("2020-10-15")
                ,new BigDecimal(320), 3, 4, hc16);

        HotelRoom hr17 = new HotelRoom(2, true, false, false, true);
        HotelCharacteristic hc17 = new HotelCharacteristic(2, FoodType.SC, hr17);
        Voucher voucher17 = new SeveralDayVoucher("Gold-ring-tour", TourType.EXCURSION
                ,"Russia", TransportType.BUS, LocalDate.parse("2020-10-23")
                ,new BigDecimal(350), 3, 4, hc17);

        VOUCHERS = new HashSet<>();
        VOUCHERS.add(voucher1);
        VOUCHERS.add(voucher2);
        VOUCHERS.add(voucher3);
        VOUCHERS.add(voucher4);
        VOUCHERS.add(voucher5);
        VOUCHERS.add(voucher6);
        VOUCHERS.add(voucher7);
        VOUCHERS.add(voucher8);
        VOUCHERS.add(voucher9);
        VOUCHERS.add(voucher10);
        VOUCHERS.add(voucher11);
        VOUCHERS.add(voucher12);
        VOUCHERS.add(voucher13);
        VOUCHERS.add(voucher14);
        VOUCHERS.add(voucher15);
        VOUCHERS.add(voucher16);
        VOUCHERS.add(voucher17);
    }
}
