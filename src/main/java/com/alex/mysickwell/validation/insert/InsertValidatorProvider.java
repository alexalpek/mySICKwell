package com.alex.mysickwell.validation.insert;

import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.util.InsertQueryUtil;
import com.alex.mysickwell.validation.Middleware;
import com.alex.mysickwell.validation.QueryProperEnd;
import com.alex.mysickwell.validation.QueryProperStart;
import com.alex.mysickwell.validation.insert.middleware.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsertValidatorProvider {

    private final String INSERT_QUERY_START = "INSERT INTO ";
    private final String INSERT_QUERY_END = ");";

    private Middleware middleware = new QueryProperStart(INSERT_QUERY_START);

    @Autowired
    public InsertValidatorProvider(Database database, InsertQueryUtil util) {
        this.middleware
                .linkWith(new InsertQueryHasTableName())
                .linkWith(new InsertValidTableName(database))
                .linkWith(new QueryProperEnd(INSERT_QUERY_END))
                .linkWith(new InsertHasParameters())
                .linkWith(new InsertParametersAreSameNumber(database, util))
                .linkWith(new InsertParametersHasTheRightDataType(database, util));
    }

    public Middleware getMiddleware() {
        return middleware;
    }

}
