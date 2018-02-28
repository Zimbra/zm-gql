package com.zimbra.graphql.models;

public class AccountInfoAttrs {
    private String displayName;
    private Boolean zimbraFeatureCalendarEnabled;
    private Boolean zimbraFeatureRelatedContactsEnabled;

    public AccountInfoAttrs(String displayName, Boolean zimbraFeatureCalendarEnabled,
            Boolean zimbraFeatureRelatedContactsEnabled) {
        this.displayName = displayName;
        this.zimbraFeatureCalendarEnabled = zimbraFeatureCalendarEnabled;
        this.zimbraFeatureRelatedContactsEnabled = zimbraFeatureRelatedContactsEnabled;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean getZimbraFeatureCalendarEnabled() {
        return zimbraFeatureCalendarEnabled;
    }

    public void setZimbraFeatureCalendarEnabled(Boolean zimbraFeatureCalendarEnabled) {
        this.zimbraFeatureCalendarEnabled = zimbraFeatureCalendarEnabled;
    }

    public Boolean getZimbraFeatureRelatedContactsEnabled() {
        return zimbraFeatureRelatedContactsEnabled;
    }

    public void setZimbraFeatureRelatedContactsEnabled(Boolean zimbraFeatureRelatedContactsEnabled) {
        this.zimbraFeatureRelatedContactsEnabled = zimbraFeatureRelatedContactsEnabled;
    }
}
