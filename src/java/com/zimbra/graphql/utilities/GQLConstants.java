/*
 * ***** BEGIN LICENSE BLOCK *****
 * Zimbra GraphQL Extension
 * Copyright (C) 2018 Synacor, Inc.
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software Foundation,
 * version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 * ***** END LICENSE BLOCK *****
 */
package com.zimbra.graphql.utilities;

/**
 * The GQLConstants class.<br>
 * Contains general service constants.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.utilities
 * @copyright Copyright © 2018
 */
public class GQLConstants {
    public static final String API_NAME = "zm-gql";
    public static final String ENCODING = "utf-8";
    public static final String DEFAULT_LOG_LEVEL = "INFO";
    public static final Integer DEFAULT_SERVER_PORT = 8080;
    public static final String DEFAULT_SERVER_CONTEXT_PATH = "/*";

    public static final String DEFAULT_HOST_URI_TEMPLATE = "https://%s:443";

    // http related
    public static final String HEADER_LOCATION = "Location";
    public static final String QUERY_ERROR = "error";
    public static final String QUERY_ERROR_MSG = "error_msg";
    public static final int STATUS_OK = 200;
    public static final int STATUS_BAD_REQUEST = 400;

    // http query error related
    public static final String ERROR_ACCESS_DENIED = "access_denied";
    public static final String ERROR_INVALID_ZM_AUTH_CODE = "invalid_zm_auth_code";
    public static final String ERROR_INVALID_ZM_AUTH_CODE_MSG = "Invalid or missing Zimbra session.";
    public static final String ERROR_AUTHENTICATION_ERROR = "authentication_error";

    // LC Related
    public static final String LC_REPOSITORY_CLASS_PREFIX = "";
    public static final String LC_GQL_SERVER_CONTEXT_PATH = "gql_server_context_path";
    public static final String LC_GQL_SERVER_PORT = "gql_server_port";
    public static final String LC_GQL_LOG_LEVEL = "gql_log_level";

}
