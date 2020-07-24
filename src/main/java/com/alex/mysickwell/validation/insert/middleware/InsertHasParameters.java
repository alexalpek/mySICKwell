package com.alex.mysickwell.validation.insert.middleware;

import com.alex.mysickwell.validation.Middleware;

import java.util.Arrays;

public class InsertHasParameters extends Middleware {
    @Override
    public boolean check(String query) {
        System.out.println(this.getClass().getSimpleName() + ": " + query);
        String[] array = query.split("\\(");
        if (array.length != 2) return false;
        String parameters = array[1].trim();
        if (Arrays.stream(parameters.split(","))
                .map(String::trim)
                .map(p -> p.split(" "))
                .allMatch(a -> a.length == 1)) {
            return checkNext(query);
        }
        System.out.println(this.getClass().getSimpleName() + " returned fail for query: " + query);
        return false;
    }
}
