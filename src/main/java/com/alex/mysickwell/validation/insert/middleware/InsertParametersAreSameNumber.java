package com.alex.mysickwell.validation.insert.middleware;

import com.alex.mysickwell.controller.advice.exception.IllegalParametersInQueryException;
import com.alex.mysickwell.controller.advice.exception.MySickWellException;
import com.alex.mysickwell.controller.advice.exception.TableDoesNotExistException;
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
    public boolean check(String query) throws MySickWellException {
        String[] data = query.split(" \\(");
        String tableName = data[0];
        Table table = database.getTable(tableName);
        if (table == null) {
            System.out.println(this.getClass().getSimpleName() + " returned fail for database being null");
            throw new TableDoesNotExistException(tableName);
        }
        int dataSize = table.getData().size();
        int parametersSize = util.getParametersFromValidationString(query).length;
        if (dataSize == parametersSize) {
            return checkNext(query);
        }
        throw new IllegalParametersInQueryException("Parameters number are not equal to column number!\nRequired: " + dataSize + " Provided: " + parametersSize);
    }
}
