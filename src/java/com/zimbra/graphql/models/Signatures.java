package com.zimbra.graphql.models;

import java.util.List;

public class Signatures {
    private List<Signature> signature;

    public Signatures(List<Signature> signature) {
        this.signature = signature;
    }

    public List<Signature> getSignature() {
        return signature;
    }

    public void setSignature(List<Signature> signature) {
        this.signature = signature;
    }

}
