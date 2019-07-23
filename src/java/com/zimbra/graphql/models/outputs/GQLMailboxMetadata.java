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

import com.zimbra.common.gql.GqlConstants;
import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.Element;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLType;

/**
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models.outputs
 * @copyright Copyright © 2019
 */
@GraphQLType(name=GqlConstants.GQL_MAILBOX_METADATA, description="Contains mailbox metadata")
public class GQLMailboxMetadata {

    private String section;
    private Map<String, Object> metadata;

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
     * @return the metadata
     */
    @GraphQLQuery(name=GqlConstants.METADATA, description="Metadata for mailbox")
    public Map<String, Object> getMetadata() {
        return metadata;
    }

    /**
     * @param metadata the metadata to set
     */
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    @GraphQLQuery(name=GqlConstants.CONTAINS_KEY, description="If metadata contains the key or not")
    public boolean containsKey(@GraphQLArgument(name = "key", description = "key to check") String key) {
        if (metadata == null) {
            return false;
        }
        return metadata.containsKey(key);
    }

    @GraphQLQuery(name=GqlConstants.SIZE, description="Size of metadata map")
    public int size() {
        if (metadata == null) {
            return 0;
        }
        return metadata.size();
    }

    @GraphQLQuery(name=GqlConstants.IS_EMPTY, description="Whether metadata map is empty or not")
    public boolean isEmpty() {
        if (metadata == null) {
            return true;
        }
        return metadata.isEmpty();
    }

    @GraphQLQuery(name=GqlConstants.GET_STRING, description="Get the value as string")
    public String get(@GraphQLArgument(name = "key", description = "key to get") String key,
            @GraphQLArgument(name = "defaultValue", description = "default string value if key not found in map") String defaultValue) {
        if (metadata == null) {
            return defaultValue;
        }
        Object value = metadata.get(key);
        return value == null ? defaultValue : value.toString();
    }

    @GraphQLQuery(name=GqlConstants.GET_LONG, description="Get the value as long")
    public long getLong(@GraphQLArgument(name = "key", description = "key to get") String key,
            @GraphQLArgument(name = "defaultValue", description = "default long value if key not found in map") long defaultValue) throws ServiceException {
        String raw = get(key, null);
        return raw == null ? defaultValue : Element.parseLong(key, raw);
    }

    @GraphQLQuery(name=GqlConstants.GET_INT, description="Get the value as int")
    public int getInt(@GraphQLArgument(name = "key", description = "key to get") String key,
            @GraphQLArgument(name = "defaultValue", description = "default int value if key not found in map") int defaultValue) throws ServiceException {
        String raw = get(key, null);
        return raw == null ? defaultValue : Element.parseInt(key, raw);
    }

    @GraphQLQuery(name=GqlConstants.GET_SHORT, description="Get the value as short")
    public short getShort(@GraphQLArgument(name = "key", description = "key to get") String key,
            @GraphQLArgument(name = "defaultValue", description = "default short value if key not found in map") short defaultValue) throws ServiceException {
        String raw = get(key, null);
        return raw == null ? defaultValue : Element.parseShort(key, raw);
    }

    @GraphQLQuery(name=GqlConstants.GET_DOUBLE, description="Get the value as double")
    public double getDouble(@GraphQLArgument(name = "key", description = "key to get") String key,
            @GraphQLArgument(name = "defaultValue", description = "default double value if key not found in map") double defaultValue) throws ServiceException {
        String raw = get(key, null);
        return raw == null ? defaultValue : Element.parseDouble(key, raw);
    }

    @GraphQLQuery(name=GqlConstants.GET_BOOL, description="Get the value as boolean")
    public boolean getBool(@GraphQLArgument(name = "key", description = "key to get") String key,
            @GraphQLArgument(name = "defaultValue", description = "default boolean value if key not found in map") boolean defaultValue) throws ServiceException {
        String raw = get(key, null);
        return raw == null ? defaultValue : Element.parseBool(key, raw);
    }

    @GraphQLQuery(name=GqlConstants.GET_LIST, description="Get the value as list")
    public List<Object> getList(@GraphQLArgument(name = "key", description = "key to get") String key,
            @GraphQLArgument(name = "nullOK", description = "To return null or throw exception if key not found in map") boolean nullOK) throws ServiceException {
        if (metadata == null) {
            if (nullOK) {
                return null;
            } else {
                throw ServiceException.INVALID_REQUEST("missing value for attribute: " + key, null);
            }
        }
        Object value = metadata.get(key);
        if (nullOK && value == null) {
            return null;
        }
        if (value instanceof List) {
            @SuppressWarnings("unchecked")
            List<Object> cast = (List<Object>) value;
            return cast;
        }
        throw ServiceException.INVALID_REQUEST("invalid/missing value for attribute: " + key, null);
    }

    @GraphQLQuery(name=GqlConstants.GET_MAP, description="Get the value as map")
    public Map<String, Object> getMap(@GraphQLArgument(name = "key", description = "key to get") String key,
            @GraphQLArgument(name = "nullable", description = "To return null or throw exception if key not found in map") boolean nullable) throws ServiceException {
        if (metadata == null) {
            if (nullable) {
                return null;
            } else {
                throw ServiceException.INVALID_REQUEST("missing value for attribute: " + key, null);
            }
        }
        Object value = metadata.get(key);
        if (nullable && value == null) {
            return null;
        }
        if (value instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> cast = (Map<String, Object>) value;
            return cast;
        }
        throw ServiceException.INVALID_REQUEST("invalid/missing value for attribute: " + key, null);
    }
}
