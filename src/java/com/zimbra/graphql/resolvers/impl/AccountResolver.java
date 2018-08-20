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
import com.zimbra.graphql.models.outputs.AccountInfo;
import com.zimbra.graphql.repositories.impl.ZXMLAccountRepository;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
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

    @GraphQLQuery(description = "Retrieve account info")
    public AccountInfo accountInfoGet(@GraphQLRootContext RequestContext context)
        throws ServiceException {
        return accountRepository.accountInfoGet(context);
    }

    @GraphQLMutation(description="Logout/end session for current user")
    public void accountEndSession(
        @GraphQLArgument(name = GqlConstants.CLEAR_COOKIES, description = "Denotes whether to clear cookies") boolean clearCookies,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        accountRepository.accountEndSession(context, clearCookies);
    }
}