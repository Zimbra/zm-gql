package com.zimbra.graphql.models;

public enum PrefCalendarInitialView {
    day("day"),
    list("list"),
    month("month"),
    week("week"),
    workweek("workweek"),
    year("year");
    
    private String mailSelectValue;

    private PrefCalendarInitialView(String mailSelectValue) {
        this.mailSelectValue = mailSelectValue;
    }

    public String getMailSelectValue() {
        return mailSelectValue;
    }

    public void setMailSelectValue(String mailSelectValue) {
        this.mailSelectValue = mailSelectValue;
    }

    public boolean isDay() {
        return this == day;
    }

    public boolean isList() {
        return this == list;
    }

    public boolean isMonth() {
        return this == month;
    }

    public boolean isWeek() {
        return this == week;
    }

    public boolean isWorkweek() {
        return this == workweek;
    }

    public boolean isYear() {
        return this == year;
    }
}
