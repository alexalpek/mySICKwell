package com.alex.mysickwell.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ModelTest {

    @Test
    void columnTypeGetTypeByString(){
        assertEquals(ColumnType.INTEGER, ColumnType.getTypeByString("INTEGER"));
    }

    @Test
    void allowedTypeReturnTrueForAllowedType(){
        assertTrue(ColumnType.allowedType("VARCHAR"));
    }

    @Test
    void allowedTypeIsNotCaseSensitive(){
        assertTrue(ColumnType.allowedType("vaRchAr"));
    }

    @Test
    void getDataTypeWorks(){
        assertEquals(Integer.class, ColumnType.INTEGER.getDatatype());
    }

    @Test
    void allowedTypeReturnFalseForNotAllowedType(){
        assertFalse(ColumnType.allowedType("INT"));
    }

}
