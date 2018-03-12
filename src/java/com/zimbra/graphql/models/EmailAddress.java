package com.zimbra.graphql.models;

/**
 * The  class.
 * 
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class EmailAddress {

    /**
    * Email address.
    */
    private String address;

    /**
    * Name related to this email address.
    */
    private String name;

    /**
    * Type of email address.
    */
    private String type;

    /**
    * Display name of this email address.
    */
    private String displayName;

    /**
     * @return The email address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address The email address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return The name related to this email address
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name related to this email address to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The type of email address
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type of email address to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The display name for this email address
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName The display name for this email address to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}