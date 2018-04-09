package com.zimbra.graphql.exceptions;

import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

/**
 * Error Adapter for GraphQL.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.exceptions
 * @copyright Copyright © 2018
 */
public class ZGraphQLErrorAdapter implements GraphQLError {

    /**
     * GraphQL error.
     */
    private final GraphQLError error;

    /**
     * Constructor.
     *
     * @param error
     */
    public ZGraphQLErrorAdapter(GraphQLError error) {
        this.error = error;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return error.getExtensions();
    }

    @Override
    public List<SourceLocation> getLocations() {
        return error.getLocations();
    }

    @Override
    public ErrorType getErrorType() {
        return error.getErrorType();
    }

    @Override
    public List<Object> getPath() {
        return error.getPath();
    }

    @Override
    public Map<String, Object> toSpecification() {
        return error.toSpecification();
    }

    @Override
    public String getMessage() {

        if (error instanceof ExceptionWhileDataFetching) {
            return ((ExceptionWhileDataFetching) error).getException().getMessage();
        }
        else {
            return error.getMessage();
        }
    }
}
