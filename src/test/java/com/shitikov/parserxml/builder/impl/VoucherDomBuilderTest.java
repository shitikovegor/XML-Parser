package com.shitikov.parserxml.builder.impl;

import com.shitikov.parserxml.dataprovider.VoucherDataProvider;
import com.shitikov.parserxml.entity.Voucher;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.*;

public class VoucherDomBuilderTest {
    VoucherDomBuilder builder;

    @BeforeMethod
    public void setUp() {
        builder = new VoucherDomBuilder();
    }

    @Test
    public void testBuildVouchers() {
        Set<Voucher> actual = builder.buildVouchers("input/TouristVouchers.xml");
        assertEquals(actual, VoucherDataProvider.VOUCHERS);
    }

}