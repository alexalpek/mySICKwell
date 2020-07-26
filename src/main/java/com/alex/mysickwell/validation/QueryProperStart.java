package com.alex.mysickwell.validation;
import com.alex.mysickwell.controller.advice.exception.IllegalQueryStartException;
import com.alex.mysickwell.controller.advice.exception.MySickWellException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QueryProperStart extends Middleware {

    private String startingString;

    @Override
    public boolean check(String query) throws MySickWellException {
        if (query == null || query.length() <= startingString.length()) throw new IllegalQueryStartException("Illegal start of query: " + query);

        if (query.toUpperCase().startsWith(startingString)) {
            return checkNext(query.substring(startingString.length()));
        }
        throw new IllegalQueryStartException("Illegal start of query: " + query);
    }
}
