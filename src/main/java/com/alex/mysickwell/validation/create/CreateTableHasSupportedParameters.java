package com.alex.mysickwell.validation.create;

import com.alex.mysickwell.validation.Middleware;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateTableHasSupportedParameters extends Middleware {

    //TODO: refactor because of terrible time complexity
    private List<String> acceptableParameters = new ArrayList<>(Arrays.asList("VARCHAR", "INTEGER", "BOOLEAN"));

    @Override
    public boolean check(String query) {
        String[] split = query.split("\\(");
        if (split.length<2) return false;
        String[][] arrayOfArrays = Arrays.stream(split[1].split(","))
                .map(String::trim)
                .map(p -> p.split(" "))
                .toArray(String[][]::new);

        if(Arrays.stream(arrayOfArrays).allMatch(a->acceptableParameters.contains(a[1]))){
            return checkNext(query);
        }

        return false;
    }
}