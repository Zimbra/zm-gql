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
package com.zimbra.graphql.models.inputs;

import com.zimbra.common.gql.GqlConstants;

import io.leangen.graphql.annotations.GraphQLEnumValue;
import io.leangen.graphql.annotations.types.GraphQLType;

/**
 * The GQLFolderSelector input class.<br>
 * Contains how the folder is identified.
 *
 */
@GraphQLType(name = GqlConstants.CLASS_FOLDER_SELECTOR, description = "select folder by id or path")
public enum GQLFolderSelector {

    @GraphQLEnumValue(description = "folder id") ID,
    @GraphQLEnumValue(description = "folder path") PATH;

}
