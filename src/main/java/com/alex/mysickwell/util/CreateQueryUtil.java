package com.alex.mysickwell.util;

import com.alex.mysickwell.model.Column;
import com.alex.mysickwell.model.Row;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;

@Service
public class CreateQueryUtil {

    public String getTableNameFromQuery(String query) {
        String queryStart = "create table ";
        String[] list = query.split("\\(");
        return list[0].substring(queryStart.length()).trim();
    }

    public HashMap<Column<?>, LinkedList<Row<?>>> getTableColumnsFromQuery(String query) { //TODO: abstract factory maybe?
        String tableDataString = query.substring(0,query.length()-2).split("\\(")[1];
        System.out.println(tableDataString);
        return null;
    }
}
