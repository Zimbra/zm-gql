package com.zimbra.graphql.resources;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.SchemaParser;
import com.zimbra.graphql.models.AccountInfo;

import graphql.schema.GraphQLSchema;

public class ZGraphQLServlet extends ZGraphQLServletBase {

    @Override
    public String getPath() {
        return "/graphql";
    }

    @Override
    protected GraphQLSchema buildSchema() {
        return SchemaParser
            .newParser()
            .file("schema.graphqls")
            .resolvers(new GraphQLQueryResolver() {
                public AccountInfo accountInfo() {
                    return new AccountInfo();
                }
        })
        .build()
        .makeExecutableSchema();
    }

}
