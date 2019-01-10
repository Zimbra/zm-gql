/*
 * ***** BEGIN LICENSE BLOCK *****
 * Zimbra GraphQL Extension
 * Copyright (C) 2019 Synacor, Inc.
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
import com.zimbra.graphql.repositories.impl.ZXMLTaskRepository;
import com.zimbra.graphql.resolvers.IResolver;
import com.zimbra.soap.mail.message.CreateTaskExceptionResponse;
import com.zimbra.soap.mail.message.CreateTaskResponse;
import com.zimbra.soap.mail.message.ModifyTaskResponse;
import com.zimbra.soap.mail.type.CalTZInfo;
import com.zimbra.soap.mail.type.InstanceRecurIdInfo;
import com.zimbra.soap.mail.type.Msg;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLRootContext;

/**
 * The TaskResolver class.<br>
 * Contains task schema resources.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.resolvers.impl
 * @copyright Copyright © 2019
 */
public class TaskResolver implements IResolver {

    protected ZXMLTaskRepository taskRepository = null;

    /**
     * Creates an instance with specified task repository.
     *
     * @param taskRepository The task repository
     */
    public TaskResolver(ZXMLTaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GraphQLMutation(description="Create a task")
    public CreateTaskResponse taskCreate(
        @GraphQLArgument(name=GqlConstants.INCLUDE_ECHO, description="Denotes whether to return created task in the response") Boolean includeEcho,
        @GraphQLArgument(name=GqlConstants.MAX_SIZE, description="Maximum inline length") Integer maxSize,
        @GraphQLArgument(name=GqlConstants.INCLUDE_HTML, description="Denotes whether to include html in response echo") Boolean includeHtml,
        @GraphQLArgument(name=GqlConstants.DO_NEUTER, description="Denotes whether to neuter elements of the echo (e.g. images)") Boolean doNeuter,
        @GraphQLArgument(name=GqlConstants.DO_FORCE, description="Denotes whether to force send the message") Boolean doForce,
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.MESSAGE, description="Message") Msg message,
        @GraphQLRootContext RequestContext rctxt) throws ServiceException {
        return taskRepository.taskCreate(rctxt, includeEcho, maxSize, includeHtml,
            doNeuter, doForce, message);
    }

    @GraphQLMutation(description="Create a task exception")
    public CreateTaskExceptionResponse taskExceptionCreate(
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.ID, description="Invite ID of default invite") String id,
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.COMPONENT_NUMBER, description="Component number of default component") Integer componentNumber,
        @GraphQLArgument(name=GqlConstants.MODIFIED_SEQUENCE, description="Change sequence of fetched version. Used for conflict detection") Integer modifiedSequence,
        @GraphQLArgument(name=GqlConstants.REVISION, description="Revision") Integer revision,
        @GraphQLArgument(name=GqlConstants.INCLUDE_ECHO, description="Denotes whether to return created task in the response") Boolean includeEcho,
        @GraphQLArgument(name=GqlConstants.MAX_SIZE, description="Maximum inline length") Integer maxSize,
        @GraphQLArgument(name=GqlConstants.INCLUDE_HTML, description="Denotes whether to include html in response echo") Boolean includeHtml,
        @GraphQLArgument(name=GqlConstants.DO_NEUTER, description="Denotes whether to neuter elements of the echo (e.g. images)") Boolean doNeuter,
        @GraphQLArgument(name=GqlConstants.DO_FORCE, description="Denotes whether to force send the message") Boolean doForce,
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.MESSAGE, description="Message") Msg message,
        @GraphQLRootContext RequestContext rctxt) throws ServiceException {
        return taskRepository.taskExceptionCreate(rctxt, id, componentNumber,
            modifiedSequence, revision, includeEcho, maxSize, includeHtml, doNeuter, doForce,
            message);
    }

    @GraphQLMutation(description="Modify a task")
    public ModifyTaskResponse taskModify(
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.ID, description="Invite ID of default invite") String id,
        @GraphQLArgument(name=GqlConstants.COMPONENT_NUMBER, description="Component number of default component") Integer componentNumber,
        @GraphQLArgument(name=GqlConstants.MODIFIED_SEQUENCE, description="Change sequence of fetched version. Used for conflict detection.") Integer modifiedSequence,
        @GraphQLArgument(name=GqlConstants.REVISION, description="Revision") Integer revision,
        @GraphQLArgument(name=GqlConstants.INCLUDE_ECHO, description="Denotes whether to return created task in the response") Boolean includeEcho,
        @GraphQLArgument(name=GqlConstants.MAX_SIZE, description="Maximum inline length") Integer maxSize,
        @GraphQLArgument(name=GqlConstants.INCLUDE_HTML, description="Denotes whether to include html in response echo") Boolean includeHtml,
        @GraphQLArgument(name=GqlConstants.DO_NEUTER, description="Denotes whether to neuter elements of the echo (e.g. images)") Boolean doNeuter,
        @GraphQLArgument(name=GqlConstants.DO_FORCE, description="Denotes whether to force send the message") Boolean doForce,
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.MESSAGE, description="Message") Msg message,
        @GraphQLRootContext RequestContext rctxt) throws ServiceException {
        return taskRepository.taskModify(rctxt, id, componentNumber, modifiedSequence,
            revision, includeEcho, maxSize, includeHtml, doNeuter, doForce, message);
    }

    @GraphQLMutation(description="Cancel a task")
    public Boolean taskCancel(
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.ID, description="Invite ID of default invite") String id,
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.COMPONENT_NUMBER, description="Component number of default component") Integer componentNumber,
        @GraphQLArgument(name=GqlConstants.MODIFIED_SEQUENCE, description="Change sequence of fetched version. Used for conflict detection.") Integer modifiedSequence,
        @GraphQLArgument(name=GqlConstants.REVISION, description="Revision") Integer revision,
        @GraphQLArgument(name=GqlConstants.INSTANCE, description="Instance recurrence ID information") InstanceRecurIdInfo instance,
        @GraphQLArgument(name=GqlConstants.TIMEZONE, description="Definition for TZID reference by datetime in instance") CalTZInfo timezone,
        @GraphQLArgument(name=GqlConstants.MESSAGE, description="Message") Msg message,
        @GraphQLRootContext RequestContext rctxt) throws ServiceException {
        return taskRepository.taskCancel(rctxt, id, componentNumber, modifiedSequence,
            revision, instance, timezone, message);
    }

}
