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
import com.zimbra.graphql.models.outputs.GQLWhiteBlackListResponse;
import com.zimbra.graphql.repositories.impl.ZXMLAccountRepository;
import com.zimbra.soap.account.message.ChangePasswordResponse;
import com.zimbra.soap.account.message.GetInfoResponse;
import com.zimbra.soap.account.type.NameId;
import com.zimbra.soap.account.type.Pref;
import com.zimbra.soap.account.type.Signature;
import com.zimbra.soap.type.AccountSelector;
import com.zimbra.soap.type.OpValue;

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
        accountRepository.accountEndSession(context, sessionId, clearCookies, clearAllSoapSessions, excludeCurrentSession);
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

    @GraphQLMutation(description="Change password.")
    public ChangePasswordResponse passwordChange(
        @GraphQLArgument(name = GqlConstants.ACCOUNT_SELECTOR, description =
            "Denotes the account for which password is to be changed.") AccountSelector accountInput,
        @GraphQLNonNull @GraphQLArgument(name = GqlConstants.OLD_PASSWORD,
            description = "The old password for this account).") String password,
        @GraphQLNonNull @GraphQLArgument(name = GqlConstants.NEW_PASSWORD,
            description = "The new password for this account.") String oldPassword,
        @GraphQLArgument(name = GqlConstants.VIRTUAL_HOST,
        description = "The virtual host to indicae domain for the account.") String virtualHost,
        @GraphQLNonNull @GraphQLRootContext RequestContext context) throws ServiceException {
        return accountRepository.changePassword(accountInput, password, oldPassword, virtualHost, context);
    }

    @GraphQLQuery(description="Retrieves white and black list entries")
    public GQLWhiteBlackListResponse whiteBlackList(@GraphQLRootContext RequestContext context) throws ServiceException {
        return accountRepository.whiteBlackList(context);
    }

    @GraphQLMutation(description="Modify white and black list entries.\n * "
        + "If no operation is present in a list, it means to remove all addresses in the list.")
    public Boolean whiteBlackListModify(
        @GraphQLArgument(name=GqlConstants.WHITE_LIST_ENTRIES, description="Whitelist entry operations") List<OpValue> whiteListEntries,
        @GraphQLArgument(name=GqlConstants.BLACK_LIST_ENTRIES, description="Blacklist entry operations") List<OpValue> blackListEntries,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return accountRepository.whiteBlackListModify(context, whiteListEntries, blackListEntries);
    }

    @GraphQLQuery(description="Get a list of signatures associated with the requester")
    public List<Signature> signatures(@GraphQLRootContext RequestContext context) throws ServiceException {
        return accountRepository.signatures(context);
    }

    @GraphQLMutation(description="Create a signature")
    public NameId signatureCreate(@GraphQLNonNull @GraphQLArgument(name=GqlConstants.SIGNATURE) Signature signature,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return accountRepository.signatureCreate(context, signature);
    }

    @GraphQLMutation(description="Modify a signature")
    public Boolean signatureModify(@GraphQLNonNull @GraphQLArgument(name=GqlConstants.ID) String id,
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.SIGNATURE) Signature signature,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return accountRepository.signatureModify(context, id, signature);
    }

    @GraphQLMutation(description="Delete a signature")
    public Boolean signatureDelete(@GraphQLNonNull @GraphQLArgument(name=GqlConstants.IDENTIFIER) NameId identifier,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return accountRepository.signatureDelete(context, identifier);
    }
}
