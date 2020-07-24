package com.alex.mysickwell.validation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QueryProperStart extends Middleware {

    private String startingString;

    @Override
    public boolean check(String query) {
        System.out.println(this.getClass().getSimpleName() + ": " + query);
        if (query == null || query.length() <= startingString.length()) return false;

        if (query.toUpperCase().startsWith(startingString)) {
            return checkNext(query.substring(startingString.length()));
        }
        System.out.println(this.getClass().getSimpleName() + " returned fail for query: " + query);
        return false;
    }
}
