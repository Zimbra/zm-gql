package com.zimbra.graphql.models;

import java.util.List;

public class DataSources {

    private List<DataSource> imap;
    private List<DataSource> pop3;
    private List<DataSource> cal;

    public DataSources(List<DataSource> imap, List<DataSource> pop3, List<DataSource> cal) {
        this.imap = imap;
        this.pop3 = pop3;
        this.cal = cal;
    }

    public List<DataSource> getImap() {
        return imap;
    }

    public void setImap(List<DataSource> imap) {
        this.imap = imap;
    }

    public List<DataSource> getPop3() {
        return pop3;
    }

    public void setPop3(List<DataSource> pop3) {
        this.pop3 = pop3;
    }

    public List<DataSource> getCal() {
        return cal;
    }

    public void setCal(List<DataSource> cal) {
        this.cal = cal;
    }
}
