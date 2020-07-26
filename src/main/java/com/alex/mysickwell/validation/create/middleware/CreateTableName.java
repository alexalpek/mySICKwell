package com.alex.mysickwell.validation.create.middleware;

import com.alex.mysickwell.controller.advice.exception.MySickWellException;
import com.alex.mysickwell.controller.advice.exception.QueryHasNoTableNameException;
import com.alex.mysickwell.controller.advice.exception.TableAlreadyExistsException;
import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.validation.Middleware;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTableName extends Middleware {

    private final Database database;

    @Override
    public boolean check(String query) throws MySickWellException {
        String[] list = query.trim().split("\\(");
        if (list.length < 2 || query.startsWith("(") || list[0].equals(""))
            throw new QueryHasNoTableNameException();
        if (!database.getTables().containsKey(list[0])){
            return checkNext(query);
        }
        throw new TableAlreadyExistsException("Table already exists: " + list[0]);
    }
}
