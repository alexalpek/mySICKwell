package com.alex.mysickwell.validation.create;

import com.alex.mysickwell.validation.Middleware;

public class CreateEndsProperly extends Middleware {
    @Override
    public boolean check(String query) {
        if (query.endsWith(");")){
            return checkNext(query.substring(0,query.length()-2));
        }
        return false;
    }
}
