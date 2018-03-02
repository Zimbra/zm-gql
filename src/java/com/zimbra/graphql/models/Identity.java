package com.zimbra.graphql.models;

/**
 * Define the Identity object
 * 
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class Identity {

    /**
     *  The id.
     */
    private String id;

    /**
     *  The name.
     */
    private String name;

    /**
     *  The attributes for Identity.
     */
    private IdentityAttrs _attrs;

    /**
     *  The defaultSignature.
     */
    private String defaultSignature;

    /**
     * Constructor for Identity class.
     *
     * @param id The identity's id to set
     * @param name The identity's name to set
     * @param _attrs The identity's list of _attrs to set
     * @param defaultSignature The identity's defaultSignature to set
     */
    public Identity(String id, String name, IdentityAttrs _attrs, String defaultSignature) {
        this.id = id;
        this.name = name;
        this._attrs = _attrs;
        this.defaultSignature = defaultSignature;
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
     * @return Returns the _attrs
     */
    public IdentityAttrs get_attrs() {
        return _attrs;
    }

    /**
     * @param Sets the _attrs
     */
    public void set_attrs(IdentityAttrs _attrs) {
        this._attrs = _attrs;
    }

    /**
     * @return Returns the defaultSignature
     */
    public String getDefaultSignature() {
        return defaultSignature;
    }

    /**
     * @param Sets the defaultSignature
     */
    public void setDefaultSignature(String defaultSignature) {
        this.defaultSignature = defaultSignature;
    }
}
