package com.zimbra.graphql.models;

public enum PrefMailSelectAfterDelete {
    next("next"),
    previous("previous"),
    adaptive("adaptive");
    
    private String mailSelectValue;

    private PrefMailSelectAfterDelete(String mailSelectValue) {
        this.mailSelectValue = mailSelectValue;
    }

    public String getMailSelectValue() {
        return mailSelectValue;
    }

    public void setMailSelectValue(String mailSelectValue) {
        this.mailSelectValue = mailSelectValue;
    }
    
    public boolean isNextm() {
        return this == next;
    }

    public boolean isPrevious() {
        return this == previous;
    }

    public boolean isAdaptive() {
        return this == adaptive;
    }
}
