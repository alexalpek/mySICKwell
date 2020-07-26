package com.alex.mysickwell.service;

import com.alex.mysickwell.controller.advice.exception.MySickWellException;
import com.alex.mysickwell.model.Database;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CreateTableServiceTest {

    @Autowired
    private Database database;

    @Autowired
    private CreateTableService service;

    @Test
    void createTableMethodCreatesTable() throws MySickWellException {
        assertEquals(0, database.getTables().size());
        service.createTable("CREATE TABLE person (age INTEGER, name VARCHAR);");
        assertEquals(1, database.getTables().size());
    }

    @Test
    void createTableThrowsErrorToIncorrectQuery(){
        assertThrows(MySickWellException.class, ()->
                service.createTable("KRIÉT TABLE person (age INTEGER, name VARCHAR);"));
    }

}
