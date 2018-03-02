package com.zimbra.graphql.models;

/**
 * Define the ReadingPaneLocation ENUM object
 * 
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public enum ReadingPaneLocation {
    bottom("bottom"),
    off("off"),
    right("right");

    /**
     *  The location value.
     */
    private String locationValue;

    /**
     * Constructor for ReadingPaneLocation class.
     * This is a JAVA ENUM that gets and sets the values "bottom", "off", and "right".
     * Value options for defining where the reading panel is located.
     * This class can also return if any of the three values are enabled or not.
     *
     * @param locationValue The selected value for this ENUM class to set
     */
    private ReadingPaneLocation(String locationValue) {
        this.locationValue = locationValue;
    }

    /**
     * @return Returns the selected value for this ENUM class
     */
    public String getLocationValue() {
        return locationValue;
    }

    /**
     * @param Sets the selected value for this ENUM class
     */
    public void setLocationValue(String locationValue) {
        this.locationValue = locationValue;
    }

    /**
     * @return Returns the boolean value of bottom
     */
    public boolean isBottom() {
        return this == bottom;
    }

    /**
     * @return Returns the boolean value of off
     */
    public boolean isOff() {
        return this == off;
    }

    /**
     * @return Returns the boolean value of right
     */
    public boolean isRight() {
        return this == right;
    }
}
