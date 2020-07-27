package com.alex.mysickwell.util;

import org.springframework.stereotype.Component;

@Component
public class SelectQueryUtil {
    public String getTableNameFromQuery(String query) {
        String[] queryData = query.split("(?i)FROM ");
        String tableNameWithSemiColon = queryData[1];
        return tableNameWithSemiColon
                .substring(0, tableNameWithSemiColon.length() - 1);
    }
}
