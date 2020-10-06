package com.shitikov.parserxml.builder.impl;

import com.shitikov.parserxml.builder.VoucherBuilder;
import com.shitikov.parserxml.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


public class VoucherStaxBuilder implements VoucherBuilder {
    private static final TourType DEFAULT_TOUR_TYPE = TourType.RECREATION;
    private static final TransportType DEFAULT_TRANSPORT_TYPE = TransportType.PLANE;
    private static final LocalDate DEFAULT_DATE = LocalDate.parse("2020-10-06");
    private static Logger logger = LogManager.getLogger();

    @Override
    public Set<Voucher> buildVouchers(String fileName) {
        Set<Voucher> vouchers = new HashSet<>();
        FileInputStream inputStream = null;
        XMLInputFactory factory;
        XMLStreamReader reader;
        String localName;


        try {
            factory = XMLInputFactory.newInstance();
            inputStream = new FileInputStream(fileName);
            reader = factory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    localName = reader.getLocalName();
                    if (VoucherXmlTag.ONE_DAY_VOUCHER.getName().equals(localName)) {
                        OneDayVoucher voucher = buildOneDayVoucher(reader);
                        vouchers.add(voucher);
                    } else if (VoucherXmlTag.SEVERAL_DAY_VOUCHER.getName().equals(localName)) {
                        SeveralDayVoucher voucher = buildSeveralDayVoucher(reader);
                        vouchers.add(voucher);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.ERROR, "File " + fileName + " not found", e);
        } catch (XMLStreamException e) {
            logger.log(Level.ERROR, "XML Stream error.", e);
        } catch (IOException e) {
            logger.log(Level.ERROR, "I/O error.", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.log(Level.ERROR, "Impossible close file " + fileName + ". ", e);
            }
        }
        return vouchers;
    }

