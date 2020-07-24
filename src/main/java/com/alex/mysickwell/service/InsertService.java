package com.alex.mysickwell.service;

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

    public Integer insertToTable(String query) throws Exception {
        if (!validator.check(query)) {
            logger.info("Got malformed query: " + query);
            throw new Exception();
        }
        String tableName = util.getTableNameFromQuery(query);
        return insertValuesFromQueryToTable(tableName, query);
    }

    public Integer insertValuesFromQueryToTable(String name, String query) throws Exception { //TODO: controlleradvice!!
        Table currentTable = database.getTable(name);
        String[] parametersString = util.getParametersFromQuery(query);
        try {
            Map<Column, LinkedList<?>> data = currentTable.getData();
            Iterator it = data.entrySet().iterator();
            int index = 0;
            while (it.hasNext()) {
                Map.Entry<Column, LinkedList<?>> pair = (Map.Entry) it.next();
                Column column = pair.getKey();
                Class<?> classOfColumn = column.getType().getDatatype();
                LinkedList dataEntries = pair.getValue();

                //addHelper(dataEntries, (classOfColumn.cast(parametersString[index])));
                dataEntries.add(classOfColumn.cast(parametersString[index])); //TODO: capture of ? wildcard bug resolve.

                index++;
            }
            logger.info(currentTable.toString());
            return parametersString.length;
        } catch (ClassCastException e) {
            database.setTable(name, currentTable); //TODO: memento pattern refactor BEFORE update and delete.
            throw new Exception(); //TODO: own sqlexception and subclasses
        }
    }

    private <V> void addHelper(LinkedList<V> linkedList, V parameter) {
        linkedList.add(parameter);
    }
}
