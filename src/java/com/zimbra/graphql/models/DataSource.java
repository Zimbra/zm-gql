package com.zimbra.graphql.models;

import java.util.List;

/**
 * Define the DataSource object
 * 
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class DataSource {

    /**
     *  The id.
     */
    private String id;

    /**
     *  The connectionType.
     */
    private String connectionType;

    /**
     *  The defaultSignature.
     */
    private String defaultSignature;

    /**
     *  The emailAddress.
     */
    private String emailAddress;

    /**
     *  The value for l.
     */
    private String l;

    /**
     *  The forwardReplySignature.
     */
    private String forwardReplySignature;

    /**
     *  The fromDisplay.
     */
    private String fromDisplay;

    /**
     *  The host.
     */
    private String host;

    /**
     *  The importOnly.
     */
    private Boolean importOnly;

    /**
     *  The .
     */
    private Boolean isEnabled;

    /**
     *  The isEnabled.
     */
    private String name;

    /**
     *  The pollingInterval.
     */
    private Float pollingInterval;

    /**
     *  The port.
     */
    private String port;

    /**
     *  The replyToAddress.
     */
    private String replyToAddress;

    /**
     *  The replyToDisplay.
     */
    private String replyToDisplay;

    /**
     *  The smtpPort.
     */
    private String smtpPort;

    /**
     *  The useAddressForForwardReply.
     */
    private Boolean useAddressForForwardReply;

    /**
     *  The username.
     */
    private String username;

    /**
     *  The failingSince.
     */
    private String failingSince;

    /**
     *  The lastError.
     */
    private List<String> lastError;

    /**
     * Constructor for DataSource class.
     *
     * @param id The datasource id to set
     * @param connectionType The datasource connectionType to set
     * @param defaultSignature The datasource defaultSignature to set
     * @param emailAddress The datasource emailAddress to set
     * @param l The datasource l to set
     * @param forwardReplySignature The datasource forwardReplySignature to set
     * @param fromDisplay The datasource fromDisplay to set
     * @param host The datasource host to set
     * @param importOnly The datasource importOnly to set
     * @param isEnabled The datasource isEnabled to set
     * @param name The datasource name to set
     * @param pollingInterval The datasource pollingInterval to set
     * @param port The datasource port to set
     * @param replyToAddress The datasource replyToAddress to set
     * @param replyToDisplay The datasource replyToDisplay to set
     * @param smtpPort The datasource smtpPort to set
     * @param useAddressForForwardRepl The datasource useAddressForForwardRepl to sety
     * @param username The datasource username to set
     * @param failingSince The datasource failingSince to set
     * @param lastError The datasource lastError to set
     */
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
     * @return Returns the connectionType
     */
    public String getConnectionType() {
        return connectionType;
    }

    /**
     * @param Sets the connectionType
     */
    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    /**
     * @return Returns the defaultSignature
     */
    public String getDefaultSignature() {
        return defaultSignature;
    }

    /**
     * @param Sets the defaultSignature
     */
    public void setDefaultSignature(String defaultSignature) {
        this.defaultSignature = defaultSignature;
    }

    /**
     * @return Returns the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param Sets the emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return Returns the l
     */
    public String getL() {
        return l;
    }

    /**
     * @param Sets the l
     */
    public void setL(String l) {
        this.l = l;
    }

    /**
     * @return Returns the forwardReplySignature
     */
    public String getForwardReplySignature() {
        return forwardReplySignature;
    }

    /**
     * @param Sets the forwardReplySignature
     */
    public void setForwardReplySignature(String forwardReplySignature) {
        this.forwardReplySignature = forwardReplySignature;
    }

    /**
     * @return Returns the fromDisplay
     */
    public String getFromDisplay() {
        return fromDisplay;
    }

    /**
     * @param Sets the fromDisplay
     */
    public void setFromDisplay(String fromDisplay) {
        this.fromDisplay = fromDisplay;
    }

    /**
     * @return Returns the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param Sets the host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return Returns the importOnly
     */
    public Boolean getImportOnly() {
        return importOnly;
    }

    /**
     * @param Sets the importOnly
     */
    public void setImportOnly(Boolean importOnly) {
        this.importOnly = importOnly;
    }

    /**
     * @return Returns the isEnabled
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     * @param Sets the isEnabled
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
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
     * @return Returns the pollingInterval
     */
    public Float getPollingInterval() {
        return pollingInterval;
    }

    /**
     * @param Sets the pollingInterval
     */
    public void setPollingInterval(Float pollingInterval) {
        this.pollingInterval = pollingInterval;
    }

    /**
     * @return Returns the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param Sets the port
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @return Returns the replyToAddress
     */
    public String getReplyToAddress() {
        return replyToAddress;
    }

    /**
     * @param Sets the replyToAddress
     */
    public void setReplyToAddress(String replyToAddress) {
        this.replyToAddress = replyToAddress;
    }

    /**
     * @return Returns the replyToDisplay
     */
    public String getReplyToDisplay() {
        return replyToDisplay;
    }

    /**
     * @param Sets the replyToDisplay
     */
    public void setReplyToDisplay(String replyToDisplay) {
        this.replyToDisplay = replyToDisplay;
    }

    /**
     * @return Returns the smtpPort
     */
    public String getSmtpPort() {
        return smtpPort;
    }

    /**
     * @param Sets the smtpPort
     */
    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    /**
     * @return Returns the useAddressForForwardReply
     */
    public Boolean getUseAddressForForwardReply() {
        return useAddressForForwardReply;
    }

    /**
     * @param Sets the useAddressForForwardReply
     */
    public void setUseAddressForForwardReply(Boolean useAddressForForwardReply) {
        this.useAddressForForwardReply = useAddressForForwardReply;
    }

    /**
     * @return Returns the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param Sets the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return Returns the failingSince
     */
    public String getFailingSince() {
        return failingSince;
    }

    /**
     * @param Sets the failingSince
     */
    public void setFailingSince(String failingSince) {
        this.failingSince = failingSince;
    }

    /**
     * @return Returns the lastError
     */
    public List<String> getLastError() {
        return lastError;
    }

    /**
     * @param Sets the lastError
     */
    public void setLastError(List<String> lastError) {
        this.lastError = lastError;
    }
}
