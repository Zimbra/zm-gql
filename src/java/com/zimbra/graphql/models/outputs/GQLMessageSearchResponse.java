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

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Iterables;
import com.zimbra.common.gql.GqlConstants;
import com.zimbra.soap.mail.type.MessageHitInfo;
import com.zimbra.soap.type.SearchHit;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLType;

/**
 * The GQLMessageSearchResponse class.<br>
 * Contains search response data for a message search.
 * @see com.zimbra.soap.mail.message.SearchResponse
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models.inputs
 * @copyright Copyright © 2018
 */
@GraphQLType(name = GqlConstants.CLASS_MESSAGE_SEARCH_RESPONSE, description = "Search response data for a message search")
public class GQLMessageSearchResponse extends GQLSearchResponse {

    @Override
    public void setSearchHits(Iterable<SearchHit> setHits) {
        this.searchHits.clear();
        if (setHits != null) {
            Iterables.addAll(this.searchHits, setHits);
        }
    }

    public GQLMessageSearchResponse addSearchHit(MessageHitInfo searchHit) {
        this.searchHits.add(searchHit);
        return this;
    }

    @Override
    @GraphQLQuery(name=GqlConstants.SEARCH_HITS, description="Search hits for messages")
    public List<MessageHitInfo> getSearchHits() {
        final List<MessageHitInfo> hits = new ArrayList<MessageHitInfo>();
        for (final SearchHit hit : searchHits) {
            if (hit instanceof MessageHitInfo) {
                hits.add((MessageHitInfo) hit);
            }
        }
        return hits;
    }

}
