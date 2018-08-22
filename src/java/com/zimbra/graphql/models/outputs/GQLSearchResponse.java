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

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.zimbra.common.gql.GqlConstants;
import com.zimbra.soap.type.BaseQueryInfo;
import com.zimbra.soap.type.SearchHit;
import com.zimbra.soap.type.ZmBoolean;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLType;

/**
 * The GQLSearchResponse class.<br>
 * Contains search response data for a search.
 * @see com.zimbra.soap.mail.message.SearchResponse
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models.inputs
 * @copyright Copyright © 2018
 */
@GraphQLType(name = GqlConstants.SEARCH_RESPONSE, description = "Search response data")
public class GQLSearchResponse {

	protected String sortBy;
	protected Integer queryOffset;
	protected ZmBoolean queryMore;
	protected Long totalSize;
    protected List<SearchHit> searchHits = Lists.newArrayList();
    protected List<BaseQueryInfo> queryInfos = Lists.newArrayList();

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public void setQueryOffset(Integer queryOffset) {
        this.queryOffset = queryOffset;
    }

    public void setQueryMore(Boolean queryMore) {
        this.queryMore = ZmBoolean.fromBool(queryMore);
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    public void setSearchHits(Iterable <SearchHit> searchHits) {
        this.searchHits.clear();
        if (searchHits != null) {
            Iterables.addAll(this.searchHits,searchHits);
        }
    }

    public GQLSearchResponse addSearchHit(SearchHit searchHit) {
        this.searchHits.add(searchHit);
        return this;
    }

    public void setQueryInfos(Iterable <BaseQueryInfo> queryInfos) {
        this.queryInfos.clear();
        if (queryInfos != null) {
            Iterables.addAll(this.queryInfos,queryInfos);
        }
    }

    public GQLSearchResponse addQueryInfo(BaseQueryInfo queryInfo) {
        this.queryInfos.add(queryInfo);
        return this;
    }

    @GraphQLQuery(name=GqlConstants.SORT_BY, description="What to sort by. Default is \"dateDesc\"")
    public String getSortBy() {
        return sortBy;
    }

    @GraphQLQuery(name=GqlConstants.QUERY_OFFSET, description="Offset - an integer specifying the 0-based offset into the results list returned as the first result for this search operation.")
    public Integer getQueryOffset() {
        return queryOffset;
    }

    @GraphQLQuery(name=GqlConstants.QUERY_MORE, description="Set if there are more search results remaining.")
    public Boolean getQueryMore() {
        return ZmBoolean.toBool(queryMore);
    }

    @GraphQLQuery(name=GqlConstants.TOTAL_SIZE, description="All messages")
    public Long getTotalSize() {
        return totalSize;
    }

    @GraphQLQuery(name=GqlConstants.QUERY_INFOS, description="Info block. \nUsed to return general status information about your search. The `wildcard` element tells you about the status of `wildcard` expansions within your search. If `expanded` is set, then the `wildcard` was expanded and the matches are included in the search. If `expanded` is unset then the `wildcard` was not specific enough and therefore no `wildcard` matches are included (exact-match is included in results).")
    public List<BaseQueryInfo> getQueryInfos() {
        return Collections.unmodifiableList(queryInfos);
    }

    @GraphQLQuery(name=GqlConstants.SEARCH_HITS, description="Search hits")
    public List<? extends SearchHit> getSearchHits() {
        return Collections.unmodifiableList(searchHits);
    }

}
