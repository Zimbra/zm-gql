package com.zimbra.graphql.models;

import java.util.List;

/**
 * The ACL class.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class ACL {

    /**
     * A list of grants.
     */
    private List<ACLGrant> grant;

    /**
     * @return The list of grants
     */
    public List<ACLGrant> getGrant() {
        return grant;
    }

    /**
     * @param grant The grant to set
     */
    public void setGrant(List<ACLGrant> grant) {
        this.grant = grant;
    }

}
