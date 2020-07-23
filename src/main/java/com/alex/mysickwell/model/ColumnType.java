package com.alex.mysickwell.model;

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

    public static boolean allowedType(String string) { //TODO: ask other's opinion about this.
        try {
            ColumnType.valueOf(string);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;


    }
}
