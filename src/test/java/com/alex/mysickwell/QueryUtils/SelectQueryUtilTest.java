package com.alex.mysickwell.QueryUtils;

import com.alex.mysickwell.util.SelectQueryUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SelectQueryUtilTest {

    private final String query = "SELECT name FROM TABLENAME;";
    @Autowired
    private SelectQueryUtil util;

    @Test
    void getTableNameFromQueryTest() {
        String tableNameFromQuery = util.getTableNameFromQuery(query);
        assertEquals("TABLENAME", tableNameFromQuery);
    }

    @Test
    void getParametersFromQueryTest() {
        String[] parametersFromQuery = util.getParametersFromQuery(query);
        assertEquals(
                Arrays.toString(new String[]{"name"}),
                Arrays.toString(parametersFromQuery));
    }

}
