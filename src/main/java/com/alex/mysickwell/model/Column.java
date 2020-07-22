package com.alex.mysickwell.model;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class Column {

    private String name;
    private ColumnType type;
    private boolean notNull;

}
