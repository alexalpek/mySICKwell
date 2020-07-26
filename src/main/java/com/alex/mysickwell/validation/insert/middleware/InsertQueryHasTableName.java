package com.alex.mysickwell.validation.insert.middleware;

import com.alex.mysickwell.controller.advice.exception.MySickWellException;
import com.alex.mysickwell.controller.advice.exception.QueryHasNoTableNameException;
import com.alex.mysickwell.validation.Middleware;


public class InsertQueryHasTableName extends Middleware {

    /**
     * QueryProperStart validator already removed the start of the
     * query ("INSERT INTO " in this case).
     * <p>
     * Query is split by the " VALUE" regex, indicating the created array will
     * have two elements: [name_of_the_table, rest_of_the_query].
     */


    @Override
    public boolean check(String query) throws MySickWellException {
        System.out.println(this.getClass().getSimpleName() + ": " + query);
        String[] split = query.split("\\s(?i)VALUES\\s");
        if (split.length < 2) throw new QueryHasNoTableNameException("Insert query has no table name in it: " + query);
        //Trim check is to prevent names with multiple whitespace.
        String tableName = split[0].trim();
        if (!tableName.equals("")) {
            return checkNext(query);
        }
        System.out.println(this.getClass().getSimpleName() + " returned fail for query: " + query);
        throw new QueryHasNoTableNameException("Insert query has no table name in it: " + query);
    }

}
