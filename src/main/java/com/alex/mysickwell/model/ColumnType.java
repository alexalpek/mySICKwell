package com.alex.mysickwell.model;

import java.util.Arrays;
import java.util.function.Function;

public enum ColumnType {

    INTEGER(Integer.class, Integer::parseInt), VARCHAR(String.class, Function.identity()), BOOLEAN(Boolean.class, Boolean::valueOf);

    private Class<?> datatype;
    private Function<String, ?> converter;


    <F> ColumnType(Class<F> datatype, Function<String, F> converter) {
        this.datatype = datatype;
        this.converter = converter;
    }

    public Class<?> getDatatype() {
        return datatype;
    }

    public <F> F convert(String string) throws IllegalArgumentException {
        return (F) this.converter.apply(string);
    }

    public static ColumnType getTypeByString(String string) {
        return ColumnType.valueOf(string);
    }

    public static boolean allowedType(String string) {
        return Arrays
                .stream(ColumnType.values())
                .anyMatch(columnType -> columnType.name().equals(string.toUpperCase()));
    }
}
