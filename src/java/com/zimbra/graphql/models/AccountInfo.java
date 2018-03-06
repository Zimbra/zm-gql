package com.zimbra.graphql.models;

/**
 * The AccountInfo class.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class AccountInfo {

    /**
     * Account id.
     */
    private String id;

    /**
     * Account name.
     */
    private String name;

    /**
     * Optional URL related to the account.
     */
    private String publicURL;

    /**
     * Rest information for the account.
     */
    private String rest;

    /**
     * Soap URL for the account.
     */
    private String soapURL;

    /**
     * Account version.
     */
    private String version;

    /**
     * Account list of identities.
     */
    private Identities identities;

    /**
     * Account data sources including pop, imap, and cal.
     */
    private DataSources dataSources;

    /**
     * List of account signatures which has id, name, and list of contents.
     */
    private Signatures signatures;

    /**
     * Object of attributes including displayName booleans for calendar and contact features.
     */
    private AccountInfoAttrs attrs;

    /**
     * Object of Zimbra preference information.
     */
    private Preferences prefs;

    /**
     * @return The account's id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The account's id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The account's name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The account's name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The account's public URL
     */
    public String getPublicURL() {
        return publicURL;
    }

    /**
     * @param publicURL The account's public URL to set
     */
    public void setPublicURL(String publicURL) {
        this.publicURL = publicURL;
    }

    /**
     * @return The account's REST
     */
    public String getRest() {
        return rest;
    }

    /**
     * @param rest The account's REST to set
     */
    public void setRest(String rest) {
        this.rest = rest;
    }

    /**
     * @return The account's soapURL
     */
    public String getSoapURL() {
        return soapURL;
    }

    /**
     * @param soapURL The account's soap URL to set
     */
    public void setSoapURL(String soapURL) {
        this.soapURL = soapURL;
    }

    /**
     * @return The account's version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version The account's version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return The account's identities
     */
    public Identities getIdentities() {
        return identities;
    }

    /**
     * @param identities The account's identities to set
     */
    public void setIdentities(Identities identities) {
        this.identities = identities;
    }

    /**
     * @return The account's data sources
     */
    public DataSources getDataSources() {
        return dataSources;
    }

    /**
     * @param dataSources The account's data sources to set
     */
    public void setDataSources(DataSources dataSources) {
        this.dataSources = dataSources;
    }

    /**
     * @return The account's signatures
     */
    public Signatures getSignatures() {
        return signatures;
    }

    /**
     * @param signatures The account's signatures to set
     */
    public void setSignatures(Signatures signatures) {
        this.signatures = signatures;
    }

    /**
     * @return The account's attributes
     */
    public AccountInfoAttrs getAttrs() {
        return attrs;
    }

    /**
     * @param attrs The account's attributes to set
     */
    public void setAttrs(AccountInfoAttrs attrs) {
        this.attrs = attrs;
    }

    /**
     * @return The account's preferences
     */
    public Preferences getPrefs() {
        return prefs;
    }

    /**
     * @param prefs The account's preferences to set
     */
    public void setPrefs(Preferences prefs) {
        this.prefs = prefs;
    }

}
