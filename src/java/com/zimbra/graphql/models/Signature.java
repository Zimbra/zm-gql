package com.zimbra.graphql.models;

import java.util.List;

/**
 * The Signature class.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class Signature {

    /**
     * Signature id.
     */
    private String id;

    /**
     * Signature name.
     */
    private String name;

    /**
     * The list of content from SignatureContent.
     */
    private List<SignatureContent> content;

    /**
     * @return The signature's id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The signature id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The signature's name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The signature name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The signature list of contents
     */
    public List<SignatureContent> getContent() {
        return content;
    }

    /**
     * @param content The signature's list of contents to set
     */
    public void setContent(List<SignatureContent> content) {
        this.content = content;
    }

}
