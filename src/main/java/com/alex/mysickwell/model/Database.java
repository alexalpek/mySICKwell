package com.alex.mysickwell.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

@Component
@Data
public class Database {

    private Map<String, Table> tables = new HashMap<>();

    public void createTable(String tableName, LinkedHashMap<Column, LinkedList<?>> table) {
        tables.put(tableName, new Table(table));
    }

    public Table getTable(String name) {
        return tables.get(name);
    }

    public void setTable(String tableName, Table table) {
        tables.put(tableName, table);
    }
}
