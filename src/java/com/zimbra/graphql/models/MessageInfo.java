package com.zimbra.graphql.models;

import java.util.List;

/**
 * The MessageInfo class.
 * 
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class MessageInfo extends MailItem {

    /**
    * List of MIMEParts.
    */
    private List<MimePart> mimeParts;

    /**
    * 'to' email address list.
    */
    private List<EmailAddress> to;

    /**
    * 'from' email address list.
    */
    private List<EmailAddress> from;

    /**
    * 'cc' email address list.
    */
    private List<EmailAddress> cc;

    /**
    * 'bcc' email address list.
    */
    private List<EmailAddress> bcc;

    /**
    * 'sender' email address list.
    */
    private List<EmailAddress> sender;

    /**
    * The html.
    */
    private String html;

    /**
    * The text.
    */
    private String text;

    /**
    * The list of attachments.
    */
    private List<MimePart> attachments;

    /**
    * The list of in-line attachments.
    */
    private List<MimePart> inlineAttachments;

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
     * @return The 'to' list of email addresses
     */
    public List<EmailAddress> getTo() {
        return to;
    }

    /**
     * @param to The 'to' list of email addresses to set
     */
    public void setTo(List<EmailAddress> to) {
        this.to = to;
    }

    /**
     * @return The 'from' list of email addresses
     */
    public List<EmailAddress> getFrom() {
        return from;
    }

    /**
     * @param from The 'from' list of email addresses to set
     */
    public void setFrom(List<EmailAddress> from) {
        this.from = from;
    }

    /**
     * @return The 'cc' list of email addresses
     */
    public List<EmailAddress> getCc() {
        return cc;
    }

    /**
     * @param cc The 'cc' list of email addresses to set
     */
    public void setCc(List<EmailAddress> cc) {
        this.cc = cc;
    }

    /**
     * @return The 'bcc' list of email addresses
     */
    public List<EmailAddress> getBcc() {
        return bcc;
    }

    /**
     * @param bcc The 'bcc' list of email addresses to set
     */
    public void setBcc(List<EmailAddress> bcc) {
        this.bcc = bcc;
    }

    /**
     * @return The 'sender' list of email addresses
     */
    public List<EmailAddress> getSender() {
        return sender;
    }

    /**
     * @param sender The 'sender' list of email addresses to set
     */
    public void setSender(List<EmailAddress> sender) {
        this.sender = sender;
    }

    /**
     * @return The html
     */
    public String getHtml() {
        return html;
    }

    /**
     * @param html The html to set
     */
    public void setHtml(String html) {
        this.html = html;
    }

    /**
     * @return The text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text The text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return The attachments
     */
    public List<MimePart> getAttachments() {
        return attachments;
    }

    /**
     * @param attachments The attachments to set
     */
    public void setAttachments(List<MimePart> attachments) {
        this.attachments = attachments;
    }

    /**
     * @return The list of in-line attachments
     */
    public List<MimePart> getInlineAttachments() {
        return inlineAttachments;
    }

    /**
     * @param inlineAttachments The list of in-line attachments to set
     */
    public void setInlineAttachments(List<MimePart> inlineAttachments) {
        this.inlineAttachments = inlineAttachments;
    }

}