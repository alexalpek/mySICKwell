package com.alex.mysickwell.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Map;

@AllArgsConstructor
public class Table {

    @Getter
    @Setter
    private Map<Column, LinkedList<?>> data;

}
