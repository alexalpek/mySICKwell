package com.alex.mysickwell.controller.advice.exception;

public class IllegalEndOfQueryException extends MySickWellException {
    public IllegalEndOfQueryException(String s) {
        super("Query is not ending with: " + s);
    }
}
