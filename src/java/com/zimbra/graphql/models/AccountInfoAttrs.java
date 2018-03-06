package com.zimbra.graphql.models;

/**
 * The AccountInfoAttrs class.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class AccountInfoAttrs {

    /**
     * Display name attribute for the account.
     */
    private String displayName;

    /**
     * Boolean 'enabled' value for Zimbra's calendar feature.
     */
    private Boolean zimbraFeatureCalendarEnabled;

    /**
     * Boolean 'enabled' value for Zimbra's related contacts feature.
     */
    private Boolean zimbraFeatureRelatedContactsEnabled;

    /**
     * @return The account's display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName The account's display name to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return The boolean enabled value of Zimbra's calendar feature
     */
    public Boolean getZimbraFeatureCalendarEnabled() {
        return zimbraFeatureCalendarEnabled;
    }

    /**
     * @param zimbraFeatureCalendarEnabled The boolean enabled value of Zimbra's calendar feature to set
     */
    public void setZimbraFeatureCalendarEnabled(Boolean zimbraFeatureCalendarEnabled) {
        this.zimbraFeatureCalendarEnabled = zimbraFeatureCalendarEnabled;
    }

    /**
     * @return The boolean enabled value of Zimbra's related contacts feature
     */
    public Boolean getZimbraFeatureRelatedContactsEnabled() {
        return zimbraFeatureRelatedContactsEnabled;
    }

    /**
     * @param zimbraFeatureRelatedContactsEnabled The boolean enabled value of related contacts feature to set
     */
    public void setZimbraFeatureRelatedContactsEnabled(Boolean zimbraFeatureRelatedContactsEnabled) {
        this.zimbraFeatureRelatedContactsEnabled = zimbraFeatureRelatedContactsEnabled;
    }

}
