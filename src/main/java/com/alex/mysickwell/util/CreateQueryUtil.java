package com.alex.mysickwell.util;

import com.alex.mysickwell.model.Column;
import com.alex.mysickwell.model.ColumnType;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

@Service
public class CreateQueryUtil {

    public String getTableNameFromQuery(String query) {
        String queryStart = "create table ";
        String[] list = query.split("\\(");
        return list[0].substring(queryStart.length()).trim();
    }

    public HashMap<Column, LinkedList<?>> getTableColumnsFromQuery(String query) {
        String tableDataString = query.substring(0, query.length() - 2).split("\\(")[1];
        //age INTEGER, name VARCHAR
        String[] split = Arrays.stream(tableDataString.split(","))
                .map(String::trim)
                .toArray(String[]::new);
        HashMap<Column, LinkedList<?>> tableRepresentation = Arrays.stream(split).map(x -> {
            String[] columnDataString = x.split(" ");
            return Column.builder()
                    .type(ColumnType.getTypeByString(columnDataString[1]))
                    .name(columnDataString[0])
                    .notNull(false)
                    .build();
        }).collect(Collectors.toMap(column -> column, column -> new LinkedList<>(), (prev, next) -> next, HashMap::new));
        return tableRepresentation;
    }
}
