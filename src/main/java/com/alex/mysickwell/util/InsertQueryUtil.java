package com.alex.mysickwell.util;

import com.alex.mysickwell.controller.advice.exception.IllegalParametersInQueryException;
import com.alex.mysickwell.controller.advice.exception.MySickWellException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class InsertQueryUtil {

    public String getTableNameFromQuery(String query) {
        String queryStart = "INSERT INTO ";
        String[] list = query.split(" VALUES");
        return list[0].substring(queryStart.length()).trim();
    }

    public String[] getParametersFromValidationString(String query) {
        String parametersWithBrackets = query.split("\\(")[1].trim();
        String[] split = parametersWithBrackets
                .split(",");
        return Arrays.stream(split).map(String::trim).toArray(String[]::new);
    }

    public String[] getParametersFromQuery(String query) {
        String parametersWithBrackets = query.split("\\(")[1].trim();
        String[] split = parametersWithBrackets
                .substring(0, parametersWithBrackets.length() - 2)
                .split(",");
        return Arrays.stream(split).map(String::trim).toArray(String[]::new);
    }

    public <T> T makeParameterFromString(String valueString, Class<?> classOfColumn) throws MySickWellException {
        try {
            if (classOfColumn == Integer.class) {
                return (T) Integer.valueOf(valueString);
            } else if (classOfColumn == Boolean.class) {
                return (T) Boolean.valueOf(valueString);
            } else if (classOfColumn == String.class) {
                return (T) valueString;
            } else {
                throw new IllegalParametersInQueryException("Illegal parameter in query at: " + valueString + ". Expected type was " + classOfColumn.getSimpleName());
            }
        } catch (ClassCastException | NumberFormatException e) {
            throw new IllegalParametersInQueryException("Illegal parameter in query at: " + valueString + ". Expected type was " + classOfColumn.getSimpleName());
        }
    }
}
