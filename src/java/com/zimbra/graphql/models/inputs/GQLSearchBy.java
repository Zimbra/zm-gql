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
package com.zimbra.graphql.models.inputs;

import com.zimbra.common.gql.GqlConstants;

import io.leangen.graphql.annotations.GraphQLEnumValue;
import io.leangen.graphql.annotations.types.GraphQLType;

/**
 * The GQLSearchBy input class.<br>
 * Contains parameters which decide how search will be done.
 *
 */
@GraphQLType(name = GqlConstants.CLASS_SEARCH_BY, description = "Search by REF or Query")
public enum GQLSearchBy {

    @GraphQLEnumValue(description = "search entry by REF") REF,
    @GraphQLEnumValue(description = "search by query") QUERY;

}
