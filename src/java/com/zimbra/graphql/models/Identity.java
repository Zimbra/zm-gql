package com.zimbra.graphql.models;

public class Identity {
    private String id;
    private String name;
    private IdentityAttrs _attrs;
    private String defaultSignature;

    public Identity(String id, String name, IdentityAttrs _attrs, String defaultSignature) {
        this.id = id;
        this.name = name;
        this._attrs = _attrs;
        this.defaultSignature = defaultSignature;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IdentityAttrs get_attrs() {
        return _attrs;
    }

    public void set_attrs(IdentityAttrs _attrs) {
        this._attrs = _attrs;
    }

    public String getDefaultSignature() {
        return defaultSignature;
    }

    public void setDefaultSignature(String defaultSignature) {
        this.defaultSignature = defaultSignature;
    }
}
