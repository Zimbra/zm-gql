package com.zimbra.graphql.exceptions;

import com.zimbra.common.util.ZimbraLog;
import com.zimbra.graphql.utilities.GQLConstants;

/**
 * Configuration exception handler for GraphQL.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.exceptions
 * @copyright Copyright © 2018
 */
public class ConfigurationException extends GenericException {

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
    public ConfigurationException(String message) {
        this(message, null);
    }

    /**
     * Constructor.<br>
     * Takes a throwable which will be used for logging.
     *
     * @param throwable an instance of Throwable
     */
    public ConfigurationException(Throwable throwable) {
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
    public ConfigurationException(String message, Throwable throwable) {
        super(GQLConstants.INVALID_CONFIG_ERROR_MSG, throwable);
        if (message == null) {
            message = "";
        }
        ZimbraLog.extensions.error("The configuration is invalid: " + message, throwable);
    }

}
