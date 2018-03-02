package com.zimbra.graphql.models;

/**
 * Define the AccountInfo Object
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
     * Optional URL related to account.
     */
    private String publicURL;

    /**
     * Rest information for account.
     */
    private String rest;

    /**
     * SoapURL for account.
     */
    private String soapURL;

    /**
     *  The version.
     */
    private String version;

    /**
     * List of Identities for account.
     */
    private Identities identities;

    /**
     * Object of dependencies including pop, imap, and cal.
     */
    private DataSources dataSources;

    /**
     * List of signatures which has id, name, and list of contents.
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
     * Constructor for AccountInfo class.
     *
     * @param id The account id to set
     * @param name The account name to set
     * @param publicURL The account publicURL to set
     * @param rest The account rest to set
     * @param soapURL The account soapURL to set
     * @param version The Zimbra version to set
     * @param identities The account identities to set 
     * @param dataSources The account dataSources to set
     * @param signatures The account signatures to set
     * @param attrs The account attrs to set
     * @param prefs The account prefs to set
     */
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

    /**
     * @return Returns the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param Sets the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return Returns the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param Sets the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the publicURL
     */
    public String getPublicURL() {
        return publicURL;
    }

    /**
     * @param Sets the publicURL
     */
    public void setPublicURL(String publicURL) {
        this.publicURL = publicURL;
    }

    /**
     * @return Returns the rest
     */
    public String getRest() {
        return rest;
    }

    /**
     * @param Sets the rest
     */
    public void setRest(String rest) {
        this.rest = rest;
    }

    /**
     * @return Returns the soapURL
     */
    public String getSoapURL() {
        return soapURL;
    }

    /**
     * @param Sets the soapURL
     */
    public void setSoapURL(String soapURL) {
        this.soapURL = soapURL;
    }

    /**
     * @return Returns the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param Sets the version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return Returns the identities
     */
    public Identities getIdentities() {
        return identities;
    }

    /**
     * @param Sets the identities
     */
    public void setIdentities(Identities identities) {
        this.identities = identities;
    }

    /**
     * @return Returns the dataSources
     */
    public DataSources getDataSources() {
        return dataSources;
    }

    /**
     * @param Sets the dataSources
     */
    public void setDataSources(DataSources dataSources) {
        this.dataSources = dataSources;
    }

    /**
     * @return Returns the signatures
     */
    public Signatures getSignatures() {
        return signatures;
    }

    /**
     * @param Sets the signatures
     */
    public void setSignatures(Signatures signatures) {
        this.signatures = signatures;
    }

    /**
     * @return Returns the attrs
     */
    public AccountInfoAttrs getAttrs() {
        return attrs;
    }

    /**
     * @param Sets the attrs
     */
    public void setAttrs(AccountInfoAttrs attrs) {
        this.attrs = attrs;
    }

    /**
     * @return Returns the prefs
     */
    public Preferences getPrefs() {
        return prefs;
    }

    /**
     * @param Sets the prefs
     */
    public void setPrefs(Preferences prefs) {
        this.prefs = prefs;
    }
}
