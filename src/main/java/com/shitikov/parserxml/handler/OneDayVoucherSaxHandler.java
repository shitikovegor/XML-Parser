package com.shitikov.parserxml.handler;

import com.shitikov.parserxml.entity.OneDayVoucher;
import com.shitikov.parserxml.entity.TourType;
import com.shitikov.parserxml.entity.TransportType;
import com.shitikov.parserxml.entity.VoucherXmlTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class OneDayVoucherSaxHandler extends DefaultHandler {
    private static Logger logger = LogManager.getLogger();
    private Set<OneDayVoucher> vouchers;
    private OneDayVoucher current;
    private VoucherXmlTag currentEnum = null;
    private EnumSet<VoucherXmlTag> withText;

    public OneDayVoucherSaxHandler() {
        vouchers = new HashSet<>();
        withText = EnumSet.range(VoucherXmlTag.COUNTRY, VoucherXmlTag.IS_WITH_GUIDE);
    }

    public Set<OneDayVoucher> getVouchers() {
        return vouchers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (VoucherXmlTag.ONE_DAY_VOUCHER.getName().equals(localName)) {
            current = new OneDayVoucher();
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
        if (VoucherXmlTag.ONE_DAY_VOUCHER.getName().equals(localName)) {
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
                case IS_WITH_LUNCH -> current.setWithLunch(Boolean.parseBoolean(value));
                case IS_WITH_GUIDE -> current.setWithGuide(Boolean.parseBoolean(value));
                default -> throw new EnumConstantNotPresentException(
                        currentEnum.getDeclaringClass(), currentEnum.name());
//                    logger.log(Level.WARN, "Invalid value.");
            }
        }
        currentEnum = null;
    }
}
