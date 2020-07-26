package com.alex.mysickwell.validation.insert.middleware;

import com.alex.mysickwell.controller.advice.exception.MySickWellException;
import com.alex.mysickwell.controller.advice.exception.QueryHasNoTableNameException;
import com.alex.mysickwell.controller.advice.exception.TableDoesNotExistException;
import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.validation.Middleware;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public class InsertValidTableName extends Middleware {

    private final Database database;

    @Override
    public boolean check(String query) throws MySickWellException {
        System.out.println(this.getClass().getSimpleName() + ": " + query);
        String[] split = query.split(" VALUES");
        if (split.length < 2) throw new QueryHasNoTableNameException("Query has no table name: " + query);
        if (database.getTables().containsKey(split[0])) {
            String[] result = Arrays
                    .stream(split)
                    .map(String::trim)
                    .toArray(String[]::new);
            return checkNext(String.join(" ", result));
        }
        System.out.println(this.getClass().getSimpleName() + " returned fail for query: " + query);
        throw new TableDoesNotExistException("Table does not exist: " + split[0]);
    }


}
