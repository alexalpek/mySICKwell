package com.alex.mysickwell.validation.select.middleware;

import com.alex.mysickwell.controller.advice.exception.IllegalParametersInQueryException;
import com.alex.mysickwell.controller.advice.exception.MySickWellException;
import com.alex.mysickwell.validation.Middleware;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public class SelectHasParameters extends Middleware {

    @Override
    public boolean check(String query) throws MySickWellException {
        String[] queryParts = Arrays
                .stream(query.split("(?i)FROM "))
                .map(String::trim)
                .toArray(String[]::new);
        if (queryParts.length < 2)
            throw new MySickWellException("Malformed query.");
        String parametersString = queryParts[0].trim();
        if (!parametersString.equals("")) {
            return checkNext(query);
        }
        throw new IllegalParametersInQueryException("Query has no parameters");
    }
}
