package com.zimbra.graphql.models;

/**
 * The IdentityAttrs class.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class IdentityAttrs {

    /**
     * The Zimbra preference for the account's identity id.
     */
    private String zimbraPrefIdentityId;

    /**
     * The Zimbra preference for the account's default signature id.
     */
    private String zimbraPrefDefaultSignatureId;

    /**
     * The Zimbra preference for the account's 'forward reply' signature id.
     */
    private String zimbraPrefForwardReplySignatureId;

    /**
     * The Zimbra preference for the account's format of 'forward reply'.
     */
    private String zimbraPrefForwardReplyFormat;

    /**
     * The Zimbra preference for the account's 'from address'.
     */
    private String zimbraPrefFromAddress;

    /**
     * The Zimbra preference for the account's 'from address' type.
     */
    private String zimbraPrefFromAddressType;

    /**
     * The Zimbra preference for the account's 'from' display.
     */
    private String zimbraPrefFromDisplay;

    /**
     * The Zimbra preference for the account's identity name.
     */
    private String zimbraPrefIdentityName;

    /**
     * The Zimbra preference for the account's mail signature style.
     */
    private String zimbraPrefMailSignatureStyle;

    /**
     * The Zimbra preference for the account's 'reply to' address.
     */
    private String zimbraPrefReplyToAddress;

    /**
     * The Zimbra preference for the account's display for 'reply to'.
     */
    private String zimbraPrefReplyToDisplay;

    /**
     * The Zimbra preference for the account's enabled value for 'reply to'.
     */
    private Boolean zimbraPrefReplyToEnabled;

    /**
     * The Zimbra preference for the account's 'save to sent' value.
     */
    private Boolean zimbraPrefSaveToSent;

    /**
     * The Zimbra preference for the account's 'sent mail' folder.
     */
    private String zimbraPrefSentMailFolder;

    /**
     * @return The account's Zimbra preference for identity id
     */
    public String getZimbraPrefIdentityId() {
        return zimbraPrefIdentityId;
    }

    /**
     * @param zimbraPrefIdentityId The Zimbra preference's identity id to set
     */
    public void setZimbraPrefIdentityId(String zimbraPrefIdentityId) {
        this.zimbraPrefIdentityId = zimbraPrefIdentityId;
    }

    /**
     * @return The account's Zimbra preference for the default signature id
     */
    public String getZimbraPrefDefaultSignatureId() {
        return zimbraPrefDefaultSignatureId;
    }

    /**
     * @param zimbraPrefDefaultSignatureId The Zimbra preference's default signature id to set
     */
    public void setZimbraPrefDefaultSignatureId(String zimbraPrefDefaultSignatureId) {
        this.zimbraPrefDefaultSignatureId = zimbraPrefDefaultSignatureId;
    }

    /**
     * @return The account's Zimbra preference for the 'forward reply' signature id
     */
    public String getZimbraPrefForwardReplySignatureId() {
        return zimbraPrefForwardReplySignatureId;
    }

    /**
     * @param zimbraPrefForwardReplySignatureId The Zimbra preference's 'forward reply' signature id to set
     */
    public void setZimbraPrefForwardReplySignatureId(String zimbraPrefForwardReplySignatureId) {
        this.zimbraPrefForwardReplySignatureId = zimbraPrefForwardReplySignatureId;
    }

    /**
     * @return The account's Zimbra preference for the 'forward reply' format
     */
    public String getZimbraPrefForwardReplyFormat() {
        return zimbraPrefForwardReplyFormat;
    }

    /**
     * @param zimbraPrefForwardReplyFormat The Zimbra preference's 'forward reply' format to set
     */
    public void setZimbraPrefForwardReplyFormat(String zimbraPrefForwardReplyFormat) {
        this.zimbraPrefForwardReplyFormat = zimbraPrefForwardReplyFormat;
    }

    /**
     * @return The account's Zimbra preference for the 'from' address
     */
    public String getZimbraPrefFromAddress() {
        return zimbraPrefFromAddress;
    }

    /**
     * @param zimbraPrefFromAddress The Zimbra preference's 'from' address to set
     */
    public void setZimbraPrefFromAddress(String zimbraPrefFromAddress) {
        this.zimbraPrefFromAddress = zimbraPrefFromAddress;
    }

    /**
     * @return The account's Zimbra preference for 'from address' type
     */
    public String getZimbraPrefFromAddressType() {
        return zimbraPrefFromAddressType;
    }

    /**
     * @param zimbraPrefFromAddressType The Zimbra preference's 'from address' type to set
     */
    public void setZimbraPrefFromAddressType(String zimbraPrefFromAddressType) {
        this.zimbraPrefFromAddressType = zimbraPrefFromAddressType;
    }

    /**
     * @return The account's Zimbra preference for the 'from' display
     */
    public String getZimbraPrefFromDisplay() {
        return zimbraPrefFromDisplay;
    }

    /**
     * @param zimbraPrefFromDisplay The Zimbra preference's 'from' display to set
     */
    public void setZimbraPrefFromDisplay(String zimbraPrefFromDisplay) {
        this.zimbraPrefFromDisplay = zimbraPrefFromDisplay;
    }

    /**
     * @return The account's Zimbra preference for the identity name
     */
    public String getZimbraPrefIdentityName() {
        return zimbraPrefIdentityName;
    }

    /**
     * @param zimbraPrefIdentityName The Zimbra preference's identity name to set
     */
    public void setZimbraPrefIdentityName(String zimbraPrefIdentityName) {
        this.zimbraPrefIdentityName = zimbraPrefIdentityName;
    }

    /**
     * @return The account's Zimbra preference for the mail's signature style
     */
    public String getZimbraPrefMailSignatureStyle() {
        return zimbraPrefMailSignatureStyle;
    }

    /**
     * @param zimbraPrefMailSignatureStyle The Zimbra preference's mail's signature style to set
     */
    public void setZimbraPrefMailSignatureStyle(String zimbraPrefMailSignatureStyle) {
        this.zimbraPrefMailSignatureStyle = zimbraPrefMailSignatureStyle;
    }

    /**
     * @return The account's Zimbra preference for the 'reply to' address
     */
    public String getZimbraPrefReplyToAddress() {
        return zimbraPrefReplyToAddress;
    }

    /**
     * @param zimbraPrefReplyToAddress The Zimbra preference's 'reply to' address to set
     */
    public void setZimbraPrefReplyToAddress(String zimbraPrefReplyToAddress) {
        this.zimbraPrefReplyToAddress = zimbraPrefReplyToAddress;
    }

    /**
     * @return The account's Zimbra preference for the 'reply to' display
     */
    public String getZimbraPrefReplyToDisplay() {
        return zimbraPrefReplyToDisplay;
    }

    /**
     * @param zimbraPrefReplyToDisplay The Zimbra preference's 'reply to' display to set
     */
    public void setZimbraPrefReplyToDisplay(String zimbraPrefReplyToDisplay) {
        this.zimbraPrefReplyToDisplay = zimbraPrefReplyToDisplay;
    }

    /**
     * @return The account's Zimbra preference for the enabled value of 'reply to'
     */
    public Boolean getZimbraPrefReplyToEnabled() {
        return zimbraPrefReplyToEnabled;
    }

    /**
     * @param zimbraPrefReplyToEnabled The Zimbra preference's enabled value of 'reply to' to set
     */
    public void setZimbraPrefReplyToEnabled(Boolean zimbraPrefReplyToEnabled) {
        this.zimbraPrefReplyToEnabled = zimbraPrefReplyToEnabled;
    }

    /**
     * @return The account's Zimbra preference for the enabled value of 'save to sent'
     */
    public Boolean getZimbraPrefSaveToSent() {
        return zimbraPrefSaveToSent;
    }

    /**
     * @param zimbraPrefSaveToSent The Zimbra preference's enabled value of 'save to sent' to set
     */
    public void setZimbraPrefSaveToSent(Boolean zimbraPrefSaveToSent) {
        this.zimbraPrefSaveToSent = zimbraPrefSaveToSent;
    }

    /**
     * @return The account's Zimbra preference for the 'sent mail' folder
     */
    public String getZimbraPrefSentMailFolder() {
        return zimbraPrefSentMailFolder;
    }

    /**
     * @param zimbraPrefSentMailFolder The Zimbra preference for the 'sent mail' folder to set
     */
    public void setZimbraPrefSentMailFolder(String zimbraPrefSentMailFolder) {
        this.zimbraPrefSentMailFolder = zimbraPrefSentMailFolder;
    }

}
