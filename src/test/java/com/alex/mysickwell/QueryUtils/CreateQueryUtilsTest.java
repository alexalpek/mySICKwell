package com.alex.mysickwell.QueryUtils;

import com.alex.mysickwell.model.Column;
import com.alex.mysickwell.model.ColumnType;
import com.alex.mysickwell.util.CreateQueryUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CreateQueryUtilsTest {

    @Autowired
    private CreateQueryUtil util;

    private String queryWithoutSpace = "CREATE TABLE tableNameWithoutSpace(kek VARCHAR, lol BOOLEAN, asd INTEGER);";
    private String queryWithSpace = "CREATE TABLE tableNameWithSpace (kek VARCHAR, lol BOOLEAN, asd INTEGER);";


    @Test
    void getTableNameFromQueryWithoutSpace() {
        assertEquals("tableNameWithoutSpace", util.getTableNameFromQuery(queryWithoutSpace));
    }
    @Test
    void getTableNameFromQueryWithSpace(){
        assertEquals("tableNameWithSpace", util.getTableNameFromQuery(queryWithSpace));
    }

    @Test
    void getTableColumnsFromQueryTest(){
        String query = "CREATE TABLE table_name(name VARCHAR, age INTEGER);";
        Map<Column, LinkedList<?>> result = new LinkedHashMap<>();
        Column column1 = Column.builder()
                .name("name")
                .type(ColumnType.VARCHAR)
                .build();
        Column column2 = Column.builder()
                .name("age")
                .type(ColumnType.INTEGER)
                .build();

        result.put(column1, new LinkedList<>());
        result.put(column2, new LinkedList<>());
        assertEquals(result.toString(), util.getTableColumnsFromQuery(query).toString());
    }


}
