package com.zimbra.graphql.exceptions;

import com.zimbra.common.util.ZimbraLog;
import com.zimbra.graphql.utilities.GQLConstants;

/**
 * Resource not found exception handler for GraphQL.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.exceptions
 * @copyright Copyright © 2018
 */
public class ResourceNotFoundException extends GenericException {

    /**
     * Serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.<br>
     * Takes a message which will be used for logging.
     *
     * @param message A message string
     */
    public ResourceNotFoundException(String message) {
        this(message, null);
    }

    /**
     * Constructor.<br>
     * Takes a throwable which will be used for logging.
     *
     * @param throwable an instance of Throwable
     */
    public ResourceNotFoundException(Throwable throwable) {
        this(null, throwable);
    }

    /**
     * Constructor.<br>
     * Sets response which will be visible to the client and logs message and/or exception.
     *
     * @param message A message string
     * @param throwable an instance of Throwable
     */
    public ResourceNotFoundException(String message, Throwable throwable) {
        super(GQLConstants.ERROR_RESOURCE_NOT_FOUND_MSG, throwable);
        ZimbraLog.extensions.error(GQLConstants.ERROR_RESOURCE_NOT_FOUND_MSG, message, throwable);
    }

}
