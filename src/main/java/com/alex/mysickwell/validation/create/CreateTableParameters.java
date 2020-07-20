package com.alex.mysickwell.validation.create;

import com.alex.mysickwell.validation.Middleware;

import java.util.Arrays;


public class CreateTableParameters extends Middleware {

    private String[] acceptableParameters = new String[]{"VARCHAR", "INTEGER", "BOOLEAN"};

    @Override
    public boolean check(String query) {
        String[] array = query.split("\\(");
        if (array.length != 2) return false;
        String remaining = array[1];
        if(Arrays.stream(remaining.split(","))
                .map(String::trim)
                .map(p->p.split(" "))
                .allMatch(list -> list.length == 2)) {
            return checkNext(query);
        }

        return false;
    }


}
