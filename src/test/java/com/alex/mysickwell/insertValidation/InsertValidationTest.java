package com.alex.mysickwell.insertValidation;

import com.alex.mysickwell.model.Column;
import com.alex.mysickwell.model.ColumnType;
import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.model.Table;
import com.alex.mysickwell.util.InsertQueryUtil;
import com.alex.mysickwell.validation.Middleware;
import com.alex.mysickwell.validation.QueryProperEnd;
import com.alex.mysickwell.validation.QueryProperStart;
import com.alex.mysickwell.validation.insert.middleware.InsertHasParameters;
import com.alex.mysickwell.validation.insert.middleware.InsertParametersAreSameNumber;
import com.alex.mysickwell.validation.insert.middleware.InsertQueryHasTableName;
import com.alex.mysickwell.validation.insert.middleware.InsertValidTableName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class InsertValidationTest {

    private final Middleware validator = new QueryProperStart("INSERT INTO ");
    private final String properString = "INSERT INTO table_name VALUES (value1, value2, value3);";
    @MockBean
    private Database database;
    @MockBean
    private InsertQueryUtil util;

    @BeforeEach
    void init() {
        this.validator
                .linkWith(new InsertQueryHasTableName())
                .linkWith(new InsertValidTableName(database))
                .linkWith(new QueryProperEnd(");"))
                .linkWith(new InsertHasParameters())
                .linkWith(new InsertParametersAreSameNumber(database, util));
    }

    @Test
    void emptyStringFails() {
        String emptyString = "";
        assertFalse(validator.check(emptyString));
    }

    @Test
    void nullFails() {
        assertFalse(validator.check(null));
    }

    @Test
    void InsertHasParametersMiddlewareWorks() {
        Middleware middleware = new InsertHasParameters();
        assertTrue(middleware.check(properString));
    }

    @Test
    void InsertHasParametersMiddlewareDetectsIfParameterIsNotOneWord() {
        Middleware middleware = new InsertHasParameters();
        String query = "INSERT INTO table_name VALUES (value1 INTEGER, value2, value3);";
        assertFalse(middleware.check(query));
    }

    @Test
    void InsertQueryHasTableNameWorks() {
        Middleware middleware = new InsertQueryHasTableName();
        String start = "INSERT INTO ";
        assertTrue(middleware.check(properString.substring(start.length(),
                properString.length() - 2)));
    }

    @Test
    void validatorWorksOnProperString() {
        HashMap<Column, LinkedList<?>> columnLinkedListHashMap = new HashMap<>();
        columnLinkedListHashMap.put(Column.builder()
                .type(ColumnType.VARCHAR)
                .name("Column1")
                .notNull(false)
                .build(), new LinkedList<>());
        columnLinkedListHashMap.put(Column.builder()
                .type(ColumnType.VARCHAR)
                .name("Column2")
                .notNull(false)
                .build(), new LinkedList<>());
        columnLinkedListHashMap.put(Column.builder()
                .type(ColumnType.VARCHAR)
                .name("Column3")
                .notNull(false)
                .build(), new LinkedList<>());
        Table table = new Table(columnLinkedListHashMap);
        Map<String, Table> mockedMap = Collections.singletonMap("table_name", table);

        Mockito.when(database.getTables()).thenReturn(mockedMap);
        Mockito.when(database.getTable("table_name")).thenReturn(table);
        Mockito.when(util.getParametersFromValidationString("table_name (value1, value2, value3")).thenReturn(new String[]{"value1", "value2", "value3"});
        assertTrue(validator.check(properString));
    }
}
