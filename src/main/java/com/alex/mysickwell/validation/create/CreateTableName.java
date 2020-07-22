package com.alex.mysickwell.validation.create;

import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.validation.Middleware;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTableName extends Middleware {

    private final Database database;

    @Override
    public boolean check(String query) {
        String[] list = query.trim().split("\\(");
        if (list.length < 2 || query.startsWith("(") || list[0].equals("")) return false;
        if (!database.getTables().containsKey(list[0])){
            return checkNext(query);
        }
        return false;
    }
}
