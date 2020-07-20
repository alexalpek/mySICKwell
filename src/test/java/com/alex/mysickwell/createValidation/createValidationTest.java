package com.alex.mysickwell.createValidation;

import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.validation.Middleware;
import com.alex.mysickwell.validation.create.CreateEndsProperly;
import com.alex.mysickwell.validation.create.CreateProperStart;
import com.alex.mysickwell.validation.create.CreateTableName;
import com.alex.mysickwell.validation.create.CreateTableParameters;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class createValidationTest {

    private Middleware validator = new CreateProperStart();

    @Autowired
    private Database database;

    private String query1 = "";
    private String query2 = null;
    private String query3 = "CREATE TABLE asd (kek VARCHAR, lol BOOLEAN, asd INTEGER);";
    private String query4 = "CREATE TABLE asd (kek, lol BOOLEAN, asd INTEGER);";
    private String query5 = "CREATE TABLE (kek INTEGER, lol BOOLEAN, asd INTEGER);";
    private String query6 = "cReaTe tAblE (););";



    @BeforeEach
    void init(){
        validator
                .linkWith(new CreateTableName(database))
                .linkWith(new CreateEndsProperly())
                .linkWith(new CreateTableParameters());
    }

    @Test
    void createTableParametersMiddlewareWorks(){
        Middleware middleware = new CreateTableParameters();
        String query = "CREATE TABLE asd (kek VARCHAR, lol BOOLEAN, asd INTEGER";
        assertTrue(middleware.check(query));
    }

    @Test
    void createProperStartWorks(){
        Middleware middleware = new CreateProperStart();
        assertTrue(middleware.check(query3));
    }

    @Test
    void createTableNameWorks(){
        Middleware middleware = new CreateTableName(database);
        assertTrue(middleware.check(query3));
    }

    @Test
    void createEndsProperlyWorks(){
        Middleware middleware = new CreateEndsProperly();
        assertTrue(middleware.check(query3));
    }

    @Test
    void testQuery1(){
        assertFalse(validator.check(query1));
    }

    @Test
    void testQuery2(){
        assertFalse(validator.check(query2));
    }

    @Test
    void testQuery3(){
        assertTrue(validator.check(query3));
    }

    @Test
    void testQuery4(){
        assertFalse(validator.check(query4));
    }

    @Test
    void testQuery5(){
        assertFalse(validator.check(query5));
    }

    @Test
    void testQuery6(){
        assertFalse(validator.check(query6));
    }



}
