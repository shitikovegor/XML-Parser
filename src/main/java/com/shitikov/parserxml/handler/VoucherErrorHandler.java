package com.shitikov.parserxml.handler;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class VoucherErrorHandler implements ErrorHandler {
    private Logger logger = LogManager.getLogger();

    public void warning(SAXParseException e) {
        logger.log(Level.WARN, getLineAddress(e) + "-" + e.getMessage());
    }

    public void error(SAXParseException e) {
        logger.log(Level.ERROR, getLineAddress(e) + " - " + e.getMessage());
    }

    public void fatalError(SAXParseException e) {
        logger.log(Level.FATAL, getLineAddress(e) + " - " + e.getMessage());
    }

    private String getLineAddress(SAXParseException e) {
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}
