package com.zimbra.graphql.exceptions;

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
public class GenericException extends RuntimeException implements GraphQLError {

    /**
     * Serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Adds information about the error.<br>
     *
     * Note: GraphQL services may provide an additional entry to errors with key extensions.
     * This entry, if set, must have a map as its value.
     * This entry is reserved for implementors to add additional information to errors
     * however they see fit, and there are no additional restrictions on its contents.
     *
     * http://facebook.github.io/graphql/draft/#sec-Errors
     */
    protected Map<String, Object> extensions;

    /**
     * Constructor.
     *
     * @param message A message string
     * @param throwable Any instance of Throwable
     */
    public GenericException(String message, Throwable throwable) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param message A message string
     */
    public GenericException(String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param throwable Any instance of Throwable
     */
    public GenericException(Throwable throwable) {
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
     */
    public void setExtensions(Map<String, Object> data) {
        this.extensions = data;
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
