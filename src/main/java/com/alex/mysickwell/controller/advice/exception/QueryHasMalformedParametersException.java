package com.alex.mysickwell.controller.advice.exception;

public class QueryHasMalformedParametersException extends MySickWellException {
    public QueryHasMalformedParametersException() {
        super("Query has malformed parameters");
    }
}
