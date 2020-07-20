package com.alex.mysickwell.validation.create;

import com.alex.mysickwell.validation.Middleware;


public class CreateProperStart extends Middleware {

    @Override
    public boolean check(String query) {
        String start = "CREATE TABLE ";
        if (query == null || query.length() <= start.length()) return false;

        if (query.toUpperCase().startsWith(start)){
            return checkNext(query);
        }
        return false;
    }
}
