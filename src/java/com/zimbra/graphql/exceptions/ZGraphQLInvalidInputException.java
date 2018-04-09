package com.zimbra.graphql.exceptions;

import com.zimbra.graphql.utilities.GQLConstants;

/**
 * Invalid input exception handler for GraphQL.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.exceptions
 * @copyright Copyright © 2018
 */
public class ZGraphQLInvalidInputException extends ZGraphQLException {

    /**
     * Serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.<br>
     * Sets message which will be visible to the client.
     *
     * @param message A message string that will be visible to the client
     */
    public ZGraphQLInvalidInputException(String message) {
        super(message, null);
    }

    /**
     * Constructor.<br>
     * Sets a generic message which will be visible to the client.
     *
     * @param throwable an instance of Throwable
     */
    public ZGraphQLInvalidInputException(Throwable throwable) {
        super(GQLConstants.INVALID_INPUT_ERROR_MSG, throwable);
    }

    /**
     * Constructor.<br>
     * Sets message which will be visible to the client.
     *
     * @param message A message string that will be visible to the client
     * @param throwable an instance of Throwable
     */
    public ZGraphQLInvalidInputException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
