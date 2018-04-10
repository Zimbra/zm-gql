package com.zimbra.graphql.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;

/**
 * Exception handler for GraphQL.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.exceptions
 * @copyright Copyright © 2018
 */
public class ExceptionHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        final List<GraphQLError> clientErrors = errors.stream()
            .filter(this::isClientError)
            .collect(Collectors.toList());
        // Use custom ErrorAdapter to handle errors for non client errors
        final List<GraphQLError> serverErrors = errors.stream()
            .filter(error -> !isClientError(error))
            .map(ErrorAdapter::new)
            .collect(Collectors.toList());

        final List<GraphQLError> combinedErrors = new ArrayList<>();
        combinedErrors.addAll(clientErrors);
        combinedErrors.addAll(serverErrors);
        return combinedErrors;
    }

    /**
     * Returns true if client side.
     *
     * @param error
     * @return boolean
     */
    protected boolean isClientError(GraphQLError error) {
        return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
    }
}
