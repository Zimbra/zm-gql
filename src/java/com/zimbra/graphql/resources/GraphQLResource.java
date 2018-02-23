package com.zimbra.graphql.resources;

import javax.servlet.annotation.WebServlet;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.SchemaParser;
import com.zimbra.graphql.models.AccountInfo;

import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;

/**
 * GraphQL Resource servlet.
 */
@WebServlet(urlPatterns = "/graphql")
public class GraphQLResource extends SimpleGraphQLServlet {

	private static final long serialVersionUID = 1L;

	public GraphQLResource() {
		super(buildSchema());
	}

	protected static GraphQLSchema buildSchema() {
		return SchemaParser.newParser()
            .file("schema.graphqls")
            .resolvers(new GraphQLQueryResolver() {
            		public AccountInfo accountInfo() {
            			return new AccountInfo("user@zimbra");
            		}
            })
            .build()
            .makeExecutableSchema();
	}

}
