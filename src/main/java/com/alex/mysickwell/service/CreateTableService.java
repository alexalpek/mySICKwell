package com.alex.mysickwell.service;

import com.alex.mysickwell.model.Column;
import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.util.CreateQueryUtil;
import com.alex.mysickwell.validation.Middleware;
import com.alex.mysickwell.validation.create.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;

@Slf4j
@Service
public class CreateTableService {

    private final Logger logger = LoggerFactory.getLogger(CreateTableService.class);
    private final CreateQueryUtil util;
    private Database database;
    private Middleware validator;


    public ResponseEntity<?> createTable(String query){
        if (!validator.check(query)) {
            logger.info("Got malformed query: " + query);
            return new ResponseEntity<>("Query is malformed.",HttpStatus.BAD_REQUEST);
        }
        String tableName = util.getTableNameFromQuery(query);
        HashMap<Column, LinkedList<?>> table = util.getTableColumnsFromQuery(query);
        database.createTable(tableName, table);
        logger.info("Table created with name of " + tableName + " with data of " + table);
        return new ResponseEntity<>("Table created with name of " + tableName, HttpStatus.OK);
    }

    @Autowired
    public CreateTableService(CreateQueryUtil util, Database database, CreateTableValidatorProvider provider) {
        this.util = util;
        this.database = database;
        validator = provider.getMiddleware();
    }
}

