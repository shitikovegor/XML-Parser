package com.shitikov.parserxml.builder.impl;

import com.shitikov.parserxml.builder.VoucherBuilder;
import com.shitikov.parserxml.entity.OneDayVoucher;
import com.shitikov.parserxml.entity.SeveralDayVoucher;
import com.shitikov.parserxml.entity.Voucher;
import com.shitikov.parserxml.handler.OneDayVoucherSaxHandler;
import com.shitikov.parserxml.handler.SeveralDayVoucherSaxHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class VoucherSaxBuilder implements VoucherBuilder {
    private static Logger logger = LogManager.getLogger();
    private OneDayVoucherSaxHandler oneDaySaxHandler;
    private SeveralDayVoucherSaxHandler severalDaySaxHandler;
    private XMLReader oneDayReader;
    private XMLReader severalDayReader;

    public VoucherSaxBuilder() {
        oneDaySaxHandler = new OneDayVoucherSaxHandler();
        severalDaySaxHandler = new SeveralDayVoucherSaxHandler();
        try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            parserFactory.setNamespaceAware(true);
            parserFactory.setValidating(true);
            SAXParser oneDayParser = parserFactory.newSAXParser();
            SAXParser severalDayParser = parserFactory.newSAXParser();
            oneDayParser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                    "http://www.w3.org/2001/XMLSchema");
            severalDayParser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                    "http://www.w3.org/2001/XMLSchema");

            oneDayReader = oneDayParser.getXMLReader();
            oneDayReader.setContentHandler(oneDaySaxHandler);
            severalDayReader = severalDayParser.getXMLReader();
            severalDayReader.setContentHandler(severalDaySaxHandler);
        } catch (SAXException | ParserConfigurationException e) {
            logger.log(Level.ERROR, "SAX parser error.", e);
        }
    }

    @Override
    public Set<Voucher> buildVouchers(String fileName) {
        Set<Voucher> vouchers = new HashSet<>();
        try {
            oneDayReader.parse(fileName);
            severalDayReader.parse(fileName);
        } catch (SAXException e) {
            logger.log(Level.ERROR, "SAX parser error.", e);
        } catch (IOException e) {
            logger.log(Level.ERROR, "I/O error.", e);
        }

        for (OneDayVoucher voucher : oneDaySaxHandler.getVouchers()) {
            vouchers.add(voucher);
        }
        for (SeveralDayVoucher voucher : severalDaySaxHandler.getVouchers()) {
            vouchers.add(voucher);
        }
        return vouchers;
    }
}
