package com.alex.mysickwell.validation;

import com.alex.mysickwell.validation.Middleware;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QueryEndsProperly extends Middleware {

    private final String endingString;

    @Override
    public boolean check(String query) {
        if (query.endsWith(endingString)){
            return checkNext(query.substring(0,query.length()-2));
        }
        return false;
    }
}
