package com.alex.mysickwell.validation.insert.middleware;

import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.validation.Middleware;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InsertValidTableName extends Middleware {

    private final Database database;

    @Override
    public boolean check(String query) {
        String[] split = query.split(" VALUES");
        if (split.length < 2) return false;
        if (database.getTables().containsKey(split[0])) {
            return checkNext(split[1].trim());
        }
        System.out.println(this.getClass().getSimpleName() + " returned fail");
        return false;
    }


}
