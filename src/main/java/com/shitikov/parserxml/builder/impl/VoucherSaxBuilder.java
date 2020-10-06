package com.shitikov.parserxml.builder.impl;

import com.shitikov.parserxml.builder.VoucherBuilder;
import com.shitikov.parserxml.entity.Voucher;
import com.shitikov.parserxml.handler.VoucherSaxHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class VoucherSaxBuilder implements VoucherBuilder {
    private static Logger logger = LogManager.getLogger();
    private VoucherSaxHandler handler;
    private XMLReader reader;

    public VoucherSaxBuilder() {
        handler = new VoucherSaxHandler();
        try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            parserFactory.setNamespaceAware(true);
            parserFactory.setValidating(true);
            SAXParser parser = parserFactory.newSAXParser();
            parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                    "http://www.w3.org/2001/XMLSchema");

            reader = parser.getXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException | ParserConfigurationException e) {
            logger.log(Level.ERROR, "SAX parser error.", e);
        }
    }

    @Override
    public Set<Voucher> buildVouchers(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            logger.log(Level.ERROR, "SAX parser error.", e);
        } catch (IOException e) {
            logger.log(Level.ERROR, "I/O error.", e);
        }
        return handler.getVouchers();
    }
}
