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

import org.apache.commons.lang.StringUtils;

import com.zimbra.common.localconfig.LC;
import com.zimbra.common.util.ZimbraLog;

/**
 * The Configuration class.<br>
 * Configuration wrapper for LC.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.utilities
 * @copyright Copyright © 2018
 */
public class Configuration {

    public Configuration() {

    }

    public String getString(String key) {
        return getString(key, null);
    }

    public String getString(String key, String defaultValue) {
        return StringUtils.defaultIfEmpty(LC.get(key), defaultValue);
    }

    public Integer getInt(String key, Integer defaultValue) {
        final String stringValue = LC.get(key);
        Integer value = defaultValue;
        if (stringValue != null) {
            try {
                value = Integer.parseInt(stringValue);
            } catch (final NumberFormatException e) {
                ZimbraLog.extensions.debug("Cannot parse integer from configured LC value for key: '" + key + "'.");
            }
        }
        return value;
    }
}
