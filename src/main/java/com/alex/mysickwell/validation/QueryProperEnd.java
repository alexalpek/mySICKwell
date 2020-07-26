package com.alex.mysickwell.validation;

import com.alex.mysickwell.controller.advice.exception.IllegalEndOfQueryException;
import com.alex.mysickwell.controller.advice.exception.MySickWellException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QueryProperEnd extends Middleware {

    private final String endingString;

    @Override
    public boolean check(String query) throws MySickWellException {
        if (query.endsWith(endingString)) {
            return checkNext(query.substring(0, query.length() - 2));
        }
        throw new IllegalEndOfQueryException(endingString);
    }
}
