package com.alex.mysickwell.validation.create;

import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.validation.Middleware;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTableName extends Middleware {

    private final Database database;

    @Override
    public boolean check(String query) {
        String[] list = query.split(" ");
        if (list.length < 3 || !list[3].startsWith("(")) return false; //TODO: define more in-depth condition -- see query6 testcase, ALSO make it more like dynamic programming, see createEndsProperly return.

        System.out.println("list 2 is "+list[2]);

        if (!database.getTables().containsKey(list[2])){
            return checkNext(query);
        }
        return false;
    }
}
