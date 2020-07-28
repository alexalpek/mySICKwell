package com.alex.mysickwell.service;

import com.alex.mysickwell.controller.advice.exception.MySickWellException;
import com.alex.mysickwell.controller.advice.exception.TableDoesNotExistException;
import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.validation.Middleware;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SelectServiceTest {

    @MockBean
    private Database database;
    @MockBean
    private Middleware validator;
    @Autowired
    private SelectService service;

    private String queryAll = "SELECT * FROM table;";
    private String querySome = "SELECT column FROM table;";

    @Test
    void selectTableMethodThrowsRightErrorValidatorReturnTrue() throws MySickWellException {
        Mockito.when(validator.check(queryAll)).thenReturn(true);
        assertThrows(TableDoesNotExistException.class,
                () -> service.selectTable(queryAll));
    }

    @Test
    void selectTableMethodThrowsRightErrorIfValidatorReturnFalse() throws MySickWellException {
        Mockito.when(validator.check(queryAll)).thenReturn(true);
        assertThrows(MySickWellException.class,
                () -> service.selectTable(queryAll));
    }
}
