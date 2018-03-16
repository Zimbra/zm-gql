package com.zimbra.graphql.models;

/**
 * The Preferences class.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class Preferences {

    /**
     * The Zimbra preference for the account's enabled value for 'auto add appointments to calendar'.
     */
    private Boolean zimbraPrefAutoAddAppointmentsToCalendar;

    /**
     * The Zimbra preference for the account's 'calendar's first day of the week'.
     */
    private String zimbraPrefCalendarFirstDayOfWeek;

    /**
     * The Zimbra preference for the account's initial calendar view.
     */
    private PrefCalendarInitialView zimbraPrefCalendarInitialView;

    /**
     * The Zimbra preference for the account's 'calendar reminder email'.
     */
    private String zimbraPrefCalendarReminderEmail;

    /**
     * The Zimbra preference for the account's 'calendar working hours'.
     */
    private String zimbraPrefCalendarWorkingHours;

    /**
     * The Zimbra preference for the account's 'group mail by'.
     */
    private String zimbraPrefGroupMailBy;

    /**
     * The Zimbra preference for the account's value of which mail to select after deleting the current the mail.
     */
    private PrefMailSelectAfterDelete zimbraPrefMailSelectAfterDelete;

    /**
     * The Zimbra preferences for the account's for marking messages as read.
     */
    private Integer zimbraPrefMarkMsgRead;

    /**
     * The Zimbra preference for the account's 'out off office from date'.
     */
    private String zimbraPrefOutOfOfficeFromDate;

    /**
     * The Zimbra preference for the account's 'out of office reply'.
     */
    private String zimbraPrefOutOfOfficeReply;

    /**
     * The Zimbra preference for the account's enabled value for 'out of office reply'.
     */
    private Boolean zimbraPrefOutOfOfficeReplyEnabled;

    /**
     * The Zimbra preference for the account's enabled value for 'out of office status alert on login'.
     */
    private Boolean zimbraPrefOutOfOfficeStatusAlertOnLogin;

    /**
     * The Zimbra preference for the account's 'out of office until date'.
     */
    private String zimbraPrefOutOfOfficeUntilDate;

    /**
     * The Zimbra preference for the account's enabled value for 'reading pane'.
     */
    private Boolean zimbraPrefReadingPaneEnabled;

    /**
     * The Zimbra preference for the account's 'reading pane location'.
     */
    private ReadingPaneLocation zimbraPrefReadingPaneLocation;

    /**
     * The Zimbra preference for the account's 'reading pane sash horizontal.
     */
    private Integer zimbraPrefReadingPaneSashHorizontal;

    /**
     * The Zimbra preference for the account's enabled value for 'show fragments'.
     */
    private Boolean zimbraPrefShowFragments;

    /**
     * @return The account's Zimbra preference for the enabled value of 'auto add appointment to calendar'
     */
    public Boolean getZimbraPrefAutoAddAppointmentsToCalendar() {
        return zimbraPrefAutoAddAppointmentsToCalendar;
    }

    /**
     * @param zimbraPrefAutoAddAppointmentsToCalendar The Zimbra preference's enabled value of 'auto add appointment to calendar' to set
     */
    public void setZimbraPrefAutoAddAppointmentsToCalendar(Boolean zimbraPrefAutoAddAppointmentsToCalendar) {
        this.zimbraPrefAutoAddAppointmentsToCalendar = zimbraPrefAutoAddAppointmentsToCalendar;
    }

    /**
     * @return The account's Zimbra preference for the calendar's 'first day of the week'
     */
    public String getZimbraPrefCalendarFirstDayOfWeek() {
        return zimbraPrefCalendarFirstDayOfWeek;
    }

    /**
     * @param zimbraPrefCalendarFirstDayOfWeek The Zimbra preference of the calendar's 'first day of the week' to set
     */
    public void setZimbraPrefCalendarFirstDayOfWeek(String zimbraPrefCalendarFirstDayOfWeek) {
        this.zimbraPrefCalendarFirstDayOfWeek = zimbraPrefCalendarFirstDayOfWeek;
    }

    /**
     * @return The account's Zimbra preference for the calendar's 'initial view'
     */
    public PrefCalendarInitialView getZimbraPrefCalendarInitialView() {
        return zimbraPrefCalendarInitialView;
    }

    /**
     * @param zimbraPrefCalendarInitialView The Zimbra preference of the calendar's 'initial view' to set
     */
    public void setZimbraPrefCalendarInitialView(PrefCalendarInitialView zimbraPrefCalendarInitialView) {
        this.zimbraPrefCalendarInitialView = zimbraPrefCalendarInitialView;
    }

    /**
     * @return The account's Zimbra preference for the calendar's 'reminder email'
     */
    public String getZimbraPrefCalendarReminderEmail() {
        return zimbraPrefCalendarReminderEmail;
    }

    /**
     * @param zimbraPrefCalendarReminderEmail The Zimbra preference of the calendar's 'reminder email' to set
     */
    public void setZimbraPrefCalendarReminderEmail(String zimbraPrefCalendarReminderEmail) {
        this.zimbraPrefCalendarReminderEmail = zimbraPrefCalendarReminderEmail;
    }

    /**
     * @return The account's Zimbra preference for the calendar's 'working hours'
     */
    public String getZimbraPrefCalendarWorkingHours() {
        return zimbraPrefCalendarWorkingHours;
    }

    /**
     * @param zimbraPrefCalendarWorkingHours The Zimbra preference of the calendar's 'working hours' to set
     */
    public void setZimbraPrefCalendarWorkingHours(String zimbraPrefCalendarWorkingHours) {
        this.zimbraPrefCalendarWorkingHours = zimbraPrefCalendarWorkingHours;
    }

    /**
     * @return The account's Zimbra preference for 'group mail by'
     */
    public String getZimbraPrefGroupMailBy() {
        return zimbraPrefGroupMailBy;
    }

    /**
     * @param zimbraPrefGroupMailBy The Zimbra preference's 'group mail by' value to set
     */
    public void setZimbraPrefGroupMailBy(String zimbraPrefGroupMailBy) {
        this.zimbraPrefGroupMailBy = zimbraPrefGroupMailBy;
    }

    /**
     * @return The account's Zimbra preference for what mail to select after deleting
     */
    public PrefMailSelectAfterDelete getZimbraPrefMailSelectAfterDelete() {
        return zimbraPrefMailSelectAfterDelete;
    }

    /**
     * @param zimbraPrefMailSelectAfterDelete The mail to select after deleting Zimbra preference to set
     */
    public void setZimbraPrefMailSelectAfterDelete(PrefMailSelectAfterDelete zimbraPrefMailSelectAfterDelete) {
        this.zimbraPrefMailSelectAfterDelete = zimbraPrefMailSelectAfterDelete;
    }

    /**
     * @return The account's Zimbra preference for the 'marked messages read' value
     */
    public Integer getZimbraPrefMarkMsgRead() {
        return zimbraPrefMarkMsgRead;
    }

    /**
     * @param zimbraPrefMarkMsgRead The Zimbra preference's 'marked messages read' value to set
     */
    public void setZimbraPrefMarkMsgRead(Integer zimbraPrefMarkMsgRead) {
        this.zimbraPrefMarkMsgRead = zimbraPrefMarkMsgRead;
    }

    /**
     * @return The account's Zimbra preference for the 'out of office from date' value
     */
    public String getZimbraPrefOutOfOfficeFromDate() {
        return zimbraPrefOutOfOfficeFromDate;
    }

    /**
     * @param zimbraPrefOutOfOfficeFromDate The Zimbra preference's 'out of office from date' value to set
     */
    public void setZimbraPrefOutOfOfficeFromDate(String zimbraPrefOutOfOfficeFromDate) {
        this.zimbraPrefOutOfOfficeFromDate = zimbraPrefOutOfOfficeFromDate;
    }

    /**
     * @return The account's Zimbra preference for the 'out of office reply'
     */
    public String getZimbraPrefOutOfOfficeReply() {
        return zimbraPrefOutOfOfficeReply;
    }

    /**
     * @param zimbraPrefOutOfOfficeReply The Zimbra preference's 'out of office reply' value to set
     */
    public void setZimbraPrefOutOfOfficeReply(String zimbraPrefOutOfOfficeReply) {
        this.zimbraPrefOutOfOfficeReply = zimbraPrefOutOfOfficeReply;
    }

    /**
     * @return The account's Zimbra preference for the enabled value of 'out of office reply'
     */
    public Boolean getZimbraPrefOutOfOfficeReplyEnabled() {
        return zimbraPrefOutOfOfficeReplyEnabled;
    }

    /**
     * @param zimbraPrefOutOfOfficeReplyEnabled The Zimbra preference's enabled value of 'out of office reply'  to set
     */
    public void setZimbraPrefOutOfOfficeReplyEnabled(Boolean zimbraPrefOutOfOfficeReplyEnabled) {
        this.zimbraPrefOutOfOfficeReplyEnabled = zimbraPrefOutOfOfficeReplyEnabled;
    }

    /**
     * @return The account's Zimbra preference for the enabled value of 'out of office status alert on login'
     */
    public Boolean getZimbraPrefOutOfOfficeStatusAlertOnLogin() {
        return zimbraPrefOutOfOfficeStatusAlertOnLogin;
    }

    /**
     * @param zimbraPrefOutOfOfficeStatusAlertOnLogin The Zimbra preference's enabled value of 'out of office status alert on login' to set
     */
    public void setZimbraPrefOutOfOfficeStatusAlertOnLogin(Boolean zimbraPrefOutOfOfficeStatusAlertOnLogin) {
        this.zimbraPrefOutOfOfficeStatusAlertOnLogin = zimbraPrefOutOfOfficeStatusAlertOnLogin;
    }

    /**
     * @return The account's Zimbra preference for the 'out of office until date' value
     */
    public String getZimbraPrefOutOfOfficeUntilDate() {
        return zimbraPrefOutOfOfficeUntilDate;
    }

    /**
     * @param zimbraPrefOutOfOfficeUntilDate The Zimbra preference's 'out of office until date' value to set
     */
    public void setZimbraPrefOutOfOfficeUntilDate(String zimbraPrefOutOfOfficeUntilDate) {
        this.zimbraPrefOutOfOfficeUntilDate = zimbraPrefOutOfOfficeUntilDate;
    }

    /**
     * @return The account's Zimbra preference for the enabled value of 'reading pane enabled'
     */
    public Boolean getZimbraPrefReadingPaneEnabled() {
        return zimbraPrefReadingPaneEnabled;
    }

    /**
     * @param zimbraPrefReadingPaneEnabled The Zimbra preference's enabled value of 'reading pane' to set
     */
    public void setZimbraPrefReadingPaneEnabled(Boolean zimbraPrefReadingPaneEnabled) {
        this.zimbraPrefReadingPaneEnabled = zimbraPrefReadingPaneEnabled;
    }

    /**
     * @return The account's Zimbra preference for the 'reading pane location' value
     */
    public ReadingPaneLocation getZimbraPrefReadingPaneLocation() {
        return zimbraPrefReadingPaneLocation;
    }

    /**
     * @param zimbraPrefReadingPaneLocation The Zimbra preference's 'reading pane location' value to set
     */
    public void setZimbraPrefReadingPaneLocation(ReadingPaneLocation zimbraPrefReadingPaneLocation) {
        this.zimbraPrefReadingPaneLocation = zimbraPrefReadingPaneLocation;
    }

    /**
     * @return The account's Zimbra preference for the 'reading pane sash horizontal' value
     */
    public Integer getZimbraPrefReadingPaneSashHorizontal() {
        return zimbraPrefReadingPaneSashHorizontal;
    }

    /**
     * @param zimbraPrefReadingPaneSashHorizontal The Zimbra preference's 'reading pane saash horizontal' value to set
     */
    public void setZimbraPrefReadingPaneSashHorizontal(Integer zimbraPrefReadingPaneSashHorizontal) {
        this.zimbraPrefReadingPaneSashHorizontal = zimbraPrefReadingPaneSashHorizontal;
    }

    /**
     * @return The account's Zimbra preference for the enabled value of 'show fragments'
     */
    public Boolean getZimbraPrefShowFragments() {
        return zimbraPrefShowFragments;
    }

    /**
     * @param zimbraPrefShowFragments The Zimbra preference's enabled value of 'show fragments' to set
     */
    public void setZimbraPrefShowFragments(Boolean zimbraPrefShowFragments) {
        this.zimbraPrefShowFragments = zimbraPrefShowFragments;
    }

}
