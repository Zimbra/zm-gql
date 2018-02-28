package com.zimbra.graphql.models;

public class AccountInfo {

    private String id;
    private String name;
    private String publicURL;
    private String rest;
    private String soapURL;
    private String version;
    private Identities identities;
    private DataSources dataSources;
    private Signatures signatures;
    private AccountInfoAttrs attrs;
    private Preferences prefs;

    public AccountInfo(String id, String name, String publicURL, String rest, String soapURL, String version,
            Identities identities, DataSources dataSources, Signatures signatures, AccountInfoAttrs attrs,
            Preferences prefs) {
        this.id = id;
        this.name = name;
        this.publicURL = publicURL;
        this.rest = rest;
        this.soapURL = soapURL;
        this.version = version;
        this.identities = identities;
        this.dataSources = dataSources;
        this.signatures = signatures;
        this.attrs = attrs;
        this.prefs = prefs;
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

    public String getPublicURL() {
        return publicURL;
    }

    public void setPublicURL(String publicURL) {
        this.publicURL = publicURL;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }

    public String getSoapURL() {
        return soapURL;
    }

    public void setSoapURL(String soapURL) {
        this.soapURL = soapURL;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Identities getIdentities() {
        return identities;
    }

    public void setIdentities(Identities identities) {
        this.identities = identities;
    }

    public DataSources getDataSources() {
        return dataSources;
    }

    public void setDataSources(DataSources dataSources) {
        this.dataSources = dataSources;
    }

    public Signatures getSignatures() {
        return signatures;
    }

    public void setSignatures(Signatures signatures) {
        this.signatures = signatures;
    }

    public AccountInfoAttrs getAttrs() {
        return attrs;
    }

    public void setAttrs(AccountInfoAttrs attrs) {
        this.attrs = attrs;
    }

    public Preferences getPrefs() {
        return prefs;
    }

    public void setPrefs(Preferences prefs) {
        this.prefs = prefs;
    }
}
