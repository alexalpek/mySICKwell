package com.alex.mysickwell.selectValidation;

import com.alex.mysickwell.controller.advice.exception.IllegalParametersInQueryException;
import com.alex.mysickwell.controller.advice.exception.MySickWellException;
import com.alex.mysickwell.controller.advice.exception.QueryHasNoTableNameException;
import com.alex.mysickwell.validation.Middleware;
import com.alex.mysickwell.validation.select.SelectValidatorProvider;
import com.alex.mysickwell.validation.select.middleware.SelectHasParameters;
import com.alex.mysickwell.validation.select.middleware.SelectHasTableName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SelectValidationTest {

    @Autowired
    private SelectValidatorProvider provider;

    @Test
    void selectHasTableNameDetectsTableName() throws MySickWellException {
        Middleware middleware = new SelectHasTableName();
        String query = "SELECT * FROM asd";
        assertTrue(middleware.check(query));
    }


    @Test
    void selectHasTableNameThrowsErrorIfNoTableNameIsPresent() {
        Middleware middleware = new SelectHasTableName();
        String query = "SELECT * FROM  ";
        assertThrows(QueryHasNoTableNameException.class, () -> middleware.check(query));
    }

    @Test
    void selectHasTableNameThrowsErrorIfNoTableNameAndFromWordIsPresent() {
        Middleware middleware = new SelectHasTableName();
        String query = "SELECT * ";
        assertThrows(MySickWellException.class, () -> middleware.check(query));
    }

    @Test
    void SelectHasParametersThrowsRightError1() {
        Middleware middleware = new SelectHasParameters();
        String query = "* FROM";
        assertThrows(MySickWellException.class, () -> middleware.check(query));
    }

    @Test
    void SelectHasParametersThrowsRightError2() {
        Middleware middleware = new SelectHasParameters();
        String query = "  FROM ASD";
        assertThrows(IllegalParametersInQueryException.class, () -> middleware.check(query));
    }

    @Test
    void SelectHasParametersThrowsReturnsTrueForProperQuery() throws MySickWellException {
        Middleware middleware = new SelectHasParameters();
        String query = "name FROM ASD";
        assertTrue(middleware.check(query));
    }

    @Test
    void validatorReturnTrueForSelectAllQueryString() throws MySickWellException {
        Middleware validator = provider.getMiddleware();
        String query = "SELECT * FROM table_name;";
        assertTrue(validator.check(query));
    }

    @Test
    void validatorReturnTrueForSelectSomeColumnQueryString() throws MySickWellException {
        Middleware validator = provider.getMiddleware();
        String query = "SELECT some_column FROM table_name;";
        assertTrue(validator.check(query));
    }

}
