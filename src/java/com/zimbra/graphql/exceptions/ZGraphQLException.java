package com.zimbra.graphql.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

/**
 * Generic exception class for GraphQL.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.exceptions
 * @copyright Copyright © 2018
 */
public class ZGraphQLException extends RuntimeException implements GraphQLError {
    /**
     * Serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Adds information about the error.<br>
     * http://facebook.github.io/graphql/draft/#sec-Errors
     */
    protected Map<String, Object> extensions = new HashMap<>();

    /**
     * Constructor.
     *
     * @param message A message string that will be visible to the client
     * @param throwable Any instance of Throwable
     */
    public ZGraphQLException(String message, Throwable throwable) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param message A message string that will be visible to the client
     */
    public ZGraphQLException(String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param throwable Any instance of Throwable
     */
    public ZGraphQLException(Throwable throwable) {
        super(throwable.getMessage());
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    /**
     * Sets the extensions attribute data.
     *
     * @param data A map of message data
     * @return the class instance (used to chain method calls)
     */
    public ZGraphQLException setExtensions(Map<String, Object> data) {
        this.extensions = data;
        return this;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
