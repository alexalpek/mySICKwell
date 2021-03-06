package com.alex.mysickwell.util;

import com.alex.mysickwell.model.Column;
import com.alex.mysickwell.model.ColumnType;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

@Service
public class CreateQueryUtil {

    public String getTableNameFromQuery(String query) {
        String queryStart = "create table ";
        String[] list = query.split("\\(");
        return list[0].substring(queryStart.length()).trim();
    }

    public LinkedHashMap<Column, LinkedList<?>> getTableColumnsFromQuery(String query) {
        String tableDataString = query.substring(0, query.length() - 2).split("\\(")[1];
        String[] split = Arrays.stream(tableDataString.split(","))
                .map(String::trim)
                .toArray(String[]::new);
        LinkedHashMap<Column, LinkedList<?>> tableRepresentation = Arrays.stream(split).map(x -> {
            String[] columnDataString = x.split(" ");
            return Column.builder()
                    .type(ColumnType.getTypeByString(columnDataString[1]))
                    .name(columnDataString[0])
                    .build();
        }).collect(Collectors.toMap(column -> column, column -> new LinkedList<>(), (prev, next) -> next, LinkedHashMap::new));
        return tableRepresentation;
    }
}
