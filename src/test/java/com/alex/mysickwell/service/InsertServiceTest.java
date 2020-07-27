package com.alex.mysickwell.service;

import com.alex.mysickwell.controller.advice.exception.MySickWellException;
import com.alex.mysickwell.model.Column;
import com.alex.mysickwell.model.ColumnType;
import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.model.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InsertServiceTest {

    private final String properQuery = "INSERT INTO table_name VALUES (string, 5);";
    @Autowired
    private InsertService service;
    @MockBean
    private Database database;
    private Map<Column, LinkedList<?>> mockedTable;

    @BeforeEach
    void init() {
        mockedTable = new LinkedHashMap<>();
        Column col1 = Column.builder()
                .name("word")
                .type(ColumnType.VARCHAR)
                .build();
        Column col2 = Column.builder()
                .name("integer")
                .type(ColumnType.INTEGER)
                .build();
        mockedTable.put(col1, new LinkedList<>());
        mockedTable.put(col2, new LinkedList<>());
    }

    @Test
    void addHelperCanAddElementsRegardlessOfType() {
        LinkedList<String> stringList = new LinkedList<>();
        service.addHelper(stringList, "param");
        assertEquals(1, stringList.size());

        LinkedList<Integer> integerList = new LinkedList<>();
        service.addHelper(integerList, 5);
        assertEquals(1, integerList.size());
    }

    @Test
    void insertValuesFromQueryToTableInsertsValues() throws MySickWellException {
        Mockito.when(database.getTable("table_name"))
                .thenReturn(new Table(mockedTable));
        Map<String, Table> returnedTables = new HashMap<>();
        returnedTables.put("table_name", null);
        Mockito.when(database.getTables()).thenReturn(returnedTables);

        assertEquals(2, service.insertToTable(properQuery));
    }


}
