package com.zimbra.graphql.models;

import java.util.List;

public class Identities {
    private List<Identity> identity;

    public Identities(List<Identity> identity) {
        this.identity = identity;
    }

    public List<Identity> getIdentity() {
        return identity;
    }

    public void setIdentity(List<Identity> identity) {
        this.identity = identity;
    }
}
