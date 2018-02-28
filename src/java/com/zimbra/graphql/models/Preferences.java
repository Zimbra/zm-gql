package com.zimbra.graphql.models;

public class Preferences {

    private Boolean zimbraPrefAutoAddAppointmentsToCalendar;
    private String zimbraPrefCalendarFirstDayOfWeek;
    private PrefCalendarInitialView zimbraPrefCalendarInitialView;
    private String zimbraPrefCalendarReminderEmail;
    private String zimbraPrefCalendarWorkingHours;
    private String zimbraPrefGroupMailBy;
    private PrefMailSelectAfterDelete zimbraPrefMailSelectAfterDelete;
    private Integer zimbraPrefMarkMsgRead;
    private String zimbraPrefOutOfOfficeFromDate;
    private String zimbraPrefOutOfOfficeReply;
    private Boolean zimbraPrefOutOfOfficeReplyEnabled;
    private Boolean zimbraPrefOutOfOfficeStatusAlertOnLogin;
    private String zimbraPrefOutOfOfficeUntilDate;
    private Boolean zimbraPrefReadingPaneEnabled;
    private ReadingPaneLocation zimbraPrefReadingPaneLocation;
    private Integer zimbraPrefReadingPaneSashHorizontal;
    private Boolean zimbraPrefShowFragments;

    public Preferences(ReadingPaneLocation zimbraPrefReadingPaneLocation) {
        this.zimbraPrefReadingPaneLocation = zimbraPrefReadingPaneLocation;
    }

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

    public Boolean getZimbraPrefAutoAddAppointmentsToCalendar() {
        return zimbraPrefAutoAddAppointmentsToCalendar;
    }

    public void setZimbraPrefAutoAddAppointmentsToCalendar(Boolean zimbraPrefAutoAddAppointmentsToCalendar) {
        this.zimbraPrefAutoAddAppointmentsToCalendar = zimbraPrefAutoAddAppointmentsToCalendar;
    }

    public String getZimbraPrefCalendarFirstDayOfWeek() {
        return zimbraPrefCalendarFirstDayOfWeek;
    }

    public void setZimbraPrefCalendarFirstDayOfWeek(String zimbraPrefCalendarFirstDayOfWeek) {
        this.zimbraPrefCalendarFirstDayOfWeek = zimbraPrefCalendarFirstDayOfWeek;
    }

    public PrefCalendarInitialView getZimbraPrefCalendarInitialView() {
        return zimbraPrefCalendarInitialView;
    }

    public void setZimbraPrefCalendarInitialView(PrefCalendarInitialView zimbraPrefCalendarInitialView) {
        this.zimbraPrefCalendarInitialView = zimbraPrefCalendarInitialView;
    }

    public String getZimbraPrefCalendarReminderEmail() {
        return zimbraPrefCalendarReminderEmail;
    }

    public void setZimbraPrefCalendarReminderEmail(String zimbraPrefCalendarReminderEmail) {
        this.zimbraPrefCalendarReminderEmail = zimbraPrefCalendarReminderEmail;
    }

    public String getZimbraPrefCalendarWorkingHours() {
        return zimbraPrefCalendarWorkingHours;
    }

    public void setZimbraPrefCalendarWorkingHours(String zimbraPrefCalendarWorkingHours) {
        this.zimbraPrefCalendarWorkingHours = zimbraPrefCalendarWorkingHours;
    }

    public String getZimbraPrefGroupMailBy() {
        return zimbraPrefGroupMailBy;
    }

    public void setZimbraPrefGroupMailBy(String zimbraPrefGroupMailBy) {
        this.zimbraPrefGroupMailBy = zimbraPrefGroupMailBy;
    }

    public PrefMailSelectAfterDelete getZimbraPrefMailSelectAfterDelete() {
        return zimbraPrefMailSelectAfterDelete;
    }

