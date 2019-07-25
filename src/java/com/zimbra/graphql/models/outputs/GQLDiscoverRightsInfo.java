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

import com.google.common.collect.Lists;
import com.zimbra.common.gql.GqlConstants;
import com.zimbra.soap.account.type.DiscoverRightsInfo;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLType;

/**
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models.outputs
 * @copyright Copyright © 2019
 */
@GraphQLType(name=GqlConstants.CLASS_GQL_DISCOVER_RIGHTS_INFO, description="Contains discovered rights and it's info")
public class GQLDiscoverRightsInfo {

    private List<DiscoverRightsInfo> discoveredRights = Lists.newArrayList();

    /**
     * @return the discoveredRights
     */
    @GraphQLQuery(name=GqlConstants.DISCOVERED_RIGHTS, description="Rights info about discovered rights")
    public List<DiscoverRightsInfo> getDiscoveredRights() {
        return discoveredRights;
    }

    /**
     * @param discoveredRights the discoveredRights to set
     */
    public void setDiscoveredRights(List<DiscoverRightsInfo> discoveredRights) {
        this.discoveredRights = discoveredRights;
    }
}
