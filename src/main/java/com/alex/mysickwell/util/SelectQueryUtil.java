package com.alex.mysickwell.util;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SelectQueryUtil {

    private String QUERY_START_STRING = "SELECT ";

    public String getTableNameFromQuery(String query) {
        String[] queryData = query.split("(?i)FROM ");
        String tableNameWithSemiColon = queryData[1];
        return tableNameWithSemiColon
                .substring(0, tableNameWithSemiColon.length() - 1);
    }

    public String[] getParametersFromQuery(String query) {
        String[] queryData = query
                .substring(QUERY_START_STRING.length())
                .split("(?i)FROM ");
        return Arrays
                .stream(queryData[0].split(","))
                .map(String::trim)
                .toArray(String[]::new);
    }
}
