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
public class ZGraphQLExceptionHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        final List<GraphQLError> clientErrors = errors.stream()
            .filter(this::isClientError)
            .collect(Collectors.toList());

        final List<GraphQLError> serverErrors = errors.stream()
            .filter(e -> !isClientError(e))
            .map(ZGraphQLErrorAdapter::new)
            .collect(Collectors.toList());

        final List<GraphQLError> e = new ArrayList<>();
        e.addAll(clientErrors);
        e.addAll(serverErrors);
        return e;
    }

    /**
     * Determines types of errors, client side or not client side.
     *
     * @param error
     * @return boolean
     */
    protected boolean isClientError(GraphQLError error) {
        return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
    }
}
