package com.shitikov.parserxml.provider;

import com.shitikov.parserxml.builder.VoucherBuilder;

import java.util.Arrays;
import java.util.Optional;

public class VoucherBuilderProvider {

    public static VoucherBuilder defineBuilder(String builderName) {
        Optional<VoucherBuilder> builderOptional = Arrays.stream(BuilderType.values())
                .filter(builderType -> builderType.toString()
                        .equalsIgnoreCase(builderName.toUpperCase()))
                .findFirst()
                .map(BuilderType::getVoucherBuilder);

        VoucherBuilder builder = builderOptional.orElseThrow(
                () -> new EnumConstantNotPresentException(BuilderType.class, builderName));

        return builder;
    }
}
