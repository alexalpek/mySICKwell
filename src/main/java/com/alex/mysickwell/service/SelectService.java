package com.alex.mysickwell.service;

import com.alex.mysickwell.controller.advice.exception.MySickWellException;
import com.alex.mysickwell.controller.advice.exception.TableDoesNotExistException;
import com.alex.mysickwell.model.Column;
import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.model.Table;
import com.alex.mysickwell.util.SelectQueryUtil;
import com.alex.mysickwell.validation.Middleware;
import com.alex.mysickwell.validation.select.SelectValidatorProvider;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class SelectService {

    private final Database database;
    private final Middleware validator;
    private final SelectQueryUtil util;
    private Logger logger = LoggerFactory.getLogger(SelectService.class);

    @Autowired
    public SelectService(Database database, SelectValidatorProvider provider, SelectQueryUtil util) {
        this.database = database;
        this.validator = provider.getMiddleware();
        this.util = util;
    }

    public Table selectTable(String query) throws MySickWellException {
        if (validator.check(query)) {
            String tableName = util.getTableNameFromQuery(query);
            logger.info(database.getTables().toString());
            Table table = database.getTable(tableName);
            if (table == null)
                throw new TableDoesNotExistException(tableName);
            List<String> parameters = Arrays.asList(util.getParametersFromQuery(query));
            if (parameters.size() == 1 && parameters.get(0).equals("*")) {
                return table;
            }
            Map<Column, LinkedList<?>> result = new LinkedHashMap<>();
            Iterator<Map.Entry<Column, LinkedList<?>>> iterator = database.getTable(tableName).getData().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Column, LinkedList<?>> pair = (Map.Entry) iterator.next();
                Column column = pair.getKey();
                LinkedList dataEntries = pair.getValue();
                if (parameters.contains(column.getName())) {
                    result.put(column, dataEntries);
                }
            }
            return new Table(result);
        }
        return null;
    }
}
