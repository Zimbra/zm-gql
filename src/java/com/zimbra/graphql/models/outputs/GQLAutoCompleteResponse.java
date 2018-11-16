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
package com.zimbra.graphql.models.outputs;

import java.util.List;

import com.zimbra.common.gql.GqlConstants;
import com.zimbra.soap.mail.type.AutoCompleteMatch;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLType;

/**
 * The GQLAutoCompleteResponse class.<br>
 * Contains auto-complete response information.
 * @see com.zimbra.soap.mail.message.AutoCompleteResponse
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models.outputs
 * @copyright Copyright © 2018
 */
@GraphQLType(name=GqlConstants.CLASS_AUTO_COMPLETE_RESPONSE, description="Contains auto-complete response information")
public class GQLAutoCompleteResponse {

    protected Boolean isCacheable;
    protected List<AutoCompleteMatch> matches;

    @GraphQLQuery(name=GqlConstants.IS_CACHEABLE, description="Denotes whether this response can be cached")
    public Boolean getIsCacheable() {
        return isCacheable;
    }

    public void setIsCacheable(Boolean isCacheable) {
        this.isCacheable = isCacheable;
    }

    @GraphQLQuery(name=GqlConstants.MATCHES, description="List of matches")
    public List<AutoCompleteMatch> getMatches() {
        return matches;
    }

    public void setMatches(List<AutoCompleteMatch> matches) {
        this.matches = matches;
    }

}
