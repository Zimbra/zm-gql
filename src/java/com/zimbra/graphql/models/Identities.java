package com.zimbra.graphql.models;

import java.util.List;

/**
 * Define the Identities object
 * 
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class Identities {

    /**
     *  The identity list.
     */
    private List<Identity> identity;

    /**
     * Constructor for Identities class.
     *
     * @param identity The identity list to set 
     */
    public Identities(List<Identity> identity) {
        this.identity = identity;
    }

    /**
     * @return Returns the identity
     */
    public List<Identity> getIdentity() {
        return identity;
    }

    /**
     * @param Sets the identity
     */
    public void setIdentity(List<Identity> identity) {
        this.identity = identity;
    }
}
