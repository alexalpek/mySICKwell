package com.alex.mysickwell.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class Database {

    private Map<String, Table> tables = new HashMap<>();

}
