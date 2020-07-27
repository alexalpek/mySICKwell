package com.alex.mysickwell.controller.advice.exception;

public class QueryHasNoTableNameException extends MySickWellException {
    public QueryHasNoTableNameException() {
        super("Query has not provided any table name!");
    }
}
