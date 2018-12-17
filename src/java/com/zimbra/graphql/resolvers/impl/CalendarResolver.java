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
package com.zimbra.graphql.resolvers.impl;

import com.zimbra.common.gql.GqlConstants;
import com.zimbra.common.service.ServiceException;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.models.inputs.GQLInviteReplyVerbInput;
import com.zimbra.graphql.repositories.impl.ZXMLCalendarRepository;
import com.zimbra.soap.mail.message.CreateAppointmentExceptionResponse;
import com.zimbra.soap.mail.message.CreateAppointmentResponse;
import com.zimbra.soap.mail.message.ModifyAppointmentResponse;
import com.zimbra.soap.mail.message.SendInviteReplyResponse;
import com.zimbra.soap.mail.type.CalTZInfo;
import com.zimbra.soap.mail.type.DtTimeInfo;
import com.zimbra.soap.mail.type.InstanceRecurIdInfo;
import com.zimbra.soap.mail.type.Msg;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLRootContext;

/**
 * The CalendarResolver class.<br>
 * Contains calendar schema resources.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.resolvers.impl
 * @copyright Copyright © 2018
 */
public class CalendarResolver {

    protected ZXMLCalendarRepository calendarRepository = null;

    /**
     * Creates an instance with specified calendar repository.
     *
     * @param calendarRepository The calendar repository
     */
    public CalendarResolver(ZXMLCalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @GraphQLMutation(description="Create an appointment")
    public CreateAppointmentResponse appointmentCreate(@GraphQLArgument(name=GqlConstants.INCLUDE_ECHO, description="Denotes whether to return created appointment in the response") Boolean includeEcho,
        @GraphQLArgument(name=GqlConstants.MAX_SIZE, description="Maximum inline length") Integer maxSize,
        @GraphQLArgument(name=GqlConstants.INCLUDE_HTML, description="Denotes whether to include html in response echo") Boolean includeHtml,
        @GraphQLArgument(name=GqlConstants.DO_NEUTER, description="Denotes whether to neuter elements of the echo (e.g. images)") Boolean doNeuter,
        @GraphQLArgument(name=GqlConstants.DO_FORCE, description="Denotes whether to force send the message") Boolean doForce,
        @GraphQLArgument(name=GqlConstants.MESSAGE, description="Message") Msg message,
        @GraphQLRootContext RequestContext rctxt) throws ServiceException {
        return calendarRepository.appointmentCreate(rctxt, includeEcho, maxSize, includeHtml, doNeuter, doForce, message);
    }

    @GraphQLMutation(description="Create an appointment exception")
    public CreateAppointmentExceptionResponse appointmentExceptionCreate(@GraphQLArgument(name=GqlConstants.ID, description="Invite ID of default invite") String id,
        @GraphQLArgument(name=GqlConstants.COMPONENT_NUMBER, description="Component number of default component") Integer componentNumber,
        @GraphQLArgument(name=GqlConstants.MODIFIED_SEQUENCE, description="Change sequence of fetched version. Used for conflict detection") Integer modifiedSequence,
        @GraphQLArgument(name=GqlConstants.REVISION, description="Revision") Integer revision,
        @GraphQLArgument(name=GqlConstants.INCLUDE_ECHO, description="Denotes whether to return created appointment in the response") Boolean includeEcho,
        @GraphQLArgument(name=GqlConstants.MAX_SIZE, description="Maximum inline length") Integer maxSize,
        @GraphQLArgument(name=GqlConstants.INCLUDE_HTML, description="Denotes whether to include html in response echo") Boolean includeHtml,
        @GraphQLArgument(name=GqlConstants.DO_NEUTER, description="Denotes whether to neuter elements of the echo (e.g. images)") Boolean doNeuter,
        @GraphQLArgument(name=GqlConstants.DO_FORCE, description="Denotes whether to force send the message") Boolean doForce,
        @GraphQLArgument(name=GqlConstants.MESSAGE, description="Message") Msg message,
        @GraphQLRootContext RequestContext rctxt) throws ServiceException {
        return calendarRepository.appointmentExceptionCreate(rctxt, id, componentNumber, modifiedSequence, revision, includeEcho, maxSize, includeHtml, doNeuter, doForce, message);
    }

    @GraphQLMutation(description="Modify an appointment")
    public ModifyAppointmentResponse appointmentModify(@GraphQLArgument(name=GqlConstants.ID, description="Invite ID of default invite") String id,
        @GraphQLArgument(name=GqlConstants.COMPONENT_NUMBER, description="Component number of default component") Integer componentNumber,
        @GraphQLArgument(name=GqlConstants.MODIFIED_SEQUENCE, description="Change sequence of fetched version. Used for conflict detection.") Integer modifiedSequence,
        @GraphQLArgument(name=GqlConstants.REVISION, description="Revision") Integer revision,
        @GraphQLArgument(name=GqlConstants.INCLUDE_ECHO, description="Denotes whether to return created appointment in the response") Boolean includeEcho,
        @GraphQLArgument(name=GqlConstants.MAX_SIZE, description="Maximum inline length") Integer maxSize,
        @GraphQLArgument(name=GqlConstants.INCLUDE_HTML, description="Denotes whether to include html in response echo") Boolean includeHtml,
        @GraphQLArgument(name=GqlConstants.DO_NEUTER, description="Denotes whether to neuter elements of the echo (e.g. images)") Boolean doNeuter,
        @GraphQLArgument(name=GqlConstants.DO_FORCE, description="Denotes whether to force send the message") Boolean doForce,
        @GraphQLArgument(name=GqlConstants.MESSAGE, description="Message") Msg message,
        @GraphQLRootContext RequestContext rctxt) throws ServiceException {
        return calendarRepository.appointmentModify(rctxt, id, componentNumber, modifiedSequence, revision, includeEcho, maxSize, includeHtml, doNeuter, doForce, message);
    }

    @GraphQLMutation(description="Reply to an invite")
    public SendInviteReplyResponse inviteReply(@GraphQLNonNull @GraphQLArgument(name=GqlConstants.ID, description="ID of invite to reply to") String id,
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.COMPONENT_NUMBER, description="Component number of the invite") Integer componentNumber,
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.VERB, description="Invite action") GQLInviteReplyVerbInput verb,
        @GraphQLArgument(name=GqlConstants.DO_UPDATE_ORGANIZER, description="Denotes whether to update the organizer", defaultValue="true") Boolean doUpdateOrganizer,
        @GraphQLArgument(name=GqlConstants.IDENTITY_ID, description="Identity id to use to send reply") String identityId,
        @GraphQLArgument(name=GqlConstants.EXCEPTION_ID, description="If supplied then reply to a single instance of the specified Invite (default is all)") DtTimeInfo exceptionId,
        @GraphQLArgument(name=GqlConstants.TIMEZONE, description="Definition for TZID reference by datetime in exceptionId") CalTZInfo timezone,
        @GraphQLArgument(name=GqlConstants.MESSAGE, description="Message") Msg message,
        @GraphQLRootContext RequestContext rctxt) throws ServiceException {
        return calendarRepository.inviteReply(rctxt, id, componentNumber, verb, doUpdateOrganizer, identityId, exceptionId, timezone, message);
    }

    @GraphQLMutation(description="Cancel an appointment")
    public Boolean appointmentCancel(@GraphQLArgument(name=GqlConstants.ID, description="Invite ID of default invite") String id,
        @GraphQLArgument(name=GqlConstants.COMPONENT_NUMBER, description="Component number of default component") Integer componentNumber,
        @GraphQLArgument(name=GqlConstants.MODIFIED_SEQUENCE, description="Change sequence of fetched version. Used for conflict detection.") Integer modifiedSequence,
        @GraphQLArgument(name=GqlConstants.REVISION, description="Revision") Integer revision,
        @GraphQLArgument(name=GqlConstants.INSTANCE, description="Instance recurrence ID information") InstanceRecurIdInfo instance,
        @GraphQLArgument(name=GqlConstants.TIMEZONE, description="Definition for TZID reference by datetime in instance") CalTZInfo timezone,
        @GraphQLArgument(name=GqlConstants.MESSAGE, description="Message") Msg message,
        @GraphQLRootContext RequestContext rctxt) throws ServiceException {
        return calendarRepository.appointmentCancel(rctxt, id, componentNumber, modifiedSequence, revision, instance, timezone, message);
    }
}
