package com.zimbra.graphql.models;

import java.util.List;

/**
 * Define the Signature object
 * 
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class Signature {

    /**
     *  The id.
     */
    private String id;

    /**
     *  The name.
     */
    private String name;

    /**
     *  The list of content from SignatureContent.
     */
    private List<SignatureContent> content;

    /**
     * Constructor for Signature class.
     *
     * @param id The signature's id to set
     * @param name The signature's name to set
     * @param content The signature's content to set
     */
    public Signature(String id, String name, List<SignatureContent> content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    /**
     * @return Returns the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param Sets the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return Returns the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param Sets the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the content list
     */
    public List<SignatureContent> getContent() {
        return content;
    }

    /**
     * @param Sets the content list
     */
    public void setContent(List<SignatureContent> content) {
        this.content = content;
    }
}
