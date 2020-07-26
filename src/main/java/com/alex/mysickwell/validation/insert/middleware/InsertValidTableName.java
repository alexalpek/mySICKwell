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
        String[] split = query.split(" VALUES");
        if (split.length < 2) throw new QueryHasNoTableNameException();
        if (database.getTables().containsKey(split[0])) {
            String[] result = Arrays
                    .stream(split)
                    .map(String::trim)
                    .toArray(String[]::new);
            return checkNext(String.join(" ", result));
        }
        throw new TableDoesNotExistException(split[0]);
    }


}
