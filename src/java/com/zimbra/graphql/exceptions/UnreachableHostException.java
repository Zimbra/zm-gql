package com.zimbra.graphql.exceptions;

import java.util.HashMap;
import java.util.Map;

import com.zimbra.common.util.ZimbraLog;
import com.zimbra.graphql.utilities.GQLConstants;

/**
 * Unreachable Host exception handler for GraphQL.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.exceptions
 * @copyright Copyright © 2018
 */
public class UnreachableHostException extends GenericException {

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
    public UnreachableHostException(String message) {
        this(message, null);
    }

    /**
     * Constructor.<br>
     * Takes a throwable which will be used for logging.
     *
     * @param throwable an instance of Throwable
     */
    public UnreachableHostException(Throwable throwable) {
        this(null, throwable);
    }

    /**
     * Constructor.<br>
     * Sets message which will be visible to the client
     * as well as a generic reason message set in the extensions.
     *
     * @param message A message string
     * @param throwable an instance of Throwable
     */
    public UnreachableHostException(String message, Throwable throwable) {
        super(GQLConstants.PLEASE_TRY_AGAIN_LATER_MSG, throwable);
        setExtensionsMessage();
        if (message == null) {
            message = "";
        }
        ZimbraLog.extensions.error("The host is unreachable: " + message, throwable);
    }

    /**
     * Sets extensions info with data viewable to the client.
     *
     * Note: GraphQL services may provide an additional entry to errors with key extensions.
     * This entry, if set, must have a map as its value.
     * This entry is reserved for implementors to add additional information to errors
     * however they see fit, and there are no additional restrictions on its contents.
     *
     * http://facebook.github.io/graphql/draft/#sec-Errors
     */
    private void setExtensionsMessage() {
        final Map<String, Object> extensions = new HashMap<String, Object>();
        extensions.put(GQLConstants.CAUSED_BY_MSG, GQLConstants.UNREACHABLE_HOST_ERROR_MSG);
        this.setExtensions(extensions);
    }

}
