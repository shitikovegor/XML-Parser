package com.shitikov.parserxml.builder;

import com.shitikov.parserxml.entity.Voucher;

import java.util.Set;

public interface VoucherBuilder {
    Set<Voucher> buildVouchers(String fileName);
}
