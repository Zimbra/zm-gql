package com.zimbra.graphql.exceptions;

import java.util.HashMap;
import java.util.Map;

import com.zimbra.graphql.utilities.GQLConstants;

/**
 * Unreachable Host exception handler for GraphQL.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.exceptions
 * @copyright Copyright © 2018
 */
public class ZUnreachableHostException extends ZGraphQLException {

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
    public ZUnreachableHostException(String message) {

        super(message, null);
    }

    /**
     * Constructor.<br>
     * Sets message which will be visible to the client as well as a generic reason message set in the extensions.
     *
     * @param throwable an instance of Throwable
     */
    public ZUnreachableHostException(Throwable throwable) {

        super(GQLConstants.PLEASE_TRY_AGAIN_LATER_MSG, throwable);
        this.setExtensionsMessage();
    }

    /**
     * Constructor.<br>
     * Sets message which will be visible to the client as well as a generic reason message set in the extensions.
     *
     * @param message A message string that will be visible to the client
     * @param throwable an instance of Throwable
     */
    public ZUnreachableHostException(String message, Throwable throwable) {

        super(message, throwable);
        this.setExtensionsMessage();
    }

    /**
     * Sets extensions info with generic data.
     */
    private void setExtensionsMessage() {

        final Map<String, Object> extensions = new HashMap<String, Object>();
        extensions.put(GQLConstants.CAUSED_BY_MSG, GQLConstants.UNREACHABLE_HOST_ERROR_MSG);
        this.setExtensions(extensions);
    }
}
