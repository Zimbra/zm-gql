package com.zimbra.graphql.models;

import io.leangen.graphql.annotations.types.GraphQLType;

/**
 * The GranteeType enum.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
@GraphQLType(name = "GranteeType", description = "Possible types of a Grantee.")
public enum GQLGranteeType {
    /**
     * Zimbra user.
     */
    usr,

    /**
     * Zimbra group (distribution list).
     */
    grp,

    /**
     * All authenticated users.
     */
    all,

    /**
     * Zimbra domain.
     */
    dom,

    /**
     * Grantee ID must match zimbraCOSId (Class of Service)
     */
    cos,

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
    key;

    public static GQLGranteeType fromByte(byte byteValue) {
        for (final GQLGranteeType gqlType : GQLGranteeType.values()) {
            if (((byte)(gqlType.ordinal()+1)) == byteValue) {
                return gqlType;
            }
        }
        return null;
    }
}
