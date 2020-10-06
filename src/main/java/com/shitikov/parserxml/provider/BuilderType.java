package com.shitikov.parserxml.provider;

import com.shitikov.parserxml.builder.VoucherBuilder;
import com.shitikov.parserxml.builder.impl.VoucherDomBuilder;
import com.shitikov.parserxml.builder.impl.VoucherSaxBuilder;
import com.shitikov.parserxml.builder.impl.VoucherStaxBuilder;

public enum BuilderType {
    DOM(new VoucherDomBuilder()),
    SAX(new VoucherSaxBuilder()),
    STAX(new VoucherStaxBuilder());

    private VoucherBuilder voucherBuilder;

    BuilderType(VoucherBuilder voucherBuilder) {
        this.voucherBuilder = voucherBuilder;
    }

    public VoucherBuilder getVoucherBuilder() {
        return voucherBuilder;
    }
}
