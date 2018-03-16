package com.zimbra.graphql.models;

import java.util.List;

/**
 * The Identities class.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class Identities {

    /**
     * The identity list.
     */
    private List<Identity> identity;

    /**
     * @return The identity list
     */
    public List<Identity> getIdentity() {
        return identity;
    }

    /**
     * @param identity The identity list to set
     */
    public void setIdentity(List<Identity> identity) {
        this.identity = identity;
    }

}
