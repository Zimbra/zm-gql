package com.zimbra.graphql.models;

/**
 * Define the PrefMailSelectAfterDelete ENUM object
 * 
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public enum PrefMailSelectAfterDelete {
    next("next"),
    previous("previous"),
    adaptive("adaptive");

    /**
     *  The mailSelectValue.
     */
    private String mailSelectValue;

    /**
     * Constructor for PrefMailSelectAfterDelete class.
     * This is a JAVA ENUM that gets and sets the values "next", "previous", and "adaptive".
     * Value options for determining which email message to display after the current email message is deleted.
     * This class can also return if any of the three values are enabled or not.
     *
     * @param mailSelectValue The selected value for this ENUM class to set
     */
    private PrefMailSelectAfterDelete(String mailSelectValue) {
        this.mailSelectValue = mailSelectValue;
    }

    /**
     * @return Returns the selected value for this ENUM class
     */
    public String getMailSelectValue() {
        return mailSelectValue;
    }

    /**
     * @param Sets the selected value for this ENUM class
     */
    public void setMailSelectValue(String mailSelectValue) {
        this.mailSelectValue = mailSelectValue;
    }

    /**
     * @return Returns the boolean value of next
     */
    public boolean isNextm() {
        return this == next;
    }

    /**
     * @return Returns the boolean value of previous
     */
    public boolean isPrevious() {
        return this == previous;
    }

    /**
     * @return Returns the boolean value of adaptive
     */
    public boolean isAdaptive() {
        return this == adaptive;
    }
}
