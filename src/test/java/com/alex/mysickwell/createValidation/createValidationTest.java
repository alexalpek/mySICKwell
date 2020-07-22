package com.alex.mysickwell.createValidation;

import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.validation.QueryEndsProperly;
import com.alex.mysickwell.validation.QueryProperStart;
import com.alex.mysickwell.validation.Middleware;
import com.alex.mysickwell.validation.create.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class createValidationTest {
    @Autowired
    private Database database;
    @Autowired
    private CreateTableValidatorProvider provider;

    private Middleware validator;

    private String query1 = "";
    private String query2 = null;
    private String query3 = "CREATE TABLE asd(kek VARCHAR, lol BOOLEAN, asd INTEGER);";
    private String query4 = "CREATE TABLE asd (kek, lol BOOLEAN, asd INTEGER);";
    private String query9 = "CREATE TABLE asd (kek INTEGER, lol NOTSUPPORTED, asd INTEGER);";
    private String query5 = "CREATE TABLE (kek INTEGER, lol BOOLEAN, asd INTEGER);";
    private String query6 = "cReaTe tAblE (););";
    private String query7 = "asd (";

    private String fanniQuery = "create table asd(); INTEGER kek, ););";


    @BeforeEach
    void init(){
        validator = provider.getMiddleware();
    }

    @Test
    void createTableParametersMiddlewareWorks() {
        Middleware middleware = new CreateTableHasParameters();
        String query = "CREATE TABLE asd (kek VARCHAR, lol BOOLEAN, asd INTEGER";
        assertTrue(middleware.check(query));
    }

    @Test
    void createProperStartWorks() {
        Middleware middleware = new QueryProperStart("CREATE TABLE ");
        assertTrue(middleware.check(query3));
    }

    @Test
    void createTableNameWorks() {
        Middleware middleware = new CreateTableName(database);
        assertTrue(middleware.check(query3));
    }

    @Test
    void createEndsProperlyWorks() {
        Middleware middleware = new QueryEndsProperly(");");
        assertTrue(middleware.check(query3));
    }

    @Test
    void emptyStringFails() {
        assertFalse(validator.check(query1));
    }

    @Test
    void nullFails() {
        assertFalse(validator.check(query2));
    }

    @Test
    void properQueryPasses() {
        assertTrue(validator.check(query3));
    }

    @Test
    void allQueryParametersHasToHaveTwoParts() {
        assertFalse(validator.check(query4));
    }

    @Test
    void tableNameHasToBeThere() {
        assertFalse(validator.check(query5));
    }

    @Test
    void multipleBracketsFails() {
        assertFalse(validator.check(query6));
    }

    @Test
    void noParametersFails() {
        assertFalse(validator.check(query7));
    }

    @Test
    void fanniTriesToBreakItButFailsHopefully() {
        assertFalse(validator.check(fanniQuery));
    }

    @Test
    void notSupportedQueryParameterTypeFails() {
        assertFalse(validator.check(query9));
    }

}
