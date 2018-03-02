package com.zimbra.graphql.models;

/**
 * Define the PrefCalendarInitialView ENUM object
 * 
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public enum PrefCalendarInitialView {
    day("day"),
    list("list"),
    month("month"),
    week("week"),
    workWeek("workWeek"),
    year("year");

    /**
     *  The mailSelectValue.
     */
    private String mailSelectValue;

    /**
     * Constructor for PrefCalendarInitialView class.
     * This is a JAVA ENUM that gets and sets the values "day", "list", and "month", "week", "workWeek", "year".
     * Preference for what the initial Calendar view is.
     * This class can also return if any of the six values are enabled or not.
     * 
     * @param mailSelectValue The selected value for this ENUM class
     */
    private PrefCalendarInitialView(String mailSelectValue) {
        this.mailSelectValue = mailSelectValue;
    }

    /**
     * @return Returns the mailSelectValue
     */
    public String getMailSelectValue() {
        return mailSelectValue;
    }

    /**
     * @param Sets the mailSelectValue
     */
    public void setMailSelectValue(String mailSelectValue) {
        this.mailSelectValue = mailSelectValue;
    }

    /**
     * @return Returns the boolean value of day
     */
    public boolean isDay() {
        return this == day;
    }

    /**
     * @return Returns the boolean value of list
     */
    public boolean isList() {
        return this == list;
    }

    /**
     * @return Returns the boolean value of month
     */
    public boolean isMonth() {
        return this == month;
    }

    /**
     * @return Returns the boolean value of week
     */
    public boolean isWeek() {
        return this == week;
    }

    /**
     * @return Returns the boolean value of workweek
     */
    public boolean isWorkweek() {
        return this == workWeek;
    }

    /**
     * @return Returns the boolean value of year
     */
    public boolean isYear() {
        return this == year;
    }
}
