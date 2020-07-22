package com.alex.mysickwell.validation;

import com.alex.mysickwell.validation.Middleware;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QueryProperStart extends Middleware {

    private final String startingString;

    @Override
    public boolean check(String query) {
        String start = "CREATE TABLE ";
        if (query == null || query.length() <= start.length()) return false;

        if (query.toUpperCase().startsWith(start)){
            return checkNext(query.substring(start.length()));
        }
        return false;
    }
}
