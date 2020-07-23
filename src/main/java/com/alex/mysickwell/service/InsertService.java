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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<?> insertToTable(String query) {
        if (!validator.check(query)) {
            logger.info("Got malformed query: " + query);
            return new ResponseEntity<>("Query is malformed.", HttpStatus.BAD_REQUEST);
        }
        String tableName = util.getTableNameFromQuery(query);
        return insertValuesFromQueryToTable(tableName, query);

    }

    public ResponseEntity<?> insertValuesFromQueryToTable(String name, String query) {
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
                LinkedList<?> dataEntries = pair.getValue();
                //dataEntries.add(classOfColumn.cast(parametersString[index])); //TODO: capture of ? wildcard bug resolve.
                index++;
            }
            return new ResponseEntity<>("Query insertion was successful", HttpStatus.CREATED);
        } catch (ClassCastException e) {
            database.setTable(name, currentTable);
            return new ResponseEntity<>("Parameter type mismatch" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
