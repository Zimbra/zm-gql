package com.zimbra.graphql.models;

import io.leangen.graphql.annotations.types.GraphQLType;

/**
 * The ACLGrant class.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
@GraphQLType(name = "ACLGrant", description = "Access control level grant.")
public class GQLACLGrant {

    /**
     * The grant address.
     */
    private String address;

    /**
     * The grant permissions.
     */
    private String permissions;

    /**
     * The grant rights.
     */
    private String rights;

    /**
     * The grantee type.
     */
    private GQLGranteeType granteeType;

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
     * The expiry.
     */
    private Long expiry;

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
     * @return The grant rights
     */
    public String getRights() {
        return rights;
    }

    /**
     * @param rights The grant rights to set
     */
    public void setRights(String rights) {
        this.rights = rights;
    }

    /**
     * @return The grantee type
     */
    public GQLGranteeType getGranteeType() {
        return granteeType;
    }

    /**
     * @param granteeType The grantee type to set
     */
    public void setGranteeType(GQLGranteeType granteeType) {
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

    /**
     * @return The expiry
     */
    public Long getExpiry() {
        return expiry;
    }

    /**
     * @param expiry The expiry to set
     */
    public void setExpiry(Long expiry) {
        this.expiry = expiry;
    }

}
