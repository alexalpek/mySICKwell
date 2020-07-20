package com.alex.mysickwell;

import com.alex.mysickwell.model.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MysickwellApplicationTests {

    private Database database;

    @BeforeEach
    public void setup(){
        database = new Database();
    }



}
