package com.alex.mysickwell.validation.insert.middleware;

import com.alex.mysickwell.validation.Middleware;

public class InsertHasCorrectParameterTypes extends Middleware {
    @Override
    public boolean check(String query) {
        System.out.println(this.getClass().getSimpleName() + " returned fail");
        return false;
    }
}
