package com.zimbra.graphql.models;

import com.zimbra.cs.account.Account;
import com.zimbra.cs.account.AuthToken;

import io.leangen.graphql.annotations.GraphQLQuery;

public class AuthContext {

    protected AuthToken authToken;

    protected Account account;

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

}