    public void setZimbraPrefMailSelectAfterDelete(PrefMailSelectAfterDelete zimbraPrefMailSelectAfterDelete) {
        this.zimbraPrefMailSelectAfterDelete = zimbraPrefMailSelectAfterDelete;
    }

    public Integer getZimbraPrefMarkMsgRead() {
        return zimbraPrefMarkMsgRead;
    }

    public void setZimbraPrefMarkMsgRead(Integer zimbraPrefMarkMsgRead) {
        this.zimbraPrefMarkMsgRead = zimbraPrefMarkMsgRead;
    }

    public String getZimbraPrefOutOfOfficeFromDate() {
        return zimbraPrefOutOfOfficeFromDate;
    }

    public void setZimbraPrefOutOfOfficeFromDate(String zimbraPrefOutOfOfficeFromDate) {
        this.zimbraPrefOutOfOfficeFromDate = zimbraPrefOutOfOfficeFromDate;
    }

    public String getZimbraPrefOutOfOfficeReply() {
        return zimbraPrefOutOfOfficeReply;
    }

    public void setZimbraPrefOutOfOfficeReply(String zimbraPrefOutOfOfficeReply) {
        this.zimbraPrefOutOfOfficeReply = zimbraPrefOutOfOfficeReply;
    }

    public Boolean getZimbraPrefOutOfOfficeReplyEnabled() {
        return zimbraPrefOutOfOfficeReplyEnabled;
    }

    public void setZimbraPrefOutOfOfficeReplyEnabled(Boolean zimbraPrefOutOfOfficeReplyEnabled) {
        this.zimbraPrefOutOfOfficeReplyEnabled = zimbraPrefOutOfOfficeReplyEnabled;
    }

    public Boolean getZimbraPrefOutOfOfficeStatusAlertOnLogin() {
        return zimbraPrefOutOfOfficeStatusAlertOnLogin;
    }

    public void setZimbraPrefOutOfOfficeStatusAlertOnLogin(Boolean zimbraPrefOutOfOfficeStatusAlertOnLogin) {
        this.zimbraPrefOutOfOfficeStatusAlertOnLogin = zimbraPrefOutOfOfficeStatusAlertOnLogin;
    }

    public String getZimbraPrefOutOfOfficeUntilDate() {
        return zimbraPrefOutOfOfficeUntilDate;
    }

    public void setZimbraPrefOutOfOfficeUntilDate(String zimbraPrefOutOfOfficeUntilDate) {
        this.zimbraPrefOutOfOfficeUntilDate = zimbraPrefOutOfOfficeUntilDate;
    }

    public Boolean getZimbraPrefReadingPaneEnabled() {
        return zimbraPrefReadingPaneEnabled;
    }

    public void setZimbraPrefReadingPaneEnabled(Boolean zimbraPrefReadingPaneEnabled) {
        this.zimbraPrefReadingPaneEnabled = zimbraPrefReadingPaneEnabled;
    }

    public ReadingPaneLocation getZimbraPrefReadingPaneLocation() {
        return zimbraPrefReadingPaneLocation;
    }

    public void setZimbraPrefReadingPaneLocation(ReadingPaneLocation zimbraPrefReadingPaneLocation) {
        this.zimbraPrefReadingPaneLocation = zimbraPrefReadingPaneLocation;
    }

    public Integer getZimbraPrefReadingPaneSashHorizontal() {
        return zimbraPrefReadingPaneSashHorizontal;
    }

    public void setZimbraPrefReadingPaneSashHorizontal(Integer zimbraPrefReadingPaneSashHorizontal) {
        this.zimbraPrefReadingPaneSashHorizontal = zimbraPrefReadingPaneSashHorizontal;
    }

    public Boolean getZimbraPrefShowFragments() {
        return zimbraPrefShowFragments;
    }

    public void setZimbraPrefShowFragments(Boolean zimbraPrefShowFragments) {
        this.zimbraPrefShowFragments = zimbraPrefShowFragments;
    }
}