    private OneDayVoucher buildOneDayVoucher(XMLStreamReader reader) throws XMLStreamException {
        OneDayVoucher voucher = new OneDayVoucher();
        setVoucherAttributes(voucher, reader);

        String localeName;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    localeName = reader.getLocalName();
                    switch (VoucherXmlTag.valueOf(localeName.replace('-', '_').toUpperCase())) {
                        case COUNTRY -> voucher.setCountry(getXMLText(reader));
                        case TRANSPORT -> {
                            String transportType = getXMLText(reader);
                            if (transportType != null) {
                                voucher.setTransportType(TransportType.valueOf(transportType.toUpperCase()));
                            } else {
                                voucher.setTransportType(DEFAULT_TRANSPORT_TYPE);
                            }
                        }
                        case DATE -> {
                            String date = getXMLText(reader);
                            if (date != null) {
                                voucher.setDate(LocalDate.parse(date));
                            } else {
                                voucher.setDate(DEFAULT_DATE);
                            }
                        }
                        case COST -> voucher.setCost(new BigDecimal(getXMLText(reader)));
                        case IS_WITH_LUNCH -> voucher.setWithLunch(Boolean.parseBoolean(getXMLText(reader)));
                        case IS_WITH_GUIDE -> voucher.setWithGuide(Boolean.parseBoolean(getXMLText(reader)));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    localeName = reader.getLocalName();
                    if (VoucherXmlTag.valueOf(localeName.replace('-', '_').toUpperCase())
                            == VoucherXmlTag.ONE_DAY_VOUCHER) {
                        return voucher;
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag One-Day-Voucher");
    }

    private SeveralDayVoucher buildSeveralDayVoucher(XMLStreamReader reader) throws XMLStreamException {
        SeveralDayVoucher voucher = new SeveralDayVoucher();
        setVoucherAttributes(voucher, reader);

        String localeName;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    localeName = reader.getLocalName();
                    switch (VoucherXmlTag.valueOf(localeName.replace('-', '_').toUpperCase())) {
                        case COUNTRY -> voucher.setCountry(getXMLText(reader));
                        case TRANSPORT -> {
                            String transportType = getXMLText(reader);
                            if (transportType != null) {
                                voucher.setTransportType(TransportType.valueOf(transportType.toUpperCase()));
                            } else {
                                voucher.setTransportType(DEFAULT_TRANSPORT_TYPE);
                            }
                        }
                        case DATE -> {
                            String date = getXMLText(reader);
                            if (date != null) {
                                voucher.setDate(LocalDate.parse(date));
                            } else {
                                voucher.setDate(DEFAULT_DATE);
                            }
                        }
                        case COST -> voucher.setCost(new BigDecimal(getXMLText(reader)));
                        case DAYS -> voucher.setDays(Integer.parseInt(getXMLText(reader)));
                        case NIGHTS -> voucher.setNights(Integer.parseInt(getXMLText(reader)));
                        case HOTEL_CHARACTERISTIC -> voucher.setHotelCharacteristic(getXMLHotelCharacteristic(reader));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    localeName = reader.getLocalName();
                    if (VoucherXmlTag.valueOf(localeName.replace('-', '_').toUpperCase())
                            == VoucherXmlTag.SEVERAL_DAY_VOUCHER) {
                        return voucher;
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag Several-Day-Voucher");
    }

    private HotelCharacteristic getXMLHotelCharacteristic(XMLStreamReader reader) throws XMLStreamException {
        HotelCharacteristic characteristic = new HotelCharacteristic();
        String localeName;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    localeName = reader.getLocalName();
                    switch (VoucherXmlTag.valueOf(localeName.replace('-', '_').toUpperCase())) {
                        case STARS -> characteristic.setStars(Integer.parseInt(getXMLText(reader)));
                        case FOOD_TYPE -> characteristic.setFoodType(FoodType.valueOf(getXMLText(reader).toUpperCase()));
                        case HOTEL_ROOM -> characteristic.setHotelRoom(getXMLHotelRoom(reader));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    localeName = reader.getLocalName();
                    if (VoucherXmlTag.valueOf(localeName.replace('-', '_').toUpperCase())
                            == VoucherXmlTag.HOTEL_CHARACTERISTIC) {
                        return characteristic;
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag hotel-characteristic");
    }

    private HotelRoom getXMLHotelRoom(XMLStreamReader reader) throws XMLStreamException {
        HotelRoom hotelRoom = new HotelRoom();
        String localeName;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    localeName = reader.getLocalName();
                    switch (VoucherXmlTag.valueOf(localeName.replace('-', '_').toUpperCase())) {
                        case GUESTS_NUMBER -> hotelRoom.setGuestsNumber(Integer.parseInt(getXMLText(reader)));
                        case TV -> hotelRoom.setTv(Boolean.parseBoolean(getXMLText(reader)));
                        case AIR_CONDITIONING -> hotelRoom.setAirConditioning(Boolean.parseBoolean(getXMLText(reader)));
                        case WIFI -> hotelRoom.setWifi(Boolean.parseBoolean(getXMLText(reader)));
                        case REFRIGERATOR -> hotelRoom.setRefrigerator(Boolean.parseBoolean(getXMLText(reader)));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    localeName = reader.getLocalName();
                    if (VoucherXmlTag.valueOf(localeName.replace('-', '_').toUpperCase())
                            == VoucherXmlTag.HOTEL_ROOM) {
                        return hotelRoom;
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag hotel-room");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            if (reader.hasText()) {
                text = reader.getText();
            }
        }
        return text;
    }

    private void setVoucherAttributes(Voucher voucher, XMLStreamReader reader) {
        voucher.setName(reader.getAttributeValue(null, VoucherXmlTag.NAME.getName()));
        String tourType = reader
                .getAttributeValue(null, VoucherXmlTag.TOUR_TYPE.getName());
        if (tourType != null) {
            voucher.setTourType(TourType.valueOf(tourType.replace('-', '_').toUpperCase()));
        } else {
            voucher.setTourType(DEFAULT_TOUR_TYPE);
        }
    }
}
