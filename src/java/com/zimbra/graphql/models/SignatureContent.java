package com.zimbra.graphql.models;

/**
 * The SignatureContent class.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class SignatureContent {

    /**
     * Signature type.
     */
    private String type;

    /**
     * Signature content.
     */
    private String _content;

    /**
     * @return The type of signature
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The signature type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The signature content value
     */
    public String get_content() {
        return _content;
    }

    /**
     * @param _content The signature content to set
     */
    public void set_content(String _content) {
        this._content = _content;
    }

}
