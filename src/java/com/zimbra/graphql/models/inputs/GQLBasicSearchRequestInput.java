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
import com.zimbra.soap.type.CursorInfo;

import io.leangen.graphql.annotations.GraphQLInputField;
import io.leangen.graphql.annotations.types.GraphQLType;

/**
 * The GQLBasicSearchRequestInput class.<br>
 * Contains search request common properties.
 */
@GraphQLType(name=GqlConstants.CLASS_BASIC_SEARCH_REQUEST, description="Common Inputs for search request.")
public class GQLBasicSearchRequestInput {

    protected Boolean quick;
    protected String sortBy;
    protected Integer limit;
    protected Integer offset;
    protected String locale;
    protected CursorInfo cursor;

    public Boolean getQuick() {
        return quick;
    }

    @GraphQLInputField(name = GqlConstants.QUICK, description="For performance reasons, skip indexing before search which lowers latencies. i.e. recent messages may not be included in the search results.")
    public void setQuick(Boolean quick) {
        this.quick = quick;
    }

    public String getSortBy() {
        return sortBy;
    }

    @GraphQLInputField(name = GqlConstants.SORT_BY, description="SortBy setting. Possible values: `none, dateAsc, dateDesc, subjAsc, subjDesc, nameAsc, nameDesc, rcptAsc, rcptDesc, attachAsc, attachDesc, flagAsc, flagDesc, priorityAsc, priorityDesc, idAsc, idDesc, readAsc, readDesc`. If `sortBy` is \"none\" then cursors MUST NOT be used, and some searches are impossible (searches that require intersection of complex sub-ops). Server will throw an IllegalArgumentException if the search is invalid. ADDITIONAL SORT MODES FOR TASKS: valid only if types=\"task\" (and task alone): `taskDueAsc, taskDueDesc, taskStatusAsc, taskStatusDesc, taskPercCompletedAsc, taskPercCompletedDesc`")
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Integer getLimit() {
        return limit;
    }

    @GraphQLInputField(name = GqlConstants.LIMIT, description="The maximum number of results to return. It defaults to 10 if not specified, and is capped by 1000")
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    @GraphQLInputField(name = GqlConstants.OFFSET, description="Specifies the 0-based offset into the results list to return as the first result for this search operation. For example, limit=10 offset=30 will return the 31st through 40th results inclusive.")
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getLocale() {
        return locale;
    }

    @GraphQLInputField(name = GqlConstants.LOCALE, description="Client locale identification. Value is of the form LL-CC[-V+]. LL 2 character language code; CC is 2 character country code; V+ is optional variant identifier string")
    public void setLocale(String locale) {
        this.locale = locale;
    }

    public CursorInfo getCursor() {
        return cursor;
    }

    @GraphQLInputField(name = GqlConstants.CURSOR, description="Cursor specification.")
    public void setCursor(CursorInfo cursor) {
        this.cursor = cursor;
    }
}
