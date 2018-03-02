package com.zimbra.graphql.models;

/**
 * Define the AccountInfoAttrs Object
 * 
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class AccountInfoAttrs {

    /**
     * The display name.
     */
    private String displayName;

    /**
     *  Boolean for whether the Calendar feature is enabled or not.
     */
    private Boolean zimbraFeatureCalendarEnabled;

    /**
     *  Boolean for whether the Related Contacts feature is enabled or not.
     */
    private Boolean zimbraFeatureRelatedContactsEnabled;

    /**
     * Constructor for AccountInfoAttrs class.
     * Includes display name and booleans to determine what features are enabled or not.
     * 
     * @param displayName The account displayName to set
     * @param zimbraFeatureCalendarEnabled The account zimbraFeatureCalendarEnabled to set
     * @param zimbraFeatureRelatedContactsEnabled The account zimbraFeatureRelatedContactsEnabled to set
     */
    public AccountInfoAttrs(String displayName, Boolean zimbraFeatureCalendarEnabled,
            Boolean zimbraFeatureRelatedContactsEnabled) {
        this.displayName = displayName;
        this.zimbraFeatureCalendarEnabled = zimbraFeatureCalendarEnabled;
        this.zimbraFeatureRelatedContactsEnabled = zimbraFeatureRelatedContactsEnabled;
    }

    /**
     * @return Returns the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param Sets the displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return Returns the zimbraFeatureCalendarEnabled
     */
    public Boolean getZimbraFeatureCalendarEnabled() {
        return zimbraFeatureCalendarEnabled;
    }

    /**
     * @param Sets the zimbraFeatureCalendarEnabled
     */
    public void setZimbraFeatureCalendarEnabled(Boolean zimbraFeatureCalendarEnabled) {
        this.zimbraFeatureCalendarEnabled = zimbraFeatureCalendarEnabled;
    }

    /**
     * @return Returns the zimbraFeatureRelatedContactsEnabled
     */
    public Boolean getZimbraFeatureRelatedContactsEnabled() {
        return zimbraFeatureRelatedContactsEnabled;
    }

    /**
     * @param Sets the zimbraFeatureRelatedContactsEnabled
     */
    public void setZimbraFeatureRelatedContactsEnabled(Boolean zimbraFeatureRelatedContactsEnabled) {
        this.zimbraFeatureRelatedContactsEnabled = zimbraFeatureRelatedContactsEnabled;
    }
}
