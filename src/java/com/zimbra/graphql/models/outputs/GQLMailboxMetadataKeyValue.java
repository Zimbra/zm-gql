/*
 * ***** BEGIN LICENSE BLOCK *****
 * Zimbra GraphQL Extension
 * Copyright (C) 2019 Synacor, Inc.
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
package com.zimbra.graphql.models.outputs;

import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;
import com.zimbra.common.gql.GqlConstants;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLType;

/**
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models.outputs
 * @copyright Copyright © 2019
 */
@GraphQLType(name=GqlConstants.CLASS_GQL_MAILBOX_METADATA_KEY_VALUE, description="Key and Value available in metadata")
public class GQLMailboxMetadataKeyValue {

    private String section;
    private String key;
    private Object value;

    /**
     * @return the section
     */
    @GraphQLQuery(name=GqlConstants.SECTION, description="Section for which the mailbox metadata is fetched")
    public String getSection() {
        return section;
    }

    /**
     * @param section the section to set
     */
    public void setSection(String section) {
        this.section = section;
    }
    /**
     * @return the key
     */
    @GraphQLQuery(name=GqlConstants.GET_KEY, description="Key available in metadata for mailbox")
    public String getKey() {
        return key;
    }
    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }
    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }
    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }
    /**
     * @return the value
     */
    @SuppressWarnings("rawtypes")
    @GraphQLQuery(name=GqlConstants.GET_VALUE, description="Value for given key available in metadata for mailbox")
    public String getStringValue() {
        String val = null;
        if (value instanceof Map) {
            val = Joiner.on(",").withKeyValueSeparator("=").join((Map<?, ?>) value);
        } else if (value instanceof List) {
            val = String.join(",", (List) value);
        } else {
            val = value.toString();
        }
        return val;
    }
}
