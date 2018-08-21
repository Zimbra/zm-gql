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
@GraphQLType(name = "GQLSearchResponse", description = "Search response data")
public class GQLSearchResponse {

    /**
     * What to sort by.
     *
     * Default is "dateDesc".
     *
     * Possible values:
     * none|dateAsc|dateDesc|subjAsc|subjDesc|nameAsc|nameDesc|rcptAsc|
     * rcptDesc|attachAsc|attachDesc|flagAsc|flagDesc|priorityAsc|
     * priorityDesc|idAsc|idDesc|readAsc|readDesc
     *
     * If sort-by is "none" then cursors MUST NOT be used, and some searches
     * are impossible (searches that require intersection of complex sub-ops).
     * Server will throw an IllegalArgumentException if the search is invalid.
     *
     * ADDITIONAL SORT MODES FOR TASKS: valid only if types="task" (and task alone):
     * taskDueAsc|taskDueDesc|taskStatusAsc|taskStatusDesc|taskPercCompletedAsc|
     * taskPercCompletedDesc
     */
	protected String sortBy;

    /**
     * Offset - an integer specifying the 0-based offset.
     *
     * An integer specifying the 0-based offset into the results list returned
     * as the first result for this search operation.
     */
	protected Integer queryOffset;

    /**
     * Set if there are more search results remaining.
     */
	protected ZmBoolean queryMore;

    /**
     * All messages in the response.
     */
	protected Long totalSize;

    /**
     * Search hits returned for the query.
     */
    protected List<SearchHit> searchHits = Lists.newArrayList();

    /**
     * Info block.  Used to return general status information about your search.
     *
     * The wildcard element tells you about the status of wildcard
     * expansions within your search.
     *
     * If expanded is set, then the wildcard was expanded and the matches
     * are included in the search. If expanded is unset then the wildcard was not
     * specific enough and therefore no wildcard matches are included
     * (exact-match <b>is</b> included in results).
     */
    protected List<BaseQueryInfo> queryInfos = Lists.newArrayList();

    /**
     * Constructor.
     */
    public GQLSearchResponse() {
    }

    /**
     * Sets sortBy.
     *
     * @param sortBy
     */
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    /**
     * Sets queryOffset.
     *
     * @param queryOffset
     */
    public void setQueryOffset(Integer queryOffset) {
        this.queryOffset = queryOffset;
    }

    /**
     * Sets queryMore.
     *
     * @param queryMore
     */
    public void setQueryMore(Boolean queryMore) {
        this.queryMore = ZmBoolean.fromBool(queryMore);
    }

    /**
     * Sets totalSize.
     *
     * @param totalSize
     */
    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    /**
     * Sets searchHits.
     *
     * @param searchHits
     */
    public void setSearchHits(Iterable <SearchHit> searchHits) {
        this.searchHits.clear();
        if (searchHits != null) {
            Iterables.addAll(this.searchHits,searchHits);
        }
    }


    /**
     * Add a SearchHit value.
     *
     * @param searchHit
     * @return this
     */
    public GQLSearchResponse addSearchHit(SearchHit searchHit) {
        this.searchHits.add(searchHit);
        return this;
    }

    /**
     * Sets queryInfos.
     *
     * @param queryInfos
     */
    public void setQueryInfos(Iterable <BaseQueryInfo> queryInfos) {
        this.queryInfos.clear();
        if (queryInfos != null) {
            Iterables.addAll(this.queryInfos,queryInfos);
        }
    }

    /**
     * Add a queryInfos value.
     *
     * @param queryInfo
     * @return
     */
    public GQLSearchResponse addQueryInfo(BaseQueryInfo queryInfo) {
        this.queryInfos.add(queryInfo);
        return this;
    }

    /**
     * Get the sortBy value.
     *
     * @return sortBy
     */
    @GraphQLQuery(name="sortBy", description="What to sort by. Default is \"dateDesc\". \n\nPossible values: \n" +
        "`none`, `dateAsc`, `dateDesc`, `subjAsc`, `subjDesc`, `nameAsc`, `nameDesc`, `rcptAsc`, `rcptDesc`, `attachAsc`, `attachDesc`, `flagAsc`, `flagDesc`, `priorityAsc`, `priorityDesc`\n\nIf `sortBy` is \"none\" then cursors MUST NOT be used, and some searches are impossible (searches that require intersection of complex sub-ops). \nServer will throw an IllegalArgumentException if the search is invalid. \nADDITIONAL SORT MODES FOR TASKS: \nvalid only if types=\"task\" (and task alone): \n`taskDueAsc`, `taskDueDesc`, `taskStatusAsc`, `taskStatusDesc`, `taskPercCompletedAsc`, `taskPercCompletedDesc`")
    public String getSortBy() {
        return sortBy;
    }

    /**
     * Get the queryOffset value.
     *
     * @return queryOffset
     */
    @GraphQLQuery(name="queryOffset", description="Offset - an integer specifying the 0-based offset into the results list returned as the first result for this search operation.")
    public Integer getQueryOffset() {
        return queryOffset;
    }

    /**
     * Get the queryMore value.
     *
     * @return queryMore
     */
    @GraphQLQuery(name="queryMore", description="Set if there are more search results remaining.")
    public Boolean getQueryMore() {
        return ZmBoolean.toBool(queryMore);
    }

    /**
     * Get the totalSize value.
     *
     * @return totalSize
     */
    @GraphQLQuery(name="totalSize", description="All messages")
    public Long getTotalSize() {
        return totalSize;
    }

    /**
     * Get the queryInfos value.
     *
     * @return queryInfos
     */
    @GraphQLQuery(name="queryInfos", description="Info block. \nUsed to return general status information about your search. The `wildcard` element tells you about the status of `wildcard` expansions within your search. If `expanded` is set, then the `wildcard` was expanded and the matches are included in the search. If `expanded` is unset then the `wildcard` was not specific enough and therefore no `wildcard` matches are included (exact-match is included in results).")
    public List<BaseQueryInfo> getQueryInfos() {
        return Collections.unmodifiableList(queryInfos);
    }

    /**
     * Get the searchHits value.
     *
     * @return searchHits
     */
    @GraphQLQuery(name="searchHits", description="Search hits")
    public List<? extends SearchHit> getSearchHits() {
        return Collections.unmodifiableList(searchHits);
    }

}
