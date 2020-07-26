package com.alex.mysickwell.model;

import java.util.Arrays;

public enum ColumnType {

    INTEGER(Integer.class), VARCHAR(String.class), BOOLEAN(Boolean.class);

    private Class<?> datatype;

    ColumnType(Class<?> datatype) {
        this.datatype = datatype;
    }

    public Class<?> getDatatype() {
        return datatype;
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
