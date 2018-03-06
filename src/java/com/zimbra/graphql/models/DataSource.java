package com.zimbra.graphql.models;

import java.util.List;

/**
 * The DataSource class.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class DataSource {

    /**
     * DataSource id.
     */
    private String id;

    /**
     * Connection type.
     */
    private String connectionType;

    /**
     * Default signature.
     */
    private String defaultSignature;

    /**
     * Email address.
     */
    private String emailAddress;

    /**
     * The value for l.
     */
    private String l;

    /**
     * The forward reply signature.
     */
    private String forwardReplySignature;

    /**
     * The fromDisplay.
     */
    private String fromDisplay;

    /**
     * DataSource host.
     */
    private String host;

    /**
     * Boolean value if DataSource is only to be imported.
     */
    private Boolean importOnly;

    /**
     * Boolean value if DataSource is Enabled.
     */
    private Boolean isEnabled;

    /**
     * DataSource name.
     */
    private String name;

    /**
     * Polling interval.
     */
    private Float pollingInterval;

    /**
     * DataSource port.
     */
    private String port;

    /**
     * Reply-to address.
     */
    private String replyToAddress;

    /**
     * Reply-to display.
     */
    private String replyToDisplay;

    /**
     * DataSource smtp port.
     */
    private String smtpPort;

    /**
     * Boolean value if DataSource is to use the address for a forward reply.
     */
    private Boolean useAddressForForwardReply;

    /**
     * DataSource username.
     */
    private String username;

    /**
     * DataSource 'failing since' record.
     */
    private String failingSince;

    /**
     * DataSource value of the last error.
     */
    private List<String> lastError;

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The connection type
     */
    public String getConnectionType() {
        return connectionType;
    }

    /**
     * @param connectionType The connection type to set
     */
    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    /**
     * @return The default signature
     */
    public String getDefaultSignature() {
        return defaultSignature;
    }

    /**
     * @param defaultSignature The default signature to set
     */
    public void setDefaultSignature(String defaultSignature) {
        this.defaultSignature = defaultSignature;
    }

    /**
     * @return The email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress The email address to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return The l
     */
    public String getL() {
        return l;
    }

    /**
     * @param l The l to set
     */
    public void setL(String l) {
        this.l = l;
    }

    /**
     * @return The forward reply signature
     */
    public String getForwardReplySignature() {
        return forwardReplySignature;
    }

    /**
     * @param forwardReplySignature The forward reply signature to set
     */
    public void setForwardReplySignature(String forwardReplySignature) {
        this.forwardReplySignature = forwardReplySignature;
    }

    /**
     * @return The from display
     */
    public String getFromDisplay() {
        return fromDisplay;
    }

    /**
     * @param fromDisplay The 'from display' to set
     */
    public void setFromDisplay(String fromDisplay) {
        this.fromDisplay = fromDisplay;
    }

    /**
     * @return The host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host The host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return The boolean 'import only' value of this DataSource
     */
    public Boolean getImportOnly() {
        return importOnly;
    }

    /**
     * @param importOnly The boolean 'import only' value of this DataSource to set
     */
    public void setImportOnly(Boolean importOnly) {
        this.importOnly = importOnly;
    }

    /**
     * @return The boolean 'enabled' value of this DataSource
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     * @param isEnabled The boolean 'enabled' value of this DataSource to set
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * @return The DataSource name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The DataSource name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The polling interval
     */
    public Float getPollingInterval() {
        return pollingInterval;
    }

    /**
     * @param pollingInterval The polling interval to set
     */
    public void setPollingInterval(Float pollingInterval) {
        this.pollingInterval = pollingInterval;
    }

    /**
     * @return The port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port The port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @return The reply-to address
     */
    public String getReplyToAddress() {
        return replyToAddress;
    }

    /**
     * @param replyToAddress The reply-to address to set
     */
    public void setReplyToAddress(String replyToAddress) {
        this.replyToAddress = replyToAddress;
    }

    /**
     * @return The reply-to display
     */
    public String getReplyToDisplay() {
        return replyToDisplay;
    }

    /**
     * @param replyToDisplay The reply-to display to set
     */
    public void setReplyToDisplay(String replyToDisplay) {
        this.replyToDisplay = replyToDisplay;
    }

    /**
     * @return The smtp port
     */
    public String getSmtpPort() {
        return smtpPort;
    }

    /**
     * @param smtpPort The smtp port to set
     */
    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    /**
     * @return The boolean value of whether to use the address for a forward reply
     */
    public Boolean getUseAddressForForwardReply() {
        return useAddressForForwardReply;
    }

    /**
     * @param useAddressForForwardReply The boolean value of 'using the address for a forward reply' to set
     */
    public void setUseAddressForForwardReply(Boolean useAddressForForwardReply) {
        this.useAddressForForwardReply = useAddressForForwardReply;
    }

    /**
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return The 'failing since' value
     */
    public String getFailingSince() {
        return failingSince;
    }

    /**
     * @param failingSince The 'failing since' value to set
     */
    public void setFailingSince(String failingSince) {
        this.failingSince = failingSince;
    }

    /**
     * @return The last error
     */
    public List<String> getLastError() {
        return lastError;
    }

    /**
     * @param lastError The last error to set
     */
    public void setLastError(List<String> lastError) {
        this.lastError = lastError;
    }

}
