package com.shitikov.parserxml.handler;

import com.shitikov.parserxml.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class SeveralDayVoucherSaxHandler extends DefaultHandler {
    private static Logger logger = LogManager.getLogger();
    private Set<SeveralDayVoucher> vouchers;
    private SeveralDayVoucher current;
    private VoucherXmlTag currentEnum = null;
    private EnumSet<VoucherXmlTag> withText;

    public SeveralDayVoucherSaxHandler() {
        vouchers = new HashSet<>();
        withText = EnumSet.range(VoucherXmlTag.COUNTRY, VoucherXmlTag.REFRIGERATOR);
        withText.remove(VoucherXmlTag.IS_WITH_GUIDE);
        withText.remove(VoucherXmlTag.IS_WITH_LUNCH);
    }

    public Set<SeveralDayVoucher> getVouchers() {
        return vouchers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (VoucherXmlTag.SEVERAL_DAY_VOUCHER.getName().equals(localName)) {
            current = new SeveralDayVoucher();
            current.setName(attrs.getValue(VoucherXmlTag.NAME.getName()));
            String tourType = attrs.getValue(VoucherXmlTag.TOUR_TYPE.getName()).replace('-', '_').toUpperCase();
            current.setTourType(TourType.valueOf(tourType));
        } else {
            VoucherXmlTag temp = VoucherXmlTag.valueOf(localName.replace('-', '_').toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (VoucherXmlTag.SEVERAL_DAY_VOUCHER.getName().equals(localName)) {
            vouchers.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).trim();
        if (currentEnum != null && current != null) {
            switch (currentEnum) {
                case COUNTRY -> current.setCountry(value);
                case TRANSPORT -> current.setTransportType(TransportType.valueOf(value.toUpperCase()));
                case DATE -> current.setDate(LocalDate.parse(value));
                case COST -> current.setCost(new BigDecimal(value));
                case DAYS -> current.setDays(Integer.parseInt(value));
                case NIGHTS -> current.setNights(Integer.parseInt(value));
                case STARS -> current.getHotelCharacteristic().setStars(Integer.parseInt(value));
                case FOOD_TYPE -> current.getHotelCharacteristic().setFoodType(FoodType.valueOf(value.toUpperCase()));
                case GUESTS_NUMBER -> current.getHotelCharacteristic().getHotelRoom().setGuestsNumber(Integer.parseInt(value));
                case TV -> current.getHotelCharacteristic().getHotelRoom().setTv(Boolean.parseBoolean(value));
                case AIR_CONDITIONING -> current.getHotelCharacteristic().getHotelRoom().setAirConditioning(Boolean.parseBoolean(value));
                case WIFI -> current.getHotelCharacteristic().getHotelRoom().setWifi(Boolean.parseBoolean(value));
                case REFRIGERATOR -> current.getHotelCharacteristic().getHotelRoom().setRefrigerator(Boolean.parseBoolean(value));
                default -> throw new EnumConstantNotPresentException(
                        currentEnum.getDeclaringClass(), currentEnum.name());
//                    logger.log(Level.WARN, "Invalid value.");
            }
        }
        currentEnum = null;
    }
}
