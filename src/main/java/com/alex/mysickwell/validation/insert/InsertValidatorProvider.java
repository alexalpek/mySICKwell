package com.alex.mysickwell.validation.insert;

import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.validation.Middleware;
import com.alex.mysickwell.validation.QueryProperEnd;
import com.alex.mysickwell.validation.QueryProperStart;
import com.alex.mysickwell.validation.insert.middleware.InsertHasParameters;
import com.alex.mysickwell.validation.insert.middleware.InsertQueryHasTableName;
import com.alex.mysickwell.validation.insert.middleware.InsertValidTableName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsertValidatorProvider {

    private final String CREATE_QUERY_START = "INSERT INTO ";
    private final String CREATE_QUERY_END = ");";

    private Middleware middleware = new QueryProperStart(CREATE_QUERY_START);

    @Autowired
    public InsertValidatorProvider(Database database) {
        this.middleware
                .linkWith(new InsertQueryHasTableName())
                .linkWith(new InsertValidTableName(database))
                .linkWith(new QueryProperEnd(CREATE_QUERY_END))
                .linkWith(new InsertHasParameters());
        //.linkWith(new InsertHasCorrectParameterTypes());    //TODO
    }

    public Middleware getMiddleware() {
        return middleware;
    }

}
