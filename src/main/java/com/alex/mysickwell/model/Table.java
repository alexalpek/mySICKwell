package com.alex.mysickwell.model;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@AllArgsConstructor
public class Table {

    private Map<Column<?>, LinkedList<Row<?>>> data = new HashMap<>();

}
