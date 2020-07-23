package com.alex.mysickwell.QueryUtils;

import com.alex.mysickwell.util.InsertQueryUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InsertQueryUtilsTest {

    @Autowired
    private InsertQueryUtil util;

    private String query = "INSERT INTO query_table_name VALUES(kek, lol, asd);";


    @Test
    void getTableNameFromQuery() {
        assertEquals("query_table_name", util.getTableNameFromQuery(query));
    }

    @Test
    void gettingParametersFromQueryWorks() {
        assertEquals(
                Arrays.toString(new String[]{"kek", "lol", "asd"}),
                Arrays.toString(util.getParametersFromQuery(query)));
    }

}
