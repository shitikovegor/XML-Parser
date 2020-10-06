package com.shitikov.parserxml.provider;

import com.shitikov.parserxml.builder.impl.VoucherDomBuilder;
import com.shitikov.parserxml.builder.impl.VoucherSaxBuilder;
import com.shitikov.parserxml.builder.impl.VoucherStaxBuilder;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class VoucherBuilderProviderTest {

    @DataProvider(name = "builderClass")
    public Object[][] createData() {
        return new Object[][]{{"sax", VoucherSaxBuilder.class}
                , {"dom", VoucherDomBuilder.class}
                , {"stax", VoucherStaxBuilder.class}};
    }

    @Test(dataProvider = "builderClass")
    public void testDefineBuilder(String builderName, Class expected) {
        Class actual = VoucherBuilderProvider.defineBuilder(builderName).getClass();
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = EnumConstantNotPresentException.class)
    public void testDefineBuilderException() {
       VoucherBuilderProvider.defineBuilder("sac");
    }
}