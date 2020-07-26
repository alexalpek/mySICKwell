package com.alex.mysickwell.validation.create.middleware;

import com.alex.mysickwell.controller.advice.exception.IllegalParametersInQueryException;
import com.alex.mysickwell.controller.advice.exception.MySickWellException;
import com.alex.mysickwell.controller.advice.exception.QueryHasMalformedParametersException;
import com.alex.mysickwell.model.ColumnType;
import com.alex.mysickwell.validation.Middleware;

import java.util.Arrays;

public class CreateTableHasSupportedParameters extends Middleware {

    @Override
    public boolean check(String query) throws MySickWellException {
        String[] split = query.split("\\(");
        if (split.length < 2) throw new QueryHasMalformedParametersException();
        String[][] arrayOfArrays = Arrays.stream(split[1].split(","))
                .map(String::trim)
                .map(p -> p.split(" "))
                .toArray(String[][]::new);

        if (Arrays.stream(arrayOfArrays).allMatch(a -> ColumnType.allowedType(a[1]))) {
            return checkNext(query);
        }

        throw new IllegalParametersInQueryException("Unsupported parameter found in query: " + query);
    }
}
