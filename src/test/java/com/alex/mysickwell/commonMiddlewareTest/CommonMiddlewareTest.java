package com.alex.mysickwell.commonMiddlewareTest;

import com.alex.mysickwell.validation.Middleware;
import com.alex.mysickwell.validation.QueryProperEnd;
import com.alex.mysickwell.validation.QueryProperStart;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CommonMiddlewareTest {

    private final String properQuery = "CREATE TABLE asd(kek VARCHAR, lol BOOLEAN, asd INTEGER);";
    private final String improperQuery = "CREATE TÉBÖL asd(kek VARCHAR, lol BOOLEAN, asd INTEGER)";

    @Test
    void StartWorksWithProperQuery() {
        Middleware middleware = new QueryProperStart("CREATE TABLE ");
        assertTrue(middleware.check(properQuery));
    }

    @Test
    void StartWorksWithImproperQuery() {
        Middleware middleware = new QueryProperStart("CREATE TABLE ");
        assertFalse(middleware.check(improperQuery));
    }


    @Test
    void EndWorksWithProperQuery() {
        Middleware middleware = new QueryProperEnd(");");
        assertTrue(middleware.check(properQuery));
    }

    @Test
    void EndWorksWithImproperQuery() {
        Middleware middleware = new QueryProperEnd(");");
        assertFalse(middleware.check(improperQuery));
    }

}
