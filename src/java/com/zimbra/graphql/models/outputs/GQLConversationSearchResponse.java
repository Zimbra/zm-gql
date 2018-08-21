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
import com.zimbra.soap.mail.type.ConversationHitInfo;
import com.zimbra.soap.type.SearchHit;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLType;

/**
 * The GQLConversationSearchResponse class.<br>
 * Contains search response data for a conversation search.
 * @see com.zimbra.soap.mail.message.SearchResponse
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models.inputs
 * @copyright Copyright © 2018
 */
@GraphQLType(name = "GQLConversationSearchResponse",
    description = "Search response data for a conversation search")
public class GQLConversationSearchResponse extends GQLSearchResponse {

    @Override
    public void setSearchHits(Iterable<SearchHit> setHits) {
        this.searchHits.clear();
        if (setHits != null) {
            final List<ConversationHitInfo> hits = new ArrayList<ConversationHitInfo>();
            for (final SearchHit hit : setHits) {
                if (hit.getClass().equals(ConversationHitInfo.class)) {
                    hits.add((ConversationHitInfo) hit);
                }
            }
            Iterables.addAll(this.searchHits, hits);
        }
    }

    public GQLConversationSearchResponse addSearchHit(ConversationHitInfo searchHit) {
        this.searchHits.add(searchHit);
        return this;
    }

    @Override
    @GraphQLQuery(name="searchHits", description="Search hits for conversations")
    public List<ConversationHitInfo> getSearchHits() {
        final List<ConversationHitInfo> hits = new ArrayList<ConversationHitInfo>();
        for (final SearchHit hit : searchHits) {
            if (hit instanceof ConversationHitInfo) {
                hits.add((ConversationHitInfo) hit);
            }
        }
        return hits;
    }
}
