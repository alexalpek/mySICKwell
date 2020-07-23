package com.alex.mysickwell.database;

import com.alex.mysickwell.model.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DatabaseTests {

    private Database database;

    @BeforeEach
    void init() {
        database = new Database();
    }

    @Test
    void databaseCanCreateNewTables() {
        assertFalse(database.getTables().containsKey("TableName"));
        database.createTable("TableName", null);
        assertTrue(database.getTables().containsKey("TableName"));
    }

}
