package com.zimbra.graphql.models;

import java.util.List;

/**
 * The Signatures class.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class Signatures {

    /**
     * Signature list from Signature.
     */
    private List<Signature> signature;

    /**
     * @return The signature list
     */
    public List<Signature> getSignature() {
        return signature;
    }

    /**
     * @param signature The signature list to set
     */
    public void setSignature(List<Signature> signature) {
        this.signature = signature;
    }

}
