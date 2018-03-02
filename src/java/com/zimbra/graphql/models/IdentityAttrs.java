package com.zimbra.graphql.models;

/**
 * Define the IdentityAttrs object
 * 
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class IdentityAttrs {

    /**
     *  The zimbraPrefIdentityId.
     */
    private String zimbraPrefIdentityId;

    /**
     *  The zimbraPrefDefaultSignatureId.
     */
    private String zimbraPrefDefaultSignatureId;

    /**
     *  The zimbraPrefForwardReplySignatureId.
     */
    private String zimbraPrefForwardReplySignatureId;

    /**
     *  The zimbraPrefForwardReplyFormat.
     */
    private String zimbraPrefForwardReplyFormat;

    /**
     *  The zimbraPrefFromAddress.
     */
    private String zimbraPrefFromAddress;

    /**
     *  The zimbraPrefFromAddressType.
     */
    private String zimbraPrefFromAddressType;

    /**
     *  The zimbraPrefFromDisplay.
     */
    private String zimbraPrefFromDisplay;

    /**
     *  The zimbraPrefIdentityName.
     */
    private String zimbraPrefIdentityName;

    /**
     *  The zimbraPrefMailSignatureStyle.
     */
    private String zimbraPrefMailSignatureStyle;

    /**
     *  The zimbraPrefReplyToAddress.
     */
    private String zimbraPrefReplyToAddress;

    /**
     *  The zimbraPrefReplyToDisplay.
     */
    private String zimbraPrefReplyToDisplay;

    /**
     *  The zimbraPrefReplyToEnabled.
     */
    private Boolean zimbraPrefReplyToEnabled;

    /**
     *  The zimbraPrefSaveToSent.
     */
    private Boolean zimbraPrefSaveToSent;

    /**
     *  The zimbraPrefSentMailFolder.
     */
    private String zimbraPrefSentMailFolder;

    /**
     * Constructor for IdentityAttrs class.
     *
     * @param zimbraPrefIdentityId The attribute's zimbraPrefIdentityId to set
     * @param zimbraPrefDefaultSignatureId The attribute's zimbraPrefDefaultSignatureId to set
     * @param zimbraPrefForwardReplySignatureId The attribute's zimbraPrefForwardReplySignatureId to set
     * @param zimbraPrefForwardReplyFormat The attribute's zimbraPrefForwardReplyFormat to set
     * @param zimbraPrefFromAddress The attribute's zimbraPrefFromAddress to set
     * @param zimbraPrefFromAddressType The attribute's zimbraPrefFromAddressType to set
     * @param zimbraPrefFromDisplay The attribute's zimbraPrefFromDisplay to set
     * @param zimbraPrefIdentityName The attribute's zimbraPrefIdentityName to set
     * @param zimbraPrefMailSignatureStyle The attribute's zimbraPrefMailSignatureStyle to set
     * @param zimbraPrefReplyToAddress The attribute's zimbraPrefReplyToAddress to set
     * @param zimbraPrefReplyToDisplay The attribute's zimbraPrefReplyToDisplay to set
     * @param zimbraPrefReplyToEnabled The attribute's zimbraPrefReplyToEnabled to set
     * @param zimbraPrefSaveToSent The attribute's zimbraPrefSaveToSent to set
     * @param zimbraPrefSentMailFolder The attribute's zimbraPrefSentMailFolder to set
     */
    public IdentityAttrs(String zimbraPrefIdentityId, String zimbraPrefDefaultSignatureId,
            String zimbraPrefForwardReplySignatureId, String zimbraPrefForwardReplyFormat, String zimbraPrefFromAddress,
            String zimbraPrefFromAddressType, String zimbraPrefFromDisplay, String zimbraPrefIdentityName,
            String zimbraPrefMailSignatureStyle, String zimbraPrefReplyToAddress, String zimbraPrefReplyToDisplay,
            Boolean zimbraPrefReplyToEnabled, Boolean zimbraPrefSaveToSent, String zimbraPrefSentMailFolder) {
        this.zimbraPrefIdentityId = zimbraPrefIdentityId;
        this.zimbraPrefDefaultSignatureId = zimbraPrefDefaultSignatureId;
        this.zimbraPrefForwardReplySignatureId = zimbraPrefForwardReplySignatureId;
        this.zimbraPrefForwardReplyFormat = zimbraPrefForwardReplyFormat;
        this.zimbraPrefFromAddress = zimbraPrefFromAddress;
        this.zimbraPrefFromAddressType = zimbraPrefFromAddressType;
        this.zimbraPrefFromDisplay = zimbraPrefFromDisplay;
        this.zimbraPrefIdentityName = zimbraPrefIdentityName;
        this.zimbraPrefMailSignatureStyle = zimbraPrefMailSignatureStyle;
        this.zimbraPrefReplyToAddress = zimbraPrefReplyToAddress;
        this.zimbraPrefReplyToDisplay = zimbraPrefReplyToDisplay;
        this.zimbraPrefReplyToEnabled = zimbraPrefReplyToEnabled;
        this.zimbraPrefSaveToSent = zimbraPrefSaveToSent;
        this.zimbraPrefSentMailFolder = zimbraPrefSentMailFolder;
    }

    /**
     * @return Returns the zimbraPrefIdentityId
     */
    public String getZimbraPrefIdentityId() {
        return zimbraPrefIdentityId;
    }

    /**
     * @param Sets the zimbraPrefIdentityId
     */
    public void setZimbraPrefIdentityId(String zimbraPrefIdentityId) {
        this.zimbraPrefIdentityId = zimbraPrefIdentityId;
    }

    /**
     * @return Returns the zimbraPrefDefaultSignatureId
     */
    public String getZimbraPrefDefaultSignatureId() {
        return zimbraPrefDefaultSignatureId;
    }

    /**
     * @param Sets the zimbraPrefDefaultSignatureId
     */
    public void setZimbraPrefDefaultSignatureId(String zimbraPrefDefaultSignatureId) {
        this.zimbraPrefDefaultSignatureId = zimbraPrefDefaultSignatureId;
    }

    /**
     * @return Returns the zimbraPrefForwardReplySignatureId
     */
    public String getZimbraPrefForwardReplySignatureId() {
        return zimbraPrefForwardReplySignatureId;
    }

    /**
     * @param Sets the zimbraPrefForwardReplySignatureId
     */
    public void setZimbraPrefForwardReplySignatureId(String zimbraPrefForwardReplySignatureId) {
        this.zimbraPrefForwardReplySignatureId = zimbraPrefForwardReplySignatureId;
    }

    /**
     * @return Returns the zimbraPrefForwardReplyFormat
     */
    public String getZimbraPrefForwardReplyFormat() {
        return zimbraPrefForwardReplyFormat;
    }

    /**
     * @param Sets the zimbraPrefForwardReplyFormat
     */
    public void setZimbraPrefForwardReplyFormat(String zimbraPrefForwardReplyFormat) {
        this.zimbraPrefForwardReplyFormat = zimbraPrefForwardReplyFormat;
    }

    /**
     * @return Returns the zimbraPrefFromAddress
     */
    public String getZimbraPrefFromAddress() {
        return zimbraPrefFromAddress;
    }

    /**
     * @param Sets the zimbraPrefFromAddress
     */
    public void setZimbraPrefFromAddress(String zimbraPrefFromAddress) {
        this.zimbraPrefFromAddress = zimbraPrefFromAddress;
    }

    /**
     * @return Returns the zimbraPrefFromAddressType
     */
    public String getZimbraPrefFromAddressType() {
        return zimbraPrefFromAddressType;
    }

    /**
     * @param Sets the zimbraPrefFromAddressType
     */
    public void setZimbraPrefFromAddressType(String zimbraPrefFromAddressType) {
        this.zimbraPrefFromAddressType = zimbraPrefFromAddressType;
    }

    /**
     * @return Returns the zimbraPrefFromDisplay
     */
    public String getZimbraPrefFromDisplay() {
        return zimbraPrefFromDisplay;
    }

    /**
     * @param Sets the zimbraPrefFromDisplay
     */
    public void setZimbraPrefFromDisplay(String zimbraPrefFromDisplay) {
        this.zimbraPrefFromDisplay = zimbraPrefFromDisplay;
    }

    /**
     * @return Returns the zimbraPrefIdentityName
     */
    public String getZimbraPrefIdentityName() {
        return zimbraPrefIdentityName;
    }

    /**
     * @param Sets the zimbraPrefIdentityName
     */
    public void setZimbraPrefIdentityName(String zimbraPrefIdentityName) {
        this.zimbraPrefIdentityName = zimbraPrefIdentityName;
    }

    /**
     * @return Returns the zimbraPrefMailSignatureStyle
     */
    public String getZimbraPrefMailSignatureStyle() {
        return zimbraPrefMailSignatureStyle;
    }

    /**
     * @param Sets the zimbraPrefMailSignatureStyle
     */
    public void setZimbraPrefMailSignatureStyle(String zimbraPrefMailSignatureStyle) {
        this.zimbraPrefMailSignatureStyle = zimbraPrefMailSignatureStyle;
    }

    /**
     * @return Returns the zimbraPrefReplyToAddress
     */
    public String getZimbraPrefReplyToAddress() {
        return zimbraPrefReplyToAddress;
    }

    /**
     * @param Sets the zimbraPrefReplyToAddress
     */
    public void setZimbraPrefReplyToAddress(String zimbraPrefReplyToAddress) {
        this.zimbraPrefReplyToAddress = zimbraPrefReplyToAddress;
    }

    /**
     * @return Returns the zimbraPrefReplyToDisplay
     */
    public String getZimbraPrefReplyToDisplay() {
        return zimbraPrefReplyToDisplay;
    }

    /**
     * @param Sets the zimbraPrefReplyToDisplay
     */
    public void setZimbraPrefReplyToDisplay(String zimbraPrefReplyToDisplay) {
        this.zimbraPrefReplyToDisplay = zimbraPrefReplyToDisplay;
    }

    /**
     * @return Returns the zimbraPrefReplyToEnabled
     */
    public Boolean getZimbraPrefReplyToEnabled() {
        return zimbraPrefReplyToEnabled;
    }

    /**
     * @param Sets the zimbraPrefReplyToEnabled
     */
    public void setZimbraPrefReplyToEnabled(Boolean zimbraPrefReplyToEnabled) {
        this.zimbraPrefReplyToEnabled = zimbraPrefReplyToEnabled;
    }

    /**
     * @return Returns the zimbraPrefSaveToSent
     */
    public Boolean getZimbraPrefSaveToSent() {
        return zimbraPrefSaveToSent;
    }

    /**
     * @param Sets the zimbraPrefSaveToSent
     */
    public void setZimbraPrefSaveToSent(Boolean zimbraPrefSaveToSent) {
        this.zimbraPrefSaveToSent = zimbraPrefSaveToSent;
    }

    /**
     * @return Returns the zimbraPrefSentMailFolder
     */
    public String getZimbraPrefSentMailFolder() {
        return zimbraPrefSentMailFolder;
    }

    /**
     * @param Sets the zimbraPrefSentMailFolder
     */
    public void setZimbraPrefSentMailFolder(String zimbraPrefSentMailFolder) {
        this.zimbraPrefSentMailFolder = zimbraPrefSentMailFolder;
    }
}
