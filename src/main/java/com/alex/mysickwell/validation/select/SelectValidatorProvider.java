package com.alex.mysickwell.validation.select;

import com.alex.mysickwell.validation.Middleware;
import com.alex.mysickwell.validation.QueryProperEnd;
import com.alex.mysickwell.validation.QueryProperStart;
import com.alex.mysickwell.validation.select.middleware.SelectHasTableName;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class SelectValidatorProvider {

    private final String SELECT_QUERY_START = "SELECT ";
    private final String SELECT_QUERY_END = ";";

    @Getter
    private Middleware middleware = new QueryProperStart(SELECT_QUERY_START);

    public SelectValidatorProvider() {
        this.middleware
                .linkWith(new QueryProperEnd(SELECT_QUERY_END))
                .linkWith(new SelectHasTableName());

    }
}
