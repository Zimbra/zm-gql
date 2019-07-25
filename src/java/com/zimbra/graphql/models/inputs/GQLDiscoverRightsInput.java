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
import java.util.List;

import com.zimbra.common.gql.GqlConstants;

import io.leangen.graphql.annotations.GraphQLInputField;
import io.leangen.graphql.annotations.types.GraphQLType;

/**
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models.inputs
 * @copyright Copyright © 2019
 */
@GraphQLType(name=GqlConstants.CLASS_GQL_DISCOVER_RIGHTS_INPUT, description="Input for DiscoverRights.")
public class GQLDiscoverRightsInput {

    private List<String> rights;

    /**
     * @return the rights
     */
    @GraphQLInputField(name=GqlConstants.RIGHTS, description="List of rights")
    public List<String> getRights() {
        return rights;
    }

    /**
     * @param rights the rights to set
     */
    public void setRights(List<String> rights) {
        this.rights = rights;
    }
}
