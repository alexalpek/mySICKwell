package com.alex.mysickwell.validation;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QueryProperEnd extends Middleware {

    private final String endingString;

    @Override
    public boolean check(String query) {
        System.out.println(this.getClass().getSimpleName() + ": " + query);
        if (query.endsWith(endingString)) {
            return checkNext(query.substring(0, query.length() - 2));
        }
        System.out.println(this.getClass().getSimpleName() + " returned fail for query: " + query);
        return false;
    }
}
