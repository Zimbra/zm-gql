package com.zimbra.graphql.models;

import java.util.List;

/**
 * The DataSources class.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class DataSources {

    /**
     * The imap DataSource.
     */
    private List<DataSource> imap;

    /**
     * The pop3 DataSource.
     */
    private List<DataSource> pop3;

    /**
     * The cal DataSource.
     */
    private List<DataSource> cal;

    /**
     * @return The imap list
     */
    public List<DataSource> getImap() {
        return imap;
    }

    /**
     * @param imap The imap list to set
     */
    public void setImap(List<DataSource> imap) {
        this.imap = imap;
    }

    /**
     * @return The pop3 list
     */
    public List<DataSource> getPop3() {
        return pop3;
    }

    /**
     * @param pop3 The pop3 list to set
     */
    public void setPop3(List<DataSource> pop3) {
        this.pop3 = pop3;
    }

    /**
     * @return The cal list
     */
    public List<DataSource> getCal() {
        return cal;
    }

    /**
     * @param cal The cal list to set
     */
    public void setCal(List<DataSource> cal) {
        this.cal = cal;
    }

}
