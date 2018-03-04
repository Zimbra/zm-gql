package com.zimbra.graphql.models;

/**
 * The ACLGrant class.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class ACLGrant {

    /**
     * The grant address.
     */
    private String address;

    /**
     * The grant permissions.
     */
    private String permissions;

    /**
     * The grantee type.
     */
    private GranteeType granteeType;

    /**
     * The Zimbra id.
     */
    private String zimbraId;

    /**
     * The password.
     */
    private String password;

    /**
     * The key.
     */
    private String key;

    /**
     * @return The address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address The address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return The permissions
     */
    public String getPermissions() {
        return permissions;
    }

    /**
     * @param permissions The permissions to set
     */
    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    /**
     * @return The grantee type
     */
    public GranteeType getGranteeType() {
        return granteeType;
    }

    /**
     * @param granteeType The grantee type to set
     */
    public void setGranteeType(GranteeType granteeType) {
        this.granteeType = granteeType;
    }

    /**
     * @return The Zimbra id
     */
    public String getZimbraId() {
        return zimbraId;
    }

    /**
     * @param zimbraId The Zimbra id to set
     */
    public void setZimbraId(String zimbraId) {
        this.zimbraId = zimbraId;
    }

    /**
     * @return The password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password The password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return The key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key The key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

}
