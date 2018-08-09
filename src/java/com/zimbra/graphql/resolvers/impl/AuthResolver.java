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
import com.zimbra.graphql.models.AuthContext;
import com.zimbra.graphql.models.inputs.GQLAuthRequestInput;
import com.zimbra.graphql.repositories.impl.ZXMLAuthRepository;
import com.zimbra.soap.account.message.AuthResponse;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLRootContext;

/**
 * The AuthResolver class.<br>
 * Contains auth schema resources.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.resolvers.impl
 * @copyright Copyright © 2018
 */
public class AuthResolver {

    protected ZXMLAuthRepository authRepository = null;

    /**
     * Creates an instance with specified auth repository.
     *
     * @param authRepository The auth repository
     */
    public AuthResolver(ZXMLAuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @GraphQLMutation(description = "Authenticate for an account.")
    public AuthResponse authenticate(
        @GraphQLNonNull @GraphQLArgument(name = "authInput") GQLAuthRequestInput authRequestInput,
        @GraphQLRootContext AuthContext context) throws ServiceException {
        return authRepository.authenticate(context, authRequestInput);
    }

}