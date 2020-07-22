package com.alex.mysickwell.validation.create;

import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.validation.QueryEndsProperly;
import com.alex.mysickwell.validation.QueryProperStart;
import com.alex.mysickwell.validation.Middleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTableValidatorProvider {

    private final String CREATE_QUERY_START = "CREATE TABLE ";
    private final String CREATE_QUERY_END = ");";

    private Middleware middleware = new QueryProperStart(CREATE_QUERY_START);

    @Autowired
    public CreateTableValidatorProvider(Database database) {
        this.middleware
                .linkWith(new CreateTableName(database))
                .linkWith(new QueryEndsProperly(CREATE_QUERY_END))
                .linkWith(new CreateTableHasParameters())
                .linkWith(new CreateTableHasSupportedParameters());
    }

    public Middleware getMiddleware() {
        return middleware;
    }
}
