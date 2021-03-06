package com.alex.mysickwell.createValidation;

import com.alex.mysickwell.controller.advice.exception.*;
import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.validation.Middleware;
import com.alex.mysickwell.validation.create.CreateTableValidatorProvider;
import com.alex.mysickwell.validation.create.middleware.CreateTableHasParameters;
import com.alex.mysickwell.validation.create.middleware.CreateTableName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CreateValidationTest {
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
    void createTableParametersMiddlewareWorks() throws MySickWellException {
        Middleware middleware = new CreateTableHasParameters();
        String query = "CREATE TABLE asd (kek VARCHAR, lol BOOLEAN, asd INTEGER";
        assertTrue(middleware.check(query));
    }


    @Test
    void createTableNameWorks() throws MySickWellException {
        Middleware middleware = new CreateTableName(database);
        assertTrue(middleware.check(query3));
    }

    @Test
    void emptyStringFails() {
        assertThrows(IllegalQueryStartException.class, () -> validator.check(query1));
    }

    @Test
    void nullFails() {
        assertThrows(IllegalQueryStartException.class, () -> validator.check(query2));
    }

    @Test
    void properQueryPasses() throws MySickWellException {
        assertTrue(validator.check(query3));
    }

    @Test
    void allQueryParametersHasToHaveTwoParts() {
        assertThrows(QueryHasMalformedParametersException.class, () -> validator.check(query4));

    }

    @Test
    void tableNameHasToBeThere(){
        assertThrows(QueryHasNoTableNameException.class, () -> validator.check(query5));
    }

    @Test
    void multipleBracketsFails(){
        assertThrows(QueryHasNoTableNameException.class, () -> validator.check(query6));
    }

    @Test
    void noParametersFails() {
        assertThrows(IllegalQueryStartException.class, () -> validator.check(query7));
    }

    @Test
    void fanniTriesToBreakItButFailsHopefully() {
        assertThrows(MySickWellException.class, () -> validator.check(fanniQuery));
    }

    @Test
    void notSupportedQueryParameterTypeFails(){
        assertThrows(IllegalParametersInQueryException.class, () -> validator.check(query9));
    }

}
