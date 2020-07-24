package com.alex.mysickwell.validation.insert.middleware;

import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.validation.Middleware;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public class InsertValidTableName extends Middleware {

    private final Database database;

    @Override
    public boolean check(String query) {
        System.out.println(this.getClass().getSimpleName() + ": " + query);
        String[] split = query.split(" VALUES");
        if (split.length < 2) return false;
        if (database.getTables().containsKey(split[0])) {
            String[] result = Arrays
                    .stream(split)
                    .map(String::trim)
                    .toArray(String[]::new);
            return checkNext(String.join(" ", result));
        }
        System.out.println(this.getClass().getSimpleName() + " returned fail for query: " + query);
        return false;
    }


}
