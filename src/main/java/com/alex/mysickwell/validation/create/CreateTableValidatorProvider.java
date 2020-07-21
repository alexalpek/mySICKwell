package com.alex.mysickwell.validation.create;

import com.alex.mysickwell.model.Database;
import com.alex.mysickwell.validation.Middleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTableValidatorProvider {

    private Middleware middleware = new CreateProperStart();

    @Autowired
    public CreateTableValidatorProvider(Database database) {
        this.middleware
                .linkWith(new CreateTableName(database))
                .linkWith(new CreateEndsProperly())
                .linkWith(new CreateTableHasParameters())
                .linkWith(new CreateTableHasSupportedParameters());
    }

    public Middleware getMiddleware() {
        return middleware;
    }
}
