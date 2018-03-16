package com.zimbra.graphql.models;

/**
 * The Identity class.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class Identity {

    /**
     * Identity id.
     */
    private String id;

    /**
     * Identity name.
     */
    private String name;

    /**
     * The attributes for Identity.
     */
    private IdentityAttrs _attrs;

    /**
     * Default signature.
     */
    private String defaultSignature;

    /**
     * @return The id of the identity
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The name of the identity
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The identity's attributes
     */
    public IdentityAttrs get_attrs() {
        return _attrs;
    }

    /**
     * @param _attrs The identity's attributes to set
     */
    public void set_attrs(IdentityAttrs _attrs) {
        this._attrs = _attrs;
    }

    /**
     * @return The default signature
     */
    public String getDefaultSignature() {
        return defaultSignature;
    }

    /**
     * @param defaultSignature The default signature to set
     */
    public void setDefaultSignature(String defaultSignature) {
        this.defaultSignature = defaultSignature;
    }

}
