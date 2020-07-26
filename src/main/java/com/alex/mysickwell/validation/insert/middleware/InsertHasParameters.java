package com.alex.mysickwell.validation.insert.middleware;

import com.alex.mysickwell.controller.advice.exception.MySickWellException;
import com.alex.mysickwell.controller.advice.exception.QueryHasMalformedParametersException;
import com.alex.mysickwell.validation.Middleware;

import java.util.Arrays;

public class InsertHasParameters extends Middleware {
    @Override
    public boolean check(String query) throws MySickWellException {
        String[] array = query.split("\\(");
        if (array.length != 2)
            throw new QueryHasMalformedParametersException();
        String parameters = array[1].trim();
        if (Arrays.stream(parameters.split(","))
                .map(String::trim)
                .map(p -> p.split(" "))
                .allMatch(a -> a.length == 1)) {
            return checkNext(query);
        }
        throw new QueryHasMalformedParametersException();
    }
}
