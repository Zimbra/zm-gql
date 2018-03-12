package com.zimbra.graphql.models;

import java.util.List;

/**
 * The MimePart class.
 * 
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class MimePart {

    /**
    * Boolean if this MIMEPart contains the body of the message.
    */
    private Boolean body;

    /**
    * The file name.
    */
    private String filename;

    /**
    * The MIMEPart part.
    */
    private String part;

    /**
    * The contents of the MIMEPart.
    */
    private String content;

    /**
    * The content id.
    */
    private String contentId;

    /**
    * The content type.
    */
    private String contentType;

    /**
    * The content-disposition.
    */
    private String contentDisposition;

    /**
    * The size of the MIMEPart.
    */
    private Integer size;

    /**
    * The list of MIMEParts.
    */
    private List<MimePart> mimeParts;

    /**
    * The url.
    */
    private String url;

    /**
    * The message id.
    */
    private String messageId;

    /**
     * @return The body
     */
    public Boolean isBody() {
        return body;
    }

    /**
     * @param body The body to set
     */
    public void setBody(Boolean body) {
        this.body = body;
    }

    /**
     * @return The file name
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename The file name to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return The MIMEPart part
     */
    public String getPart() {
        return part;
    }

    /**
     * @param part The MIMEPart part to set
     */
    public void setPart(String part) {
        this.part = part;
    }

    /**
     * @return The content of the MIMEPart
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content The content of the MIMEPart to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return The content id
     */
    public String getContentId() {
        return contentId;
    }

    /**
     * @param contentId The content id to set
     */
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    /**
     * @return The content type
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @param contentType The content type to set
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * @return The content-disposition
     */
    public String getContentDisposition() {
        return contentDisposition;
    }

    /**
     * @param contentDisposition The content-disposition to set
     */
    public void setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }

    /**
     * @return The size of the MIMEPart
     */
    public Integer getSize() {
        return size;
    }

    /**
     * @param size The size of the MIMEPart to set
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * @return The list of MIMEParts
     */
    public List<MimePart> getMimeParts() {
        return mimeParts;
    }

    /**
     * @param mimeParts The list of MIMEParts to set
     */
    public void setMimeParts(List<MimePart> mimeParts) {
        this.mimeParts = mimeParts;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return The message id
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * @param messageId The message id to set
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

}