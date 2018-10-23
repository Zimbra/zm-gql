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

import java.util.List;

import com.zimbra.common.gql.GqlConstants;
import com.zimbra.common.service.ServiceException;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.models.inputs.GQLPrefInput;
import com.zimbra.graphql.models.outputs.AccountInfo;
import com.zimbra.graphql.repositories.impl.ZXMLAccountRepository;
import com.zimbra.soap.account.message.GetInfoResponse;
import com.zimbra.soap.account.type.Pref;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLRootContext;

/**
 * The AccountResolver class.<br>
 * Contains account schema resources.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.resolvers.impl
 * @copyright Copyright © 2018
 */
public class AccountResolver {

    protected ZXMLAccountRepository accountRepository = null;

    /**
     * Creates an instance with specified account repository.
     *
     * @param accountRepository The account repository
     */
    public AccountResolver(ZXMLAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GraphQLQuery(description="Retrieve account info")
    public AccountInfo accountInfoGet(@GraphQLRootContext RequestContext context)
        throws ServiceException {
        return accountRepository.accountInfoGet(context);
    }

    /**
     * @param context
     * @param sections
     * @param rights
     * @return GetInfoResponse
     * @throws ServiceException
     */
    @GraphQLQuery(description="Retrieve info")
    public GetInfoResponse info(@GraphQLRootContext RequestContext context,
            @GraphQLArgument(name=GqlConstants.SECTIONS,
                description="Comma separated list of sections to return information about") String sections,
            @GraphQLArgument(name=GqlConstants.RIGHTS,
                description="Comma separated list of rights to return information about") String rights)
        throws ServiceException {
        return accountRepository.info(context, sections, rights);
    }

    @GraphQLMutation(description="Logout/end session for current user")
    public void accountEndSession(
        @GraphQLArgument(name=GqlConstants.SESSION_ID, description="The id of a specific session to end") String sessionId,
        @GraphQLArgument(name=GqlConstants.CLEAR_COOKIES, description="Denotes whether to clear cookies", defaultValue="false") boolean clearCookies,
        @GraphQLArgument(name=GqlConstants.CLEAR_ALL_SOAP_SESSIONS, description="Denotes whether to clear all soap sessions", defaultValue="false") boolean clearAllSoapSessions,
        @GraphQLArgument(name=GqlConstants.EXCLUDE_CURRENT_SESSION, description="Denotes whether to retain current session, when clear all session is true", defaultValue="false") boolean excludeCurrentSession,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        accountRepository.accountEndSession(context, sessionId, clearCookies);
    }

    @GraphQLQuery(description="Retrieves prefs by given properties")
    public List<Pref> prefs(@GraphQLArgument(name=GqlConstants.PREFERENCES) List<Pref> prefs,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return accountRepository.prefs(context, prefs);
    }

    @GraphQLMutation(description="Modify listed prefs with given properties")
    public List<Pref> prefsModify(@GraphQLNonNull @GraphQLArgument(name=GqlConstants.PREFERENCES) List<GQLPrefInput> prefs,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return accountRepository.prefsModify(context, prefs);
    }
}
