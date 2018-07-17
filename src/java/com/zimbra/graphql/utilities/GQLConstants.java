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
public enum GQLConstants {

    /**
     * Service name.
     */
    API_NAME("zm-gql"),

    /**
     * Default encoding to use.
     */
    ENCODING("utf-8"),

    /**
     * Service path.
     */
    DEFAULT_SERVER_PATH("/graphql"),

    /**
     * TODO: see schema wiring
     */
    LC_REPOSITORY_CLASS_PREFIX("");

    /**
     * Enum value.
     */
    private String value;

    /**
     * @param value The value to set
     */
    GQLConstants(String value) {
        this.value = value;
    }

    /**
     * @return The value of this instance
     */
    public String getValue() {
        return value;
    }

}
