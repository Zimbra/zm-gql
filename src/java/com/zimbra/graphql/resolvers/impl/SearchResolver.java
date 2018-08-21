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


import com.zimbra.common.service.ServiceException;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.models.inputs.GQLSearchRequestInput;
import com.zimbra.graphql.models.outputs.GQLConversationSearchResponse;
import com.zimbra.graphql.models.outputs.GQLMessageSearchResponse;
import com.zimbra.graphql.repositories.impl.ZXMLSearchRepository;

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
            @GraphQLNonNull @GraphQLArgument(name = "params") GQLSearchRequestInput searchInput,
            @GraphQLRootContext RequestContext context
            ) throws ServiceException {
        return searchRepository.messageSearch(context, searchInput);
    }

    @GraphQLQuery(description = "Search for conversations with the given properties.")
    public GQLConversationSearchResponse conversationSearch(
            @GraphQLNonNull @GraphQLArgument(name = "params") GQLSearchRequestInput searchInput,
            @GraphQLRootContext RequestContext context
            ) throws ServiceException {
        return searchRepository.conversationSearch(context, searchInput);
    }

}
