package com.zimbra.graphql.models;

import java.util.List;

import io.leangen.graphql.annotations.types.GraphQLType;

/**
 * The ACL class.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
@GraphQLType(name = "ACL", description = "Access Control Level.")
public class GQLACL {

    /**
     * A list of grants.
     */
    private List<GQLACLGrant> grant;

    /**
     * The internal expiry.
     */
    private Long internalExpiry;

    /**
     * The guest expiry.
     */
    private Long guestExpiry;

    /**
     * @return The list of grants
     */
    public List<GQLACLGrant> getGrant() {
        return grant;
    }

    /**
     * @param grant The grant to set
     */
    public void setGrant(List<GQLACLGrant> grant) {
        this.grant = grant;
    }

    public Long getInternalExpiry() {
        return internalExpiry;
    }

    public void setInternalExpiry(Long expiry) {
        this.internalExpiry = expiry;
    }

    public Long getGuestExpiry() {
        return guestExpiry;
    }


    public void setGuestExpiry(Long guestExpiry) {
        this.guestExpiry = guestExpiry;
    }

}
