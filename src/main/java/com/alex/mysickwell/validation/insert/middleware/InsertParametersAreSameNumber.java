package com.alex.mysickwell.validation.insert.middleware;

import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.model.Table;
import com.alex.mysickwell.util.InsertQueryUtil;
import com.alex.mysickwell.validation.Middleware;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InsertParametersAreSameNumber extends Middleware {

    /**
     * At this point a proper query will look like this: "table_name (value1, value2, ..., valueN"
     */

    private Database database;
    private InsertQueryUtil util;

    @Override
    public boolean check(String query) {
        System.out.println(this.getClass().getSimpleName() + ": " + query);
        String[] data = query.split(" \\(");
        String tableName = data[0];
        Table table = database.getTable(tableName);
        if (table == null) {
            System.out.println(this.getClass().getSimpleName() + " returned fail for database being null");
            return false;
        }
        if (table.getData().size() == util.getParametersFromValidationString(query).length) {
            return checkNext(query);
        }
        System.out.println(this.getClass().getSimpleName() + " returned fail for query: " + query);
        return false;
    }
}
