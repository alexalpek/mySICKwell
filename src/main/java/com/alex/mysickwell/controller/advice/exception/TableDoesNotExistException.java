package com.alex.mysickwell.controller.advice.exception;

public class TableDoesNotExistException extends MySickWellException {
    public TableDoesNotExistException(String tableName) {
        super("Requested table does not exist: " + tableName);
    }
}
