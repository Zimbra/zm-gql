package com.zimbra.graphql.models;

import java.util.List;

/**
 * Define the DataSources object
 * 
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class DataSources {

    /**
     *  The imap DataSource.
     */
    private List<DataSource> imap;

    /**
     *  The pop3 DataSource.
     */
    private List<DataSource> pop3;

    /**
     *  The cal DataSource.
     */
    private List<DataSource> cal;

    /**
     * Constructor for DataSources class.
     *
     * @param imap The datasource imap to set
     * @param pop3 The datasource pop3 to set
     * @param cal The datasource cal to set
     */
    public DataSources(List<DataSource> imap, List<DataSource> pop3, List<DataSource> cal) {
        this.imap = imap;
        this.pop3 = pop3;
        this.cal = cal;
    }

    /**
     * @return Returns the imap list
     */
    public List<DataSource> getImap() {
        return imap;
    }

    /**
     * @param Sets the imap list
     */
    public void setImap(List<DataSource> imap) {
        this.imap = imap;
    }

    /**
     * @return Returns the pop3 list
     */
    public List<DataSource> getPop3() {
        return pop3;
    }

    /**
     * @param Sets the pop3 list
     */
    public void setPop3(List<DataSource> pop3) {
        this.pop3 = pop3;
    }

    /**
     * @return Returns the cal list
     */
    public List<DataSource> getCal() {
        return cal;
    }

    /**
     * @param Sets the cal list
     */
    public void setCal(List<DataSource> cal) {
        this.cal = cal;
    }
}
