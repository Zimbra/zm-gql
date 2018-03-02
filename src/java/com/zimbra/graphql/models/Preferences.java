package com.zimbra.graphql.models;

/**
 * Define the Preferences object
 * 
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class Preferences {

    /**
     *  The zimbraPrefAutoAddAppointmentsToCalendar.
     */
    private Boolean zimbraPrefAutoAddAppointmentsToCalendar;

    /**
     *  The zimbraPrefCalendarFirstDayOfWeek.
     */
    private String zimbraPrefCalendarFirstDayOfWeek;

    /**
     *  The zimbraPrefCalendarInitialView.
     */
    private PrefCalendarInitialView zimbraPrefCalendarInitialView;

    /**
     *  The zimbraPrefCalendarReminderEmail.
     */
    private String zimbraPrefCalendarReminderEmail;

    /**
     *  The zimbraPrefCalendarWorkingHours.
     */
    private String zimbraPrefCalendarWorkingHours;

    /**
     *  The zimbraPrefGroupMailBy.
     */
    private String zimbraPrefGroupMailBy;

    /**
     *  The zimbraPrefMailSelectAfterDelete.
     */
    private PrefMailSelectAfterDelete zimbraPrefMailSelectAfterDelete;

    /**
     *  The zimbraPrefMarkMsgRead.
     */
    private Integer zimbraPrefMarkMsgRead;

    /**
     *  The zimbraPrefOutOfOfficeFromDate.
     */
    private String zimbraPrefOutOfOfficeFromDate;

    /**
     *  The zimbraPrefOutOfOfficeReply.
     */
    private String zimbraPrefOutOfOfficeReply;

    /**
     *  The zimbraPrefOutOfOfficeReplyEnabled.
     */
    private Boolean zimbraPrefOutOfOfficeReplyEnabled;

    /**
     *  The zimbraPrefOutOfOfficeStatusAlertOnLogin.
     */
    private Boolean zimbraPrefOutOfOfficeStatusAlertOnLogin;

    /**
     *  The zimbraPrefOutOfOfficeUntilDate.
     */
    private String zimbraPrefOutOfOfficeUntilDate;

    /**
     *  The zimbraPrefReadingPaneEnabled.
     */
    private Boolean zimbraPrefReadingPaneEnabled;

    /**
     *  The zimbraPrefReadingPaneLocation.
     */
    private ReadingPaneLocation zimbraPrefReadingPaneLocation;

    /**
     *  The zimbraPrefReadingPaneSashHorizontal.
     */
    private Integer zimbraPrefReadingPaneSashHorizontal;

    /**
     *  The zimbraPrefShowFragments.
     */
    private Boolean zimbraPrefShowFragments;

    /**
     * Constructor for Preferences class.
     *
     * @param zimbraPrefAutoAddAppointmentsToCalendar The preference's zimbraPrefAutoAddAppointmentsToCalendar to set
     * @param zimbraPrefCalendarFirstDayOfWeek The preference's zimbraPrefCalendarFirstDayOfWeek to set
     * @param zimbraPrefCalendarInitialView The preference's zimbraPrefCalendarInitialView to set
     * @param zimbraPrefCalendarReminderEmail The preference's zimbraPrefCalendarReminderEmail to set
     * @param zimbraPrefCalendarWorkingHours The preference's zimbraPrefCalendarWorkingHours to set
     * @param zimbraPrefGroupMailBy The preference's zimbraPrefGroupMailBy to set
     * @param zimbraPrefMailSelectAfterDelete The preference's zimbraPrefMailSelectAfterDelete to set
     * @param zimbraPrefMarkMsgRead The preference's zimbraPrefMarkMsgRead to set
     * @param zimbraPrefOutOfOfficeFromDate The preference's zimbraPrefOutOfOfficeFromDate to set
     * @param zimbraPrefOutOfOfficeReply The preference's zimbraPrefOutOfOfficeReply to set
     * @param zimbraPrefOutOfOfficeReplyEnabled The preference's zimbraPrefOutOfOfficeReplyEnabled to set
     * @param zimbraPrefOutOfOfficeStatusAlertOnLogin The preference's zimbraPrefOutOfOfficeStatusAlertOnLogin to set
     * @param zimbraPrefOutOfOfficeUntilDate The preference's zimbraPrefOutOfOfficeUntilDate to set
     * @param zimbraPrefReadingPaneEnabled The preference's zimbraPrefReadingPaneEnabled to set
     * @param zimbraPrefReadingPaneLocation The preference's zimbraPrefReadingPaneLocation to set
     * @param zimbraPrefReadingPaneSashHorizontal The preference's zimbraPrefReadingPaneSashHorizontal to set
     * @param zimbraPrefShowFragments The preference's zimbraPrefShowFragments to set
     */
    public Preferences(Boolean zimbraPrefAutoAddAppointmentsToCalendar, String zimbraPrefCalendarFirstDayOfWeek,
            PrefCalendarInitialView zimbraPrefCalendarInitialView, String zimbraPrefCalendarReminderEmail,
            String zimbraPrefCalendarWorkingHours, String zimbraPrefGroupMailBy,
            PrefMailSelectAfterDelete zimbraPrefMailSelectAfterDelete, Integer zimbraPrefMarkMsgRead,
            String zimbraPrefOutOfOfficeFromDate, String zimbraPrefOutOfOfficeReply,
            Boolean zimbraPrefOutOfOfficeReplyEnabled, Boolean zimbraPrefOutOfOfficeStatusAlertOnLogin,
            String zimbraPrefOutOfOfficeUntilDate, Boolean zimbraPrefReadingPaneEnabled,
            ReadingPaneLocation zimbraPrefReadingPaneLocation, Integer zimbraPrefReadingPaneSashHorizontal,
            Boolean zimbraPrefShowFragments) {
        this.zimbraPrefAutoAddAppointmentsToCalendar = zimbraPrefAutoAddAppointmentsToCalendar;
        this.zimbraPrefCalendarFirstDayOfWeek = zimbraPrefCalendarFirstDayOfWeek;
        this.zimbraPrefCalendarInitialView = zimbraPrefCalendarInitialView;
        this.zimbraPrefCalendarReminderEmail = zimbraPrefCalendarReminderEmail;
        this.zimbraPrefCalendarWorkingHours = zimbraPrefCalendarWorkingHours;
        this.zimbraPrefGroupMailBy = zimbraPrefGroupMailBy;
        this.zimbraPrefMailSelectAfterDelete = zimbraPrefMailSelectAfterDelete;
        this.zimbraPrefMarkMsgRead = zimbraPrefMarkMsgRead;
        this.zimbraPrefOutOfOfficeFromDate = zimbraPrefOutOfOfficeFromDate;
        this.zimbraPrefOutOfOfficeReply = zimbraPrefOutOfOfficeReply;
        this.zimbraPrefOutOfOfficeReplyEnabled = zimbraPrefOutOfOfficeReplyEnabled;
        this.zimbraPrefOutOfOfficeStatusAlertOnLogin = zimbraPrefOutOfOfficeStatusAlertOnLogin;
        this.zimbraPrefOutOfOfficeUntilDate = zimbraPrefOutOfOfficeUntilDate;
        this.zimbraPrefReadingPaneEnabled = zimbraPrefReadingPaneEnabled;
        this.zimbraPrefReadingPaneLocation = zimbraPrefReadingPaneLocation;
        this.zimbraPrefReadingPaneSashHorizontal = zimbraPrefReadingPaneSashHorizontal;
        this.zimbraPrefShowFragments = zimbraPrefShowFragments;
    }

    /**
     * @return Returns the zimbraPrefAutoAddAppointmentsToCalendar
     */
    public Boolean getZimbraPrefAutoAddAppointmentsToCalendar() {
        return zimbraPrefAutoAddAppointmentsToCalendar;
    }

    /**
     * @param Sets the zimbraPrefAutoAddAppointmentsToCalendar
     */
    public void setZimbraPrefAutoAddAppointmentsToCalendar(Boolean zimbraPrefAutoAddAppointmentsToCalendar) {
        this.zimbraPrefAutoAddAppointmentsToCalendar = zimbraPrefAutoAddAppointmentsToCalendar;
    }

    /**
     * @return Returns the zimbraPrefCalendarFirstDayOfWeek
     */
    public String getZimbraPrefCalendarFirstDayOfWeek() {
        return zimbraPrefCalendarFirstDayOfWeek;
    }

    /**
     * @param Sets the zimbraPrefCalendarFirstDayOfWeek
     */
    public void setZimbraPrefCalendarFirstDayOfWeek(String zimbraPrefCalendarFirstDayOfWeek) {
        this.zimbraPrefCalendarFirstDayOfWeek = zimbraPrefCalendarFirstDayOfWeek;
    }

    /**
     * @return Returns the zimbraPrefCalendarInitialView
     */
    public PrefCalendarInitialView getZimbraPrefCalendarInitialView() {
        return zimbraPrefCalendarInitialView;
    }

    /**
     * @param Sets the zimbraPrefCalendarInitialView
     */
    public void setZimbraPrefCalendarInitialView(PrefCalendarInitialView zimbraPrefCalendarInitialView) {
        this.zimbraPrefCalendarInitialView = zimbraPrefCalendarInitialView;
    }

    /**
     * @return Returns the zimbraPrefCalendarReminderEmail
     */
    public String getZimbraPrefCalendarReminderEmail() {
        return zimbraPrefCalendarReminderEmail;
    }

    /**
     * @param Sets the zimbraPrefCalendarReminderEmail
     */
    public void setZimbraPrefCalendarReminderEmail(String zimbraPrefCalendarReminderEmail) {
        this.zimbraPrefCalendarReminderEmail = zimbraPrefCalendarReminderEmail;
    }

    /**
     * @return Returns the zimbraPrefCalendarWorkingHours
     */
    public String getZimbraPrefCalendarWorkingHours() {
        return zimbraPrefCalendarWorkingHours;
    }

    /**
     * @param Sets the zimbraPrefCalendarWorkingHours
     */
    public void setZimbraPrefCalendarWorkingHours(String zimbraPrefCalendarWorkingHours) {
        this.zimbraPrefCalendarWorkingHours = zimbraPrefCalendarWorkingHours;
    }

    /**
     * @return Returns the zimbraPrefGroupMailBy
     */
    public String getZimbraPrefGroupMailBy() {
        return zimbraPrefGroupMailBy;
    }

    /**
     * @param Sets the zimbraPrefGroupMailBy
     */
    public void setZimbraPrefGroupMailBy(String zimbraPrefGroupMailBy) {
        this.zimbraPrefGroupMailBy = zimbraPrefGroupMailBy;
    }

    /**
     * @return Returns the zimbraPrefMailSelectAfterDelete
     */
    public PrefMailSelectAfterDelete getZimbraPrefMailSelectAfterDelete() {
        return zimbraPrefMailSelectAfterDelete;
    }

    /**
     * @param Sets the zimbraPrefMailSelectAfterDelete
     */
    public void setZimbraPrefMailSelectAfterDelete(PrefMailSelectAfterDelete zimbraPrefMailSelectAfterDelete) {
        this.zimbraPrefMailSelectAfterDelete = zimbraPrefMailSelectAfterDelete;
    }

    /**
     * @return Returns the zimbraPrefMarkMsgRead
     */
    public Integer getZimbraPrefMarkMsgRead() {
        return zimbraPrefMarkMsgRead;
    }

    /**
     * @param Sets the zimbraPrefMarkMsgRead
     */
    public void setZimbraPrefMarkMsgRead(Integer zimbraPrefMarkMsgRead) {
        this.zimbraPrefMarkMsgRead = zimbraPrefMarkMsgRead;
    }

    /**
     * @return Returns the zimbraPrefOutOfOfficeFromDate
     */
    public String getZimbraPrefOutOfOfficeFromDate() {
        return zimbraPrefOutOfOfficeFromDate;
    }

    /**
     * @param Sets the zimbraPrefOutOfOfficeFromDate
     */
    public void setZimbraPrefOutOfOfficeFromDate(String zimbraPrefOutOfOfficeFromDate) {
        this.zimbraPrefOutOfOfficeFromDate = zimbraPrefOutOfOfficeFromDate;
    }

    /**
     * @return Returns the zimbraPrefOutOfOfficeReply
     */
    public String getZimbraPrefOutOfOfficeReply() {
        return zimbraPrefOutOfOfficeReply;
    }

    /**
     * @param Sets the zimbraPrefOutOfOfficeReply
     */
    public void setZimbraPrefOutOfOfficeReply(String zimbraPrefOutOfOfficeReply) {
        this.zimbraPrefOutOfOfficeReply = zimbraPrefOutOfOfficeReply;
    }

    /**
     * @return Returns the zimbraPrefOutOfOfficeReplyEnabled
     */
    public Boolean getZimbraPrefOutOfOfficeReplyEnabled() {
        return zimbraPrefOutOfOfficeReplyEnabled;
    }

    /**
     * @param Sets the zimbraPrefOutOfOfficeReplyEnabled
     */
    public void setZimbraPrefOutOfOfficeReplyEnabled(Boolean zimbraPrefOutOfOfficeReplyEnabled) {
        this.zimbraPrefOutOfOfficeReplyEnabled = zimbraPrefOutOfOfficeReplyEnabled;
    }

    /**
     * @return Returns the zimbraPrefOutOfOfficeStatusAlertOnLogin
     */
    public Boolean getZimbraPrefOutOfOfficeStatusAlertOnLogin() {
        return zimbraPrefOutOfOfficeStatusAlertOnLogin;
    }

    /**
     * @param Sets the zimbraPrefOutOfOfficeStatusAlertOnLogin
     */
    public void setZimbraPrefOutOfOfficeStatusAlertOnLogin(Boolean zimbraPrefOutOfOfficeStatusAlertOnLogin) {
        this.zimbraPrefOutOfOfficeStatusAlertOnLogin = zimbraPrefOutOfOfficeStatusAlertOnLogin;
    }

    /**
     * @return Returns the zimbraPrefOutOfOfficeUntilDate
     */
    public String getZimbraPrefOutOfOfficeUntilDate() {
        return zimbraPrefOutOfOfficeUntilDate;
    }

    /**
     * @param Sets the zimbraPrefOutOfOfficeUntilDate
     */
    public void setZimbraPrefOutOfOfficeUntilDate(String zimbraPrefOutOfOfficeUntilDate) {
        this.zimbraPrefOutOfOfficeUntilDate = zimbraPrefOutOfOfficeUntilDate;
    }

    /**
     * @return Returns the zimbraPrefReadingPaneEnabled
     */
    public Boolean getZimbraPrefReadingPaneEnabled() {
        return zimbraPrefReadingPaneEnabled;
    }

    /**
     * @param Sets the zimbraPrefReadingPaneEnabled
     */
    public void setZimbraPrefReadingPaneEnabled(Boolean zimbraPrefReadingPaneEnabled) {
        this.zimbraPrefReadingPaneEnabled = zimbraPrefReadingPaneEnabled;
    }

    /**
     * @return Returns the zimbraPrefReadingPaneLocation
     */
    public ReadingPaneLocation getZimbraPrefReadingPaneLocation() {
        return zimbraPrefReadingPaneLocation;
    }

    /**
     * @param Sets the zimbraPrefReadingPaneLocation
     */
    public void setZimbraPrefReadingPaneLocation(ReadingPaneLocation zimbraPrefReadingPaneLocation) {
        this.zimbraPrefReadingPaneLocation = zimbraPrefReadingPaneLocation;
    }

    /**
     * @return Returns the zimbraPrefReadingPaneSashHorizontal
     */
    public Integer getZimbraPrefReadingPaneSashHorizontal() {
        return zimbraPrefReadingPaneSashHorizontal;
    }

    /**
     * @param Sets the zimbraPrefReadingPaneSashHorizontal
     */
    public void setZimbraPrefReadingPaneSashHorizontal(Integer zimbraPrefReadingPaneSashHorizontal) {
        this.zimbraPrefReadingPaneSashHorizontal = zimbraPrefReadingPaneSashHorizontal;
    }

    /**
     * @return Returns the zimbraPrefShowFragments
     */
    public Boolean getZimbraPrefShowFragments() {
        return zimbraPrefShowFragments;
    }

    /**
     * @param Sets the zimbraPrefShowFragments
     */
    public void setZimbraPrefShowFragments(Boolean zimbraPrefShowFragments) {
        this.zimbraPrefShowFragments = zimbraPrefShowFragments;
    }
}
