package com.zimbra.graphql.models;

public enum ReadingPaneLocation {
    bottom("bottom"),
    off("off"),
    right("right");

    private String locationValue;

    private ReadingPaneLocation(String locationValue) {
        this.locationValue = locationValue;
    }

    public String getLocationValue() {
        return locationValue;
    }

    public void setLocationValue(String locationValue) {
        this.locationValue = locationValue;
    }

    public boolean isBottom() {
        return this == bottom;
    }

    public boolean isOff() {
        return this == off;
    }

    public boolean isRight() {
        return this == right;
    }
}
