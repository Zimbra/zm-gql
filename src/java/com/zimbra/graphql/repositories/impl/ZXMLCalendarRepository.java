/*
 * ***** BEGIN LICENSE BLOCK *****
 * Zimbra GraphQL Extension
 * Copyright (C) 2018 Synacor, Inc.
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software Foundation,
 * version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 * ***** END LICENSE BLOCK *****
 */
package com.zimbra.graphql.repositories.impl;

import java.util.Collections;
import java.util.List;

import org.dom4j.QName;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.Element;
import com.zimbra.common.soap.MailConstants;
import com.zimbra.cs.service.mail.CancelAppointment;
import com.zimbra.cs.service.mail.CreateAppointment;
import com.zimbra.cs.service.mail.CreateAppointmentException;
import com.zimbra.cs.service.mail.GetFreeBusy;
import com.zimbra.cs.service.mail.ModifyAppointment;
import com.zimbra.cs.service.mail.SendInviteReply;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.models.inputs.GQLInviteReplyVerbInput;
import com.zimbra.graphql.repositories.IRepository;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.XMLDocumentUtilities;
import com.zimbra.soap.ZimbraSoapContext;
import com.zimbra.soap.mail.message.CancelAppointmentRequest;
import com.zimbra.soap.mail.message.CreateAppointmentExceptionRequest;
import com.zimbra.soap.mail.message.CreateAppointmentExceptionResponse;
import com.zimbra.soap.mail.message.CreateAppointmentRequest;
import com.zimbra.soap.mail.message.CreateAppointmentResponse;
import com.zimbra.soap.mail.message.GetFreeBusyRequest;
import com.zimbra.soap.mail.message.GetFreeBusyResponse;
import com.zimbra.soap.mail.message.ModifyAppointmentRequest;
import com.zimbra.soap.mail.message.ModifyAppointmentResponse;
import com.zimbra.soap.mail.message.SendInviteReplyRequest;
import com.zimbra.soap.mail.message.SendInviteReplyResponse;
import com.zimbra.soap.mail.type.CalTZInfo;
import com.zimbra.soap.mail.type.DtTimeInfo;
import com.zimbra.soap.mail.type.FreeBusyUserInfo;
import com.zimbra.soap.mail.type.FreeBusyUserSpec;
import com.zimbra.soap.mail.type.InstanceRecurIdInfo;
import com.zimbra.soap.mail.type.Msg;

/**
 * The ZXMLCalendarRepository class.<br>
 * Contains XML document based data access methods for calendar.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.repositories.impl
 * @copyright Copyright © 2018
 */
public class ZXMLCalendarRepository extends ZXMLRepository implements IRepository {

    /**
     * The createAppointment document handler.
     */
    protected final CreateAppointment createAppointmentHandler;

    /**
     * The createAppointmentException document handler.
     */
    protected final CreateAppointmentException createAppointmentExceptionHandler;

    /**
     * The modifyAppointment document handler.
     */
    protected final ModifyAppointment modifyAppointmentHandler;

    /**
     * The cancelAppointment document handler.
     */
    protected final CancelAppointment cancelAppointmentHandler;

    /**
     * The inviteReply document handler.
     */
    protected final SendInviteReply inviteReplyHandler;

    /**
     * The getFreeBusy document handler.
     */
    protected final GetFreeBusy getFreeBusyHandler;

    /**
     * Creates an instance with default document handlers.
     */
    public ZXMLCalendarRepository() {
        this(new CreateAppointment(), new CreateAppointmentException(),
            new ModifyAppointment(), new CancelAppointment(), new SendInviteReply(),
            new GetFreeBusy());
    }

    /**
     * Creates an instance with specified handlers.
     *
     * @param createAppointmentHandler The createAppointment handler
     * @param createAppointmentExceptionHandler The createAppointmentException handler
     * @param modifyAppointment The modifyAppointment handler
     * @param cancelAppointmentHandler The cancelAppointment handler
     * @param sendInviteReplyHandler The inviteReply handler
     * @param getFreeBusyHandler The getFreeBusy handler
     */
    public ZXMLCalendarRepository(CreateAppointment createAppointmentHandler,
        CreateAppointmentException createAppointmentExceptionHandler,
        ModifyAppointment modifyAppointmentHandler, CancelAppointment cancelAppointmentHandler,
        SendInviteReply inviteReplyHandler, GetFreeBusy getFreeBusyHandler) {
        super();
        this.createAppointmentHandler = createAppointmentHandler;
        this.createAppointmentExceptionHandler = createAppointmentExceptionHandler;
        this.modifyAppointmentHandler = modifyAppointmentHandler;
        this.cancelAppointmentHandler = cancelAppointmentHandler;
        this.inviteReplyHandler = inviteReplyHandler;
        this.getFreeBusyHandler = getFreeBusyHandler;
        // set response models
        this.createAppointmentHandler.setResponseQName(QName.get("CreateAppointmentResponse"));
        this.createAppointmentExceptionHandler.setResponseQName(QName.get("CreateAppointmentExceptionResponse"));
        this.modifyAppointmentHandler.setResponseQName(QName.get("ModifyAppointmentResponse"));
        this.cancelAppointmentHandler.setResponseQName(QName.get("CancelAppointmentResponse"));
        this.inviteReplyHandler.setResponseQName(QName.get("SendInviteReplyResponse"));
        this.getFreeBusyHandler.setResponseQName(MailConstants.GET_FREE_BUSY_RESPONSE);
    }

