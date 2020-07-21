package com.alex.mysickwell.createQueryUtils;

import com.alex.mysickwell.util.CreateQueryUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class createQueryUtilsTest {

    @Autowired
    private CreateQueryUtil util;

    private String queryWithoutSpace = "CREATE TABLE tableNameWithoutSpace(kek VARCHAR, lol BOOLEAN, asd INTEGER);";
    private String queryWithSpace = "CREATE TABLE tableNameWithSpace (kek VARCHAR, lol BOOLEAN, asd INTEGER);";


    @Test
    void getTableNameFromQueryWithoutSpace(){
        assertEquals("tableNameWithoutSpace", util.getTableNameFromQuery(queryWithoutSpace));
    }
    @Test
    void getTableNameFromQueryWithSpace(){
        assertEquals("tableNameWithSpace", util.getTableNameFromQuery(queryWithSpace));
    }


}
