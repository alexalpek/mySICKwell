package com.alex.mysickwell.validation.create.middleware;

import com.alex.mysickwell.validation.Middleware;

import java.util.Arrays;

public class CreateTableHasParameters extends Middleware {

    @Override
    public boolean check(String query) {
        String[] array = query.split("\\(");
        if (array.length != 2) return false;
        String remaining = array[1];
        if (Arrays.stream(remaining.split(","))
                .map(String::trim)
                .map(p -> p.split(" "))
                .allMatch(a -> a.length == 2)) {
            return checkNext(query);
        }

        return false;
    }


}
