package com.alex.mysickwell.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class Column {

    private String name;
    private ColumnType type;
    private boolean notNull;

}
