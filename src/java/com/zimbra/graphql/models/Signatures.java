package com.zimbra.graphql.models;

import java.util.List;

/**
 * Define the Signatures object
 * 
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class Signatures {

    /**
     *  The signature list from Signature.
     */
    private List<Signature> signature;

    /**
     * Constructor for Signatures class.
     * Gets and Sets lists based on the Signature class.
     *
     * @param signature The list of signatures to set
     */
    public Signatures(List<Signature> signature) {
        this.signature = signature;
    }

    /**
     * @return Returns the signature list
     */
    public List<Signature> getSignature() {
        return signature;
    }

    /**
     * @param Sets the signature list
     */
    public void setSignature(List<Signature> signature) {
        this.signature = signature;
    }
}
