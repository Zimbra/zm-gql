package com.zimbra.graphql.models;

import java.util.List;

public class DataSource {

    private String id;
    private String connectionType;
    private String defaultSignature;
    private String emailAddress;
    private String l;
    private String forwardReplySignature;
    private String fromDisplay;
    private String host;
    private Boolean importOnly;
    private Boolean isEnabled;
    private String name;
    private Float pollingInterval;
    private String port;
    private String replyToAddress;
    private String replyToDisplay;
    private String smtpPort;
    private Boolean useAddressForForwardReply;
    private String username;
    private String failingSince;
    private List<String> lastError;

    public DataSource(String id, String connectionType, String defaultSignature, String emailAddress, String l,
            String forwardReplySignature, String fromDisplay, String host, Boolean importOnly, Boolean isEnabled,
            String name, Float pollingInterval, String port, String replyToAddress, String replyToDisplay,
            String smtpPort, Boolean useAddressForForwardReply, String username, String failingSince,
            List<String> lastError) {
        this.id = id;
        this.connectionType = connectionType;
        this.defaultSignature = defaultSignature;
        this.emailAddress = emailAddress;
        this.l = l;
        this.forwardReplySignature = forwardReplySignature;
        this.fromDisplay = fromDisplay;
        this.host = host;
        this.importOnly = importOnly;
        this.isEnabled = isEnabled;
        this.name = name;
        this.pollingInterval = pollingInterval;
        this.port = port;
        this.replyToAddress = replyToAddress;
        this.replyToDisplay = replyToDisplay;
        this.smtpPort = smtpPort;
        this.useAddressForForwardReply = useAddressForForwardReply;
        this.username = username;
        this.failingSince = failingSince;
        this.lastError = lastError;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public String getDefaultSignature() {
        return defaultSignature;
    }

    public void setDefaultSignature(String defaultSignature) {
        this.defaultSignature = defaultSignature;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getForwardReplySignature() {
        return forwardReplySignature;
    }

    public void setForwardReplySignature(String forwardReplySignature) {
        this.forwardReplySignature = forwardReplySignature;
    }

    public String getFromDisplay() {
        return fromDisplay;
    }

    public void setFromDisplay(String fromDisplay) {
        this.fromDisplay = fromDisplay;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Boolean getImportOnly() {
        return importOnly;
    }

    public void setImportOnly(Boolean importOnly) {
        this.importOnly = importOnly;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPollingInterval() {
        return pollingInterval;
    }

    public void setPollingInterval(Float pollingInterval) {
        this.pollingInterval = pollingInterval;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getReplyToAddress() {
        return replyToAddress;
    }

    public void setReplyToAddress(String replyToAddress) {
        this.replyToAddress = replyToAddress;
    }

    public String getReplyToDisplay() {
        return replyToDisplay;
    }

    public void setReplyToDisplay(String replyToDisplay) {
        this.replyToDisplay = replyToDisplay;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public Boolean getUseAddressForForwardReply() {
        return useAddressForForwardReply;
    }

    public void setUseAddressForForwardReply(Boolean useAddressForForwardReply) {
        this.useAddressForForwardReply = useAddressForForwardReply;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFailingSince() {
        return failingSince;
    }

    public void setFailingSince(String failingSince) {
        this.failingSince = failingSince;
    }

    public List<String> getLastError() {
        return lastError;
    }

    public void setLastError(List<String> lastError) {
        this.lastError = lastError;
    }
}
