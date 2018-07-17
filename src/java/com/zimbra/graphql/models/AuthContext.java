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
package com.zimbra.graphql.models;

import com.zimbra.cs.account.Account;
import com.zimbra.cs.account.AuthToken;
import com.zimbra.cs.mailbox.OperationContext;

import io.leangen.graphql.annotations.GraphQLQuery;

public class AuthContext {

    /**
     * Request auth token.
     */
    protected AuthToken authToken;

    /**
     * Request account.
     */
    protected Account account;

    /**
     * Request operation context.
     */
    protected OperationContext operationContext;

    @GraphQLQuery(name = "authToken", description = "The authorization token.")
    public AuthToken getAuthToken() {
        return authToken;
    }

    /**
     * @param authToken The auth token to set
     */
    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    @GraphQLQuery(name = "account", description = "The authorized account.")
    public Account getAccount() {
        return account;
    }

    /**
     * @param account The account to set
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    @GraphQLQuery(name = "operationContext", description = "The operation context information.")
    public OperationContext getOperationContext() {
        return operationContext;
    }

    /**
     * @param operationContext The operation context to set
     */
    public void setOperationContext(OperationContext operationContext) {
        this.operationContext = operationContext;
    }

}
