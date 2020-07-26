package com.alex.mysickwell.service;

import com.alex.mysickwell.controller.advice.exception.IllegalParametersInQueryException;
import com.alex.mysickwell.controller.advice.exception.MySickWellException;
import com.alex.mysickwell.model.Column;
import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.model.Table;
import com.alex.mysickwell.util.InsertQueryUtil;
import com.alex.mysickwell.validation.Middleware;
import com.alex.mysickwell.validation.insert.InsertValidatorProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

@Service
public class InsertService {

    private final Logger logger = LoggerFactory.getLogger(CreateTableService.class);
    private final InsertQueryUtil util;
    private final Database database;
    private final Middleware validator;

    @Autowired
    public InsertService(InsertQueryUtil util, Database database, InsertValidatorProvider provider) {
        this.util = util;
        this.database = database;
        this.validator = provider.getMiddleware();
    }

    public Integer insertToTable(String query) throws MySickWellException {
        if (validator.check(query)) {
            String tableName = util.getTableNameFromQuery(query);
            return insertValuesFromQueryToTable(tableName, query);
        }
        return 0;
    }

    public Integer insertValuesFromQueryToTable(String name, String query) throws MySickWellException {
        Table currentTable = database.getTable(name);

        //TODO: implement failsafe. Columns still refers to the same memory place. cloning?
        Map<Column, LinkedList<?>> newData = new HashMap<>(currentTable.getData());
        Table tableBeforeChange = new Table(newData);

        String[] parametersString = util.getParametersFromQuery(query);
        try {
            Map<Column, LinkedList<?>> data = currentTable.getData();
            Iterator it = data.entrySet().iterator();
            int index = 0;
            while (it.hasNext()) {
                Boolean asd = currentTable.equals(tableBeforeChange);
                Map.Entry<Column, LinkedList<?>> pair = (Map.Entry) it.next();

                Column column = pair.getKey();
                LinkedList dataEntries = pair.getValue();
                Class<?> classOfColumn = column.getType().getDatatype();

                String valueString = parametersString[index];
                addHelper(dataEntries, util.makeParameterFromString(valueString, classOfColumn));
                index++;
            }
            logger.info(currentTable.toString());
            return parametersString.length;
        } catch (IllegalParametersInQueryException e) {
            database.setTable(name, tableBeforeChange); //TODO: memento pattern refactor BEFORE update and delete.
            throw new IllegalParametersInQueryException(e.getMessage());
        }
    }

    public <V> void addHelper(LinkedList<V> linkedList, V parameter) {
        linkedList.add(parameter);
    }
}
