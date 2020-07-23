package com.alex.mysickwell.validation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QueryProperStart extends Middleware {

    private final String startingString;

    @Override
    public boolean check(String query) {
        if (query == null || query.length() <= startingString.length()) return false;

        if (query.toUpperCase().startsWith(startingString)) {
            return checkNext(query.substring(startingString.length()));
        }
        return false;
    }
}
