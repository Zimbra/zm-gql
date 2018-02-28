package com.zimbra.graphql.models;

public class IdentityAttrs {
    private String zimbraPrefIdentityId;
    private String zimbraPrefDefaultSignatureId;
    private String zimbraPrefForwardReplySignatureId;
    private String zimbraPrefForwardReplyFormat;
    private String zimbraPrefFromAddress;
    private String zimbraPrefFromAddressType;
    private String zimbraPrefFromDisplay;
    private String zimbraPrefIdentityName;
    private String zimbraPrefMailSignatureStyle;
    private String zimbraPrefReplyToAddress;
    private String zimbraPrefReplyToDisplay;
    private Boolean zimbraPrefReplyToEnabled;
    private Boolean zimbraPrefSaveToSent;
    private String zimbraPrefSentMailFolder;

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

	public String getZimbraPrefIdentityId() {
		return zimbraPrefIdentityId;
	}

	public void setZimbraPrefIdentityId(String zimbraPrefIdentityId) {
		this.zimbraPrefIdentityId = zimbraPrefIdentityId;
	}

	public String getZimbraPrefDefaultSignatureId() {
		return zimbraPrefDefaultSignatureId;
	}

	public void setZimbraPrefDefaultSignatureId(String zimbraPrefDefaultSignatureId) {
		this.zimbraPrefDefaultSignatureId = zimbraPrefDefaultSignatureId;
	}

	public String getZimbraPrefForwardReplySignatureId() {
		return zimbraPrefForwardReplySignatureId;
	}

	public void setZimbraPrefForwardReplySignatureId(String zimbraPrefForwardReplySignatureId) {
		this.zimbraPrefForwardReplySignatureId = zimbraPrefForwardReplySignatureId;
	}

	public String getZimbraPrefForwardReplyFormat() {
		return zimbraPrefForwardReplyFormat;
	}

	public void setZimbraPrefForwardReplyFormat(String zimbraPrefForwardReplyFormat) {
		this.zimbraPrefForwardReplyFormat = zimbraPrefForwardReplyFormat;
	}

	public String getZimbraPrefFromAddress() {
		return zimbraPrefFromAddress;
	}

	public void setZimbraPrefFromAddress(String zimbraPrefFromAddress) {
		this.zimbraPrefFromAddress = zimbraPrefFromAddress;
	}

	public String getZimbraPrefFromAddressType() {
		return zimbraPrefFromAddressType;
	}

	public void setZimbraPrefFromAddressType(String zimbraPrefFromAddressType) {
		this.zimbraPrefFromAddressType = zimbraPrefFromAddressType;
	}

	public String getZimbraPrefFromDisplay() {
		return zimbraPrefFromDisplay;
	}

	public void setZimbraPrefFromDisplay(String zimbraPrefFromDisplay) {
		this.zimbraPrefFromDisplay = zimbraPrefFromDisplay;
	}

	public String getZimbraPrefIdentityName() {
		return zimbraPrefIdentityName;
	}

	public void setZimbraPrefIdentityName(String zimbraPrefIdentityName) {
		this.zimbraPrefIdentityName = zimbraPrefIdentityName;
	}

	public String getZimbraPrefMailSignatureStyle() {
		return zimbraPrefMailSignatureStyle;
	}

	public void setZimbraPrefMailSignatureStyle(String zimbraPrefMailSignatureStyle) {
		this.zimbraPrefMailSignatureStyle = zimbraPrefMailSignatureStyle;
	}

	public String getZimbraPrefReplyToAddress() {
		return zimbraPrefReplyToAddress;
	}

	public void setZimbraPrefReplyToAddress(String zimbraPrefReplyToAddress) {
		this.zimbraPrefReplyToAddress = zimbraPrefReplyToAddress;
	}

	public String getZimbraPrefReplyToDisplay() {
		return zimbraPrefReplyToDisplay;
	}

	public void setZimbraPrefReplyToDisplay(String zimbraPrefReplyToDisplay) {
		this.zimbraPrefReplyToDisplay = zimbraPrefReplyToDisplay;
	}

	public Boolean getZimbraPrefReplyToEnabled() {
		return zimbraPrefReplyToEnabled;
	}

	public void setZimbraPrefReplyToEnabled(Boolean zimbraPrefReplyToEnabled) {
		this.zimbraPrefReplyToEnabled = zimbraPrefReplyToEnabled;
	}

	public Boolean getZimbraPrefSaveToSent() {
		return zimbraPrefSaveToSent;
	}

	public void setZimbraPrefSaveToSent(Boolean zimbraPrefSaveToSent) {
		this.zimbraPrefSaveToSent = zimbraPrefSaveToSent;
	}

	public String getZimbraPrefSentMailFolder() {
		return zimbraPrefSentMailFolder;
	}

	public void setZimbraPrefSentMailFolder(String zimbraPrefSentMailFolder) {
		this.zimbraPrefSentMailFolder = zimbraPrefSentMailFolder;
	}
}
