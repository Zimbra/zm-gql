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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * The GQLUtilities class.<br>
 * Contains general utility methods.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.utilities
 * @copyright Copyright © 2018
 */
public class GQLUtilities {

    /**
     * Creates a mapper that can convert between Java <-> JSON objects.
     *
     * @return mapper A mapper object
     */
    public static ObjectMapper createDefaultMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        return mapper;
    }

    /**
     * Decodes a stream into a byte array.
     *
     * @param input The stream
     * @param size The buffer size
     * @return Decoded stream
     * @throws IOException If there are issues reading the stream
     */
    public static byte[] decodeStream(InputStream input, long size) throws IOException {
        final long MIN_BUFFER_SIZE = 100;
        final long MAX_BUFFER_SIZE = 4080;
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int lengthRead;
        // buffer size must be within our bounds
        if (size < MIN_BUFFER_SIZE || size > MAX_BUFFER_SIZE) {
            size = MAX_BUFFER_SIZE;
        }
        final byte[] data = new byte[(int) size];
        try {
            // read until the end
            while ((lengthRead = input.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, lengthRead);
            }
            buffer.flush();
        } finally {
            // always close the input
            input.close();
        }
        return buffer.toByteArray();
    }

    /**
     * Returns an empty list if the specified list is null.
     *
     * @param list The list to check
     * @return A non-null list
     */
    public static <E> List<E> emptyListIfNull(List<E> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return list;
    }

}