    /**
     * Creates an appointment.
     *
     * @param rctxt The request context
     * @param includeEcho Denotes whether to return created appointment in the response
     * @param maxSize Maximum inline length
     * @param includeHtml Denotes whether to include html in response echo
     * @param doNeuter Denotes whether to neuter elements of the echo (e.g. images)
     * @param doForce Denotes whether to force send the messag
     * @param message Message
     * @return Create appointment response
     * @throws ServiceException If there are issues executing the document
     */
    public CreateAppointmentResponse appointmentCreate(RequestContext rctxt, Boolean includeEcho, Integer maxSize,
        Boolean includeHtml, Boolean doNeuter, Boolean doForce, Msg message) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final CreateAppointmentRequest request = new CreateAppointmentRequest();
        request.setEcho(includeEcho);
        request.setWantHtml(includeHtml);
        request.setMaxSize(maxSize);
        request.setNeuter(doNeuter);
        request.setForceSend(doForce);
        request.setMsg(message);
        final Element response = XMLDocumentUtilities.executeDocument(
            createAppointmentHandler,
            zsc,
            XMLDocumentUtilities.toElement(request),
            rctxt);
        CreateAppointmentResponse resp = null;
        if (response != null) {
            resp = XMLDocumentUtilities.fromElement(response,
                CreateAppointmentResponse.class);
        }
        return resp;
    }

    /**
     * Creates an appointment exception.
     *
     * @param rctxt The request context
     * @param id Invite ID of default invite
     * @param componentNumber Component number of default component
     * @param modifiedSequence Change sequence of fetched version. Used for conflict detection
     * @param revision Revision
     * @param includeEcho Denotes whether to return created appointment in the response
     * @param maxSize Maximum inline length
     * @param includeHtml Denotes whether to include html in response echo
     * @param doNeuter Denotes whether to neuter elements of the echo (e.g. images)
     * @param doForce Denotes whether to force send the messag
     * @param message Message
     * @return Create appointment exception response
     * @throws ServiceException If there are issues executing the document
     */
    public CreateAppointmentExceptionResponse appointmentExceptionCreate(RequestContext rctxt, String id,
        Integer componentNumber, Integer modifiedSequence, Integer revision, Boolean includeEcho, Integer maxSize,
        Boolean includeHtml, Boolean doNeuter, Boolean doForce, Msg message) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final CreateAppointmentExceptionRequest request = new CreateAppointmentExceptionRequest();
        request.setId(id);
        request.setNumComponents(componentNumber);
        request.setModifiedSequence(modifiedSequence);
        request.setRevision(revision);
        request.setEcho(includeEcho);
        request.setWantHtml(includeHtml);
        request.setMaxSize(maxSize);
        request.setNeuter(doNeuter);
        request.setForceSend(doForce);
        request.setMsg(message);
        final Element response = XMLDocumentUtilities.executeDocument(
            createAppointmentExceptionHandler,
            zsc,
            XMLDocumentUtilities.toElement(request),
            rctxt);
        CreateAppointmentExceptionResponse resp = null;
        if (response != null) {
            resp = XMLDocumentUtilities.fromElement(response,
                CreateAppointmentExceptionResponse.class);
        }
        return resp;
    }

    /**
     * Modifies an appointment.
     *
     * @param rctxt The request context
     * @param id Invite ID of default invite
     * @param componentNumber Component number of default invite
     * @param modifiedSequence Change sequence of fetched version. Used for conflict detection
     * @param revision Revision
     * @param includeEcho Denotes whether to return created appointment in the response
     * @param maxSize Maximum inline length
     * @param includeHtml Denotes whether to include html in response echo
     * @param doNeuter Denotes whether to neuter elements of the echo (e.g. images)
     * @param doForce Denotes whether to force send the messag
     * @param message Message
     * @return Modify appointment response
     * @throws ServiceException If there are issues executing the document
     */
    public ModifyAppointmentResponse appointmentModify(RequestContext rctxt, String id,
        Integer componentNumber, Integer modifiedSequence, Integer revision, Boolean includeEcho,
        Integer maxSize, Boolean includeHtml, Boolean doNeuter, Boolean doForce, Msg message)
        throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final ModifyAppointmentRequest request = new ModifyAppointmentRequest();
        request.setId(id);
        request.setComponentNum(componentNumber);
        request.setEcho(includeEcho);
        request.setModifiedSequence(modifiedSequence);
        request.setRevision(revision);
        request.setMaxSize(maxSize);
        request.setWantHtml(includeHtml);
        request.setNeuter(doNeuter);
        request.setForceSend(doForce);
        request.setMsg(message);
        final Element response = XMLDocumentUtilities.executeDocument(
            modifyAppointmentHandler,
            zsc,
            XMLDocumentUtilities.toElement(request),
            rctxt);
        ModifyAppointmentResponse resp = null;
        if (response != null) {
            resp = XMLDocumentUtilities.fromElement(response,
                ModifyAppointmentResponse.class);
        }
        return resp;
    }

    /**
     * Replies to an invite.
     *
     * @param rctxt The request context
     * @param id Invite ID of default invite
     * @param componentNumber Component number of default invite
     * @param verb Invite action
     * @param doUpdateOrganizer Denotes whether to update the organizer
     * @param identityId Identity id to use to send reply
     * @param exceptionId If supplied then reply to a single instance of the specified Invite (default is all)
     * @param timezone Definition for TZID reference by datetime in instance
     * @param message Message
     * @return Send invite reply response
     * @throws ServiceException If there are issues executing the document
     */
    public SendInviteReplyResponse inviteReply(RequestContext rctxt, String id,
        Integer componentNumber, GQLInviteReplyVerbInput verb, Boolean doUpdateOrganizer,
        String identityId, DtTimeInfo exceptionId, CalTZInfo timezone, Msg message)
        throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final SendInviteReplyRequest request = new SendInviteReplyRequest(id, componentNumber, verb.name());
        request.setUpdateOrganizer(doUpdateOrganizer);
        request.setIdentityId(identityId);
        request.setExceptionId(exceptionId);
        request.setTimezone(timezone);
        request.setMsg(message);
        final Element response = XMLDocumentUtilities.executeDocument(
            inviteReplyHandler,
            zsc,
            XMLDocumentUtilities.toElement(request),
            rctxt);
        SendInviteReplyResponse resp = null;
        if (response != null) {
            resp = XMLDocumentUtilities.fromElement(response,
                SendInviteReplyResponse.class);
        }
        return resp;
    }

    /**
     * Cancels an appointment.
     *
     * @param rctxt The request context
     * @param id Invite ID of default invite
     * @param componentNumber Component number of default invite
     * @param modifiedSequence Change sequence of fetched version. Used for conflict detection
     * @param revision Revision
     * @param instance Instance recurrence ID information
     * @param timezone Definition for TZID reference by datetime in instance
     * @param message Message
     * @return True if successful
     * @throws ServiceException If there are issues executing the document
     */
    public Boolean appointmentCancel(RequestContext rctxt, String id,
        Integer componentNumber, Integer modifiedSequence, Integer revision,
        InstanceRecurIdInfo instance, CalTZInfo timezone, Msg message)
        throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final CancelAppointmentRequest request = new CancelAppointmentRequest();
        request.setId(id);
        request.setComponentNum(componentNumber);
        request.setModifiedSequence(modifiedSequence);
        request.setRevision(revision);
        request.setInstance(instance);
        request.setTimezone(timezone);
        request.setMsg(message);
        XMLDocumentUtilities.executeDocument(
            cancelAppointmentHandler,
            zsc,
            XMLDocumentUtilities.toElement(request),
            rctxt);
        return true;
    }

    /**
     * Retrieves free/busy status.
     *
     * @param rctxt The request context
     * @param startTime Range start in milliseconds
     * @param endTime Range end in milliseconds
     * @param ids Comma-separated list of ids
     * @param emails Comma-separated list of emails
     * @param excludeUid UID of appointment to exclude from free/busy search
     * @param freeBusyUsers Specify to view free/busy for a single folders in particular accounts
     * @return List of free/busy user information
     * @throws ServiceException If there are issues executing the document
     */
    public List<FreeBusyUserInfo> freeBusy(RequestContext rctxt, Long startTime, Long endTime,
        String ids, String emails, String excludeUid, List<FreeBusyUserSpec> freeBusyUsers)
        throws ServiceException {
        final GetFreeBusyRequest request = new GetFreeBusyRequest(startTime, endTime);
        request.setId(ids);
        request.setName(emails);
        request.setExcludeUid(excludeUid);
        request.setFreebusyUsers(freeBusyUsers);
        final Element response = XMLDocumentUtilities.executeDocumentAsGuest(
            getFreeBusyHandler,
            XMLDocumentUtilities.toElement(request),
            rctxt);
        GetFreeBusyResponse resp = null;
        if (response != null) {
            resp = XMLDocumentUtilities.fromElement(response,
                GetFreeBusyResponse.class);
            return resp.getFreebusyUsers();
        }
        return Collections.emptyList();
    }
}
