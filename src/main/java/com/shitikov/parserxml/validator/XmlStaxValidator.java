package com.shitikov.parserxml.validator;

import com.shitikov.parserxml.handler.VoucherErrorHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class XmlStaxValidator {
    private static Logger logger = LogManager.getLogger();

    public void validate(String fileName, String schemaName, XMLStreamReader reader) {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            reader = inputFactory.createXMLStreamReader(new FileInputStream(fileName));
            Schema schema = factory.newSchema(schemaLocation);
            Source source = new StAXSource(reader);

            Validator validator = schema.newValidator();
            validator.setErrorHandler(new VoucherErrorHandler());

            validator.validate(source);
            logger.log(Level.INFO, fileName + " is valid");
        } catch (SAXException | IOException e) {
            logger.log(Level.FATAL, fileName + " is invalid. ", e);
        } catch (XMLStreamException e) {
            logger.log(Level.ERROR, "XML Stream error. ", e);
        }
    }
}
