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

import io.leangen.graphql.annotations.GraphQLInputField;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.types.GraphQLType;

/**
 * The GQLPrefInput class.<br>
 * Contains prefs which are to be modified.
 * @see com.zimbra.soap.account.message.Pref
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models.inputs
 * @copyright Copyright © 2018
 */
@GraphQLType(name=GqlConstants.CLASS_ACCOUNT_PREF_INPUT, description="Input for prefs.")
public class GQLPrefInput {

    protected String name;
    protected String value;

    public String getName() {
        return name;
    }

    @GraphQLInputField(name=GqlConstants.NAME, description="The preference name to set")
    public void setName(@GraphQLNonNull String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    @GraphQLInputField(name=GqlConstants.VALUE, description="The preference value to set")
    public void setValue(@GraphQLNonNull String value) {
        this.value = value;
    }
}
