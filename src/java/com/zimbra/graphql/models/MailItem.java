package com.zimbra.graphql.models;

import java.util.List;

/**
 * The MailItem class.
 * 
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public abstract class MailItem {

    /**
    * The MessageInfo id.
    */
    private String id;

    /**
    * The MessageInfo size.
    */
    private Float size;

    /**
    * The date.
    */
    private Float date;

    /**
    * Folder id.
    */
    private String folderId;

    /**
    * The subject.
    */
    private String subject;

    /**
    * List of email addresses.
    */
    private List<EmailAddress> emailAddresses;

    /**
    * The excerpt.
    */
    private String excerpt;

    /**
    * The conversation id.
    */
    private String conversationId;

    /**
    * The flags.
    */
    private String flags;

    /**
    * The tags.
    */
    private String tags;

    /**
    * The tag names.
    */
    private String tagNames;

    /**
    * The revision.
    */
    private Integer revision;

    /**
    * The change date.
    */
    private Float changeDate;

    /**
    * The modified sequence.
    */
    private Integer modifiedSequence;

    /**
    * The sorted field.
    */
    private String sortField;

    /**
     * @return The MessageInfo id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The MessageInfo id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The MessageInfo size
     */
    public Float getSize() {
        return size;
    }

    /**
     * @param size The MessageInfo size to set
     */
    public void setSize(Float size) {
        this.size = size;
    }

    /**
     * @return The date
     */
    public Float getDate() {
        return date;
    }

    /**
     * @param date The date to set
     */
    public void setDate(Float date) {
        this.date = date;
    }

    /**
     * @return The folder id
     */
    public String getFolderId() {
        return folderId;
    }

    /**
     * @param folderId The folder id to set
     */
    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    /**
     * @return The subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject The subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return The list of email addresses
     */
    public List<EmailAddress> getEmailAddresses() {
        return emailAddresses;
    }

    /**
     * @param emailAddresses The list of email addresses to set
     */
    public void setEmailAddresses(List<EmailAddress> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    /**
     * @return The excerpt
     */
    public String getExcerpt() {
        return excerpt;
    }

    /**
     * @param excerpt The excerpt to set
     */
    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    /**
     * @return The conversation id
     */
    public String getConversationId() {
        return conversationId;
    }

    /**
     * @param conversationId The conversation id to set
     */
    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    /**
     * @return The flags
     */
    public String getFlags() {
        return flags;
    }

    /**
     * @param flags The flags to set
     */
    public void setFlags(String flags) {
        this.flags = flags;
    }

    /**
     * @return The tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * @param tags The tags to set
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * @return The tag names
     */
    public String getTagNames() {
        return tagNames;
    }

    /**
     * @param tagNames The tag names to set
     */
    public void setTagNames(String tagNames) {
        this.tagNames = tagNames;
    }

    /**
     * @return The revision
     */
    public Integer getRevision() {
        return revision;
    }

    /**
     * @param revision The revision to set
     */
    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    /**
     * @return The change date
     */
    public Float getChangeDate() {
        return changeDate;
    }

    /**
     * @param changeDate The change date to set
     */
    public void setChangeDate(Float changeDate) {
        this.changeDate = changeDate;
    }

    /**
     * @return The modified sequence
     */
    public Integer getModifiedSequence() {
        return modifiedSequence;
    }

    /**
     * @param modifiedSequence The modified sequence to set
     */
    public void setModifiedSequence(Integer modifiedSequence) {
        this.modifiedSequence = modifiedSequence;
    }

    /**
     * @return The sort field
     */
    public String getSortField() {
        return sortField;
    }

    /**
     * @param sortField The sort field to set
     */
    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

}
