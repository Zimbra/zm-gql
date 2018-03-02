package com.zimbra.graphql.models;

/**
 * SignatureContent class
 * 
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class SignatureContent {

    /**
     *  The type.
     */
    private String type;

    /**
     *  The _content.
     */
    private String _content;

	/**
	 * Constructor for signatureContent class.
	 * 
     * @param type The siganture's type to set
     * @param _content The siganture's _content to set
     */
    public SignatureContent(String type, String _content) {
        this.type = type;
        this._content = _content;
    }

    /**
     * @return Returns the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param Sets the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return Returns the _content value
     */
    public String get_content() {
        return _content;
    }

    /**
     * @param Sets the _content value
     */
    public void set_content(String _content) {
        this._content = _content;
    }
}
