/*
 * ***** BEGIN LICENSE BLOCK *****
 * Zimbra Collaboration Suite, Network Edition.
 * Copyright (C) 2013, 2014 Zimbra, Inc.  All Rights Reserved.
 * ***** END LICENSE BLOCK *****
 */
package com.zimbra.graphql.resolvers.impl;


import java.util.List;

import com.zimbra.common.service.ServiceException;
import com.zimbra.graphql.models.AuthContext;
import com.zimbra.graphql.models.inputs.GQLSearchRequestInput;
import com.zimbra.graphql.repositories.impl.ZXMLSearchRepository;
import com.zimbra.soap.admin.message.SearchMultiMailboxResponse;
import com.zimbra.soap.mail.message.SearchConvResponse;
import com.zimbra.soap.mail.message.SearchResponse;
import com.zimbra.soap.mail.type.ConversationHitInfo;
import com.zimbra.soap.mail.type.MessageHitInfo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
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

    @GraphQLQuery(description = "Retrieves search results for the given properties.")
    public List<MessageHitInfo> searchMessages(
            @GraphQLNonNull @GraphQLArgument(name = "params") GQLSearchRequestInput searchInput,
            @GraphQLRootContext AuthContext context
            ) throws ServiceException {
        return searchRepository.searchMessages(searchInput, context.getOperationContext(), context.getAccount());
    }

    @GraphQLQuery(description = "Retrieves search results for the given properties.")
    public List<ConversationHitInfo> searchConversations(
            @GraphQLNonNull @GraphQLArgument(name = "params") GQLSearchRequestInput searchInput,
            @GraphQLRootContext AuthContext context
            ) throws ServiceException {
        return searchRepository.searchConversations(searchInput, context.getOperationContext(), context.getAccount());
    }

}
