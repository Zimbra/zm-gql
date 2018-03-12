package com.zimbra.graphql.models.inputs;

/**
 * The EmailAddressInput class.
 * 
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models.inputs
 * @copyright Copyright © 2018
 */
public class EmailAddressInput {

    /**
    * Email address.
    */
    private String email;

    /**
    * Name related to this email address.
    */
    private String name;

    /**
    * Short name related to this email address.
    */
    private String shortName;

    /**
     * @return The email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The email address to set
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return The short name related to this email address
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @param shortName The short name related to this email address to set
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

}