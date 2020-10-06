package com.shitikov.parserxml.builder.impl;

import com.shitikov.parserxml.builder.VoucherBuilder;
import com.shitikov.parserxml.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class VoucherDomBuilder implements VoucherBuilder {
    private static Logger logger = LogManager.getLogger();
    private DocumentBuilder docBuilder;

    public VoucherDomBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                "http://www.w3.org/2001/XMLSchema");
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR, "Parser's configuration error. " + e);
        }
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }

    @Override
    public Set<Voucher> buildVouchers(String fileName) {
        Document document;
        Set<Voucher> vouchers = new HashSet<>();

        try {
            document = docBuilder.parse(fileName);
            Element root = document.getDocumentElement();

            NodeList oneDayVouchersList = root.getElementsByTagName(VoucherXmlTag.ONE_DAY_VOUCHER.getName());
            for (int i = 0; i < oneDayVouchersList.getLength(); i++) {
                Element oneDayVoucherElement = (Element) oneDayVouchersList.item(i);
                OneDayVoucher oneDayVoucher = buildOneDayVoucher(oneDayVoucherElement);
                vouchers.add(oneDayVoucher);
            }

            NodeList severalDayVouchersList = root.getElementsByTagName(VoucherXmlTag.SEVERAL_DAY_VOUCHER.getName());
            for (int i = 0; i < severalDayVouchersList.getLength(); i++) {
                Element severalDayVoucherElement = (Element) severalDayVouchersList.item(i);
                SeveralDayVoucher severalDayVoucher = buildSeveralDayVoucher(severalDayVoucherElement);
                vouchers.add(severalDayVoucher);
            }
        } catch (IOException e) {
            logger.log(Level.ERROR, "File error or I/O error: " + e);
        } catch (SAXException e) {
            logger.log(Level.ERROR, "Parsing failure: " + e);
        }
        return vouchers;
    }

    private OneDayVoucher buildOneDayVoucher(Element oneDayVoucherElement) {
        OneDayVoucher voucher = new OneDayVoucher();

        voucher.setName(oneDayVoucherElement.getAttribute(VoucherXmlTag.NAME.getName()));
        String tourType = oneDayVoucherElement.getAttribute(VoucherXmlTag.TOUR_TYPE.getName());
        voucher.setTourType(TourType.valueOf(tourType.replace('-', '_').toUpperCase()));
        voucher.setCountry(getElementTextContent(oneDayVoucherElement, VoucherXmlTag.COUNTRY.getName()));
        voucher.setTransportType(TransportType.valueOf(
                getElementTextContent(oneDayVoucherElement, VoucherXmlTag.TRANSPORT.getName()).toUpperCase()));
        voucher.setDate(LocalDate.parse(getElementTextContent(oneDayVoucherElement, VoucherXmlTag.DATE.getName())));
        voucher.setCost(new BigDecimal(getElementTextContent(oneDayVoucherElement, VoucherXmlTag.COST.getName())));
        voucher.setWithLunch(Boolean.parseBoolean(getElementTextContent(
                oneDayVoucherElement, VoucherXmlTag.IS_WITH_LUNCH.getName())));
        voucher.setWithGuide(Boolean.parseBoolean(getElementTextContent(
                oneDayVoucherElement, VoucherXmlTag.IS_WITH_GUIDE.getName())));

        return voucher;
    }

    private SeveralDayVoucher buildSeveralDayVoucher(Element severalDayVoucherElement) {
        SeveralDayVoucher voucher = new SeveralDayVoucher();

        voucher.setName(severalDayVoucherElement.getAttribute(VoucherXmlTag.NAME.getName()));
        String tourType = severalDayVoucherElement.getAttribute(VoucherXmlTag.TOUR_TYPE.getName());
        voucher.setTourType(TourType.valueOf(tourType.replace('-', '_').toUpperCase()));
        voucher.setCountry(getElementTextContent(severalDayVoucherElement, VoucherXmlTag.COUNTRY.getName()));
        voucher.setTransportType(TransportType.valueOf(
                getElementTextContent(severalDayVoucherElement, VoucherXmlTag.TRANSPORT.getName()).toUpperCase()));
        voucher.setDate(LocalDate.parse(getElementTextContent(severalDayVoucherElement, VoucherXmlTag.DATE.getName())));
        voucher.setCost(new BigDecimal(getElementTextContent(severalDayVoucherElement, VoucherXmlTag.COST.getName())));
        voucher.setDays(Integer.parseInt(getElementTextContent(severalDayVoucherElement, VoucherXmlTag.DAYS.getName())));
        voucher.setNights(Integer.parseInt(getElementTextContent(severalDayVoucherElement, VoucherXmlTag.NIGHTS.getName())));

        HotelCharacteristic characteristic = voucher.getHotelCharacteristic();
        Element characteristicElement = (Element) severalDayVoucherElement.getElementsByTagName(
                VoucherXmlTag.HOTEL_CHARACTERISTIC.getName()).item(0);
        characteristic.setStars(Integer.parseInt(getElementTextContent(characteristicElement,
                VoucherXmlTag.STARS.getName())));
        characteristic.setFoodType(FoodType.valueOf(getElementTextContent(
                characteristicElement, VoucherXmlTag.FOOD_TYPE.getName()).toUpperCase()));

        HotelRoom room = characteristic.getHotelRoom();
        Element roomElement = (Element) characteristicElement.getElementsByTagName(
                VoucherXmlTag.HOTEL_ROOM.getName()).item(0);
        room.setGuestsNumber(Integer.parseInt(getElementTextContent(roomElement,
                VoucherXmlTag.GUESTS_NUMBER.getName())));
        room.setTv(Boolean.parseBoolean(getElementTextContent(roomElement, VoucherXmlTag.TV.getName())));
        room.setAirConditioning(Boolean.parseBoolean(getElementTextContent(
                roomElement, VoucherXmlTag.AIR_CONDITIONING.getName())));
        room.setWifi(Boolean.parseBoolean(getElementTextContent(roomElement, VoucherXmlTag.WIFI.getName())));
        room.setRefrigerator(Boolean.parseBoolean(getElementTextContent(
                roomElement, VoucherXmlTag.REFRIGERATOR.getName())));

        return voucher;
    }
}
