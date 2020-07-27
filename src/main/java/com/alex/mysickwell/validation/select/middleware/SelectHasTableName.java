package com.alex.mysickwell.validation.select.middleware;

import com.alex.mysickwell.controller.advice.exception.MySickWellException;
import com.alex.mysickwell.controller.advice.exception.QueryHasNoTableNameException;
import com.alex.mysickwell.validation.Middleware;

import java.util.Arrays;

public class SelectHasTableName extends Middleware {
    @Override
    public boolean check(String query) throws MySickWellException {

        String[] queryParts = Arrays
                .stream(query.split("(?i)FROM "))
                .map(String::trim)
                .toArray(String[]::new);
        if (queryParts.length < 2)
            throw new MySickWellException("Malformed query.");
        if (!queryParts[1].trim().equals("")) {
            return checkNext(query);
        }
        throw new QueryHasNoTableNameException();
    }
}
