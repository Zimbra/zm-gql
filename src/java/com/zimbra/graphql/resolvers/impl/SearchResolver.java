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
package com.zimbra.graphql.resolvers.impl;


import com.zimbra.common.gql.GqlConstants;
import com.zimbra.common.service.ServiceException;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.models.inputs.GQLSearchRequestInput;
import com.zimbra.graphql.models.outputs.GQLAutoCompleteResponse;
import com.zimbra.graphql.models.outputs.GQLConversationSearchResponse;
import com.zimbra.graphql.models.outputs.GQLMessageSearchResponse;
import com.zimbra.graphql.repositories.impl.ZXMLSearchRepository;
import com.zimbra.soap.type.GalSearchType;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLRootContext;

/**
 * The SearchResolver class.<br>
 * Contains search schema resources.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.resolvers.impl
 * @copyright Copyright © 2018
 */
public class SearchResolver {

    protected ZXMLSearchRepository searchRepository = null;

    /**
     * Creates an instance with specified search repository.
     *
     * @param searchRepository The search repository
     */
    public SearchResolver(ZXMLSearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @GraphQLQuery(description = "Search for messages with the given properties.")
    public GQLMessageSearchResponse messageSearch(
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.SEARCH_PARAMS, description="Input parameters for the search") GQLSearchRequestInput searchInput,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return searchRepository.messageSearch(context, searchInput);
    }

    @GraphQLQuery(description = "Search for conversations with the given properties.")
    public GQLConversationSearchResponse conversationSearch(
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.SEARCH_PARAMS, description="Input parameters for the search") GQLSearchRequestInput searchInput,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return searchRepository.conversationSearch(context, searchInput);
    }

    @GraphQLQuery(description = "Search for auto-complete matches.")
    public GQLAutoCompleteResponse autoComplete(
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.NAME, description="Name") String name,
        @GraphQLArgument(name=GqlConstants.TYPE, description="Type of addresses to auto-complete on") GalSearchType type,
        @GraphQLArgument(name=GqlConstants.INCLUDE_IS_EXPANDABLE, description="Denotes whether to include `isExpandable` flag for group entries", defaultValue="false") Boolean includeIsExpandable,
        @GraphQLArgument(name=GqlConstants.FOLDERS, description="Comma-separated list of folder ids") String folders,
        @GraphQLArgument(name=GqlConstants.INCLUDE_GAL, description="Denotes whether to search the global address list") Boolean includeGal,
        @GraphQLRootContext RequestContext rctxt) throws ServiceException {
        return searchRepository.autoComplete(rctxt, name, type, includeIsExpandable, folders, includeGal);
    }

}
