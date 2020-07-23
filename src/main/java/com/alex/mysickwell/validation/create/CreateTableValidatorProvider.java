package com.alex.mysickwell.validation.create;

import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.validation.Middleware;
import com.alex.mysickwell.validation.QueryProperEnd;
import com.alex.mysickwell.validation.QueryProperStart;
import com.alex.mysickwell.validation.create.middleware.CreateTableHasParameters;
import com.alex.mysickwell.validation.create.middleware.CreateTableHasSupportedParameters;
import com.alex.mysickwell.validation.create.middleware.CreateTableName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateTableValidatorProvider {

    private final String CREATE_QUERY_START = "CREATE TABLE ";
    private final String CREATE_QUERY_END = ");";

    private Middleware middleware = new QueryProperStart(CREATE_QUERY_START);

    @Autowired
    public CreateTableValidatorProvider(Database database) {
        this.middleware
                .linkWith(new CreateTableName(database))
                .linkWith(new QueryProperEnd(CREATE_QUERY_END))
                .linkWith(new CreateTableHasParameters())
                .linkWith(new CreateTableHasSupportedParameters());
    }

    public Middleware getMiddleware() {
        return middleware;
    }
}
