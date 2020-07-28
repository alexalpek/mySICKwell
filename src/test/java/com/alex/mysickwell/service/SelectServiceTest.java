package com.alex.mysickwell.service;

import com.alex.mysickwell.controller.advice.exception.MySickWellException;
import com.alex.mysickwell.controller.advice.exception.TableDoesNotExistException;
import com.alex.mysickwell.model.Column;
import com.alex.mysickwell.model.ColumnType;
import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.model.Table;
import com.alex.mysickwell.validation.Middleware;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    private Map<Column, LinkedList<?>> mockedTable;

    @BeforeEach
    void init() {
        mockedTable = new LinkedHashMap<>();
        Column col1 = Column.builder()
                .name("column")
                .type(ColumnType.VARCHAR)
                .build();
        Column col2 = Column.builder()
                .name("column2")
                .type(ColumnType.INTEGER)
                .build();
        mockedTable.put(col1, new LinkedList<>());
        mockedTable.put(col2, new LinkedList<>());
    }

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

    @Test
    void selectTableTestForSelectAll() throws MySickWellException {
        Mockito.when(database.getTable("table")).thenReturn(new Table(mockedTable));
        assertEquals(new Table(mockedTable).toString(), service.selectTable(queryAll).toString());
    }

    @Test
    void selectTableTestForSelectColumn() throws MySickWellException {
        Map<Column, LinkedList<?>> table = new LinkedHashMap<>();
        Column col1 = Column.builder()
                .name("column")
                .type(ColumnType.VARCHAR)
                .build();
        table.put(col1, new LinkedList<>());
        Mockito.when(database.getTable("table")).thenReturn(new Table(mockedTable));
        assertEquals(new Table(table).toString(), service.selectTable(querySome).toString());
    }
}
