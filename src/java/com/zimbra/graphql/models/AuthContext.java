package com.zimbra.graphql.models;

import com.zimbra.cs.account.Account;
import com.zimbra.cs.account.AuthToken;
import com.zimbra.cs.mailbox.OperationContext;

import io.leangen.graphql.annotations.GraphQLQuery;

public class AuthContext {

    protected AuthToken authToken;

    protected Account account;

    protected OperationContext operationContext;

    @GraphQLQuery(name = "authToken", description = "The authorization token.")
    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    @GraphQLQuery(name = "account", description = "The authorized account.")
    public Account getAccount() {
        return account;
    }


    public void setAccount(Account account) {
        this.account = account;
    }

    @GraphQLQuery(name = "operationContext", description = "The operation context information.")
    public OperationContext getOperationContext() {
        return operationContext;
    }


    public void setOperationContext(OperationContext operationContext) {
        this.operationContext = operationContext;
    }

}
