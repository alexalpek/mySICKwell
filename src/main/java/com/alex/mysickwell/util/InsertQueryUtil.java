package com.alex.mysickwell.util;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class InsertQueryUtil {

    public String getTableNameFromQuery(String query) {
        String queryStart = "INSERT INTO ";
        String[] list = query.split(" VALUES");
        return list[0].substring(queryStart.length()).trim();
    }

    public String[] getParametersFromQuery(String query) {
        String parametersWithBrackets = query.split(" VALUES")[1].trim();
        String[] split = parametersWithBrackets
                .substring(1, parametersWithBrackets.length() - 2)
                .split(",");
        return Arrays.stream(split).map(String::trim).toArray(String[]::new);
    }
}
