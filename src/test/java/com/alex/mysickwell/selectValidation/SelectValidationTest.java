package com.alex.mysickwell.selectValidation;

import com.alex.mysickwell.controller.advice.exception.MySickWellException;
import com.alex.mysickwell.controller.advice.exception.QueryHasNoTableNameException;
import com.alex.mysickwell.validation.Middleware;
import com.alex.mysickwell.validation.select.middleware.SelectHasTableName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SelectValidationTest {

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


}
