package com.alex.mysickwell.validation.insert.middleware;

import com.alex.mysickwell.controller.advice.exception.IllegalParametersInQueryException;
import com.alex.mysickwell.controller.advice.exception.MySickWellException;
import com.alex.mysickwell.model.Column;
import com.alex.mysickwell.model.ColumnType;
import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.model.Table;
import com.alex.mysickwell.util.InsertQueryUtil;
import com.alex.mysickwell.validation.Middleware;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

@AllArgsConstructor
public class InsertParametersHasTheRightDataType extends Middleware {

    private final Database database;
    private InsertQueryUtil util;

    @Override
    public boolean check(String query) throws MySickWellException {
        String[] queryParts = Arrays.stream(query.split("\\(")).map(String::trim).toArray(String[]::new);
        String tableName = queryParts[0];
        String[] parametersString = Arrays.stream(queryParts[1].split(",")).map(String::trim).toArray(String[]::new);
        Table currentTable = database.getTable(tableName);

        Map<Column, LinkedList<?>> data = currentTable.getData();
        Iterator it = data.entrySet().iterator();
        int index = 0;
        while (it.hasNext()) {
            Map.Entry<Column, LinkedList<?>> pair = (Map.Entry) it.next();

            Column column = pair.getKey();
            ColumnType classOfColumn = column.getType();
            String valueString = parametersString[index];
            try {
                if (classOfColumn.getDatatype().equals(classOfColumn.convert(valueString).getClass())) {
                    index++;
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalParametersInQueryException("Illegal parameter in query at: " + valueString + ". Expected type was " + classOfColumn.getDatatype().getSimpleName());
            }
        }
        return checkNext(query);
    }
}

