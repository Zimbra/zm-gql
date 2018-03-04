package com.zimbra.graphql.models;

/**
 * The GranteeType enum.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public enum GranteeType {
    /**
     * Zimbra user.
     */
    usr,

    /**
     * Zimbra group (distribution list).
     */
    grp,

    /**
     * An external AD group.
     */
    egp,

    /**
     * Zimbra domain.
     */
    dom,

    /**
     * All authenticated users.
     */
    all,

    /**
     * Public access.
     */
    pub,

    /**
     * Non-zimbra email address and password.
     */
    guest,

    /**
     * Non-zimbra email access and access key.
     */
    key,

    /**
     * Grantee ID must match zimbraCOSId (Class of Service)
     */
    cos
}
