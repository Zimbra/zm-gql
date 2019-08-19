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
package com.zimbra.graphql.repositories.impl;

import org.dom4j.QName;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.Element;
import com.zimbra.cs.service.mail.CancelTask;
import com.zimbra.cs.service.mail.CreateTask;
import com.zimbra.cs.service.mail.CreateTaskException;
import com.zimbra.cs.service.mail.ModifyTask;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.repositories.IRepository;
import com.zimbra.graphql.repositories.ZXMLRepository;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.XMLDocumentUtilities;
import com.zimbra.soap.ZimbraSoapContext;
import com.zimbra.soap.mail.message.CancelTaskRequest;
import com.zimbra.soap.mail.message.CreateTaskExceptionRequest;
import com.zimbra.soap.mail.message.CreateTaskExceptionResponse;
import com.zimbra.soap.mail.message.CreateTaskRequest;
import com.zimbra.soap.mail.message.CreateTaskResponse;
import com.zimbra.soap.mail.message.ModifyTaskRequest;
import com.zimbra.soap.mail.message.ModifyTaskResponse;
import com.zimbra.soap.mail.type.CalTZInfo;
import com.zimbra.soap.mail.type.InstanceRecurIdInfo;
import com.zimbra.soap.mail.type.Msg;

/**
 * The ZXMLTaskRepository class.<br>
 * Contains XML document based data access methods for task.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.repositories.impl
 * @copyright Copyright © 2019
 */
public class ZXMLTaskRepository extends ZXMLRepository implements IRepository {

    /**
     * The createTask document handler.
     */
    protected final CreateTask createTaskHandler;

    /**
     * The createTaskException document handler.
     */
    protected final CreateTaskException createTaskExceptionHandler;

    /**
     * The modifyTask document handler.
     */
    protected final ModifyTask modifyTaskHandler;

    /**
     * The cancelTask document handler.
     */
    protected final CancelTask cancelTaskHandler;

    /**
     * Creates an instance with default document handlers.
     */
    public ZXMLTaskRepository() {
        this(new CreateTask(), new CreateTaskException(),
            new ModifyTask(), new CancelTask());
    }

    /**
     * Creates an instance with specified handlers.
     *
     * @param createTaskHandler The createTask handler
     * @param createTaskExceptionHandler The createTaskException handler
     * @param modifyTask The modifyTask handler
     * @param cancelTaskHandler The cancelTask handler
     */
    public ZXMLTaskRepository(CreateTask createTaskHandler,
        CreateTaskException createTaskExceptionHandler,
        ModifyTask modifyTaskHandler, CancelTask cancelTaskHandler) {
        super();
        this.createTaskHandler = createTaskHandler;
        this.createTaskExceptionHandler = createTaskExceptionHandler;
        this.modifyTaskHandler = modifyTaskHandler;
        this.cancelTaskHandler = cancelTaskHandler;
        // set response models
        this.createTaskHandler.setResponseQName(QName.get("CreateTaskResponse"));
        this.createTaskExceptionHandler.setResponseQName(QName.get("CreateTaskExceptionResponse"));
        this.modifyTaskHandler.setResponseQName(QName.get("ModifyTaskResponse"));
        this.cancelTaskHandler.setResponseQName(QName.get("CancelTaskResponse"));
    }

    /**
     * Creates a task.
     *
     * @param rctxt The request context
     * @param includeEcho Denotes whether to return created task in the response
     * @param maxSize Maximum inline length
     * @param includeHtml Denotes whether to include html in response echo
     * @param doNeuter Denotes whether to neuter elements of the echo (e.g. images)
     * @param doForce Denotes whether to force send the message
     * @param message Message
     * @return Create task response
     * @throws ServiceException If there are issues executing the document
     */
    public CreateTaskResponse taskCreate(RequestContext rctxt, Boolean includeEcho, Integer maxSize,
        Boolean includeHtml, Boolean doNeuter, Boolean doForce, Msg message) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final CreateTaskRequest request = new CreateTaskRequest();
        request.setEcho(includeEcho);
        request.setWantHtml(includeHtml);
        request.setMaxSize(maxSize);
        request.setNeuter(doNeuter);
        request.setForceSend(doForce);
        request.setMsg(message);
        final Element response = XMLDocumentUtilities.executeDocument(
                createTaskHandler,
            zsc,
            XMLDocumentUtilities.toElement(request),
            rctxt);
        CreateTaskResponse resp = null;
        if (response != null) {
            resp = XMLDocumentUtilities.fromElement(response,
                CreateTaskResponse.class);
        }
        return resp;
    }

    /**
     * Creates a task exception.
     *
     * @param rctxt The request context
     * @param id Invite ID of default invite
     * @param componentNumber Component number of default component
     * @param modifiedSequence Change sequence of fetched version. Used for conflict detection
     * @param revision Revision
     * @param includeEcho Denotes whether to return created task in the response
     * @param maxSize Maximum inline length
     * @param includeHtml Denotes whether to include html in response echo
     * @param doNeuter Denotes whether to neuter elements of the echo (e.g. images)
     * @param doForce Denotes whether to force send the message
     * @param message Message
     * @return Create task exception response
     * @throws ServiceException If there are issues executing the document
     */
    public CreateTaskExceptionResponse taskExceptionCreate(RequestContext rctxt, String id,
        Integer componentNumber, Integer modifiedSequence, Integer revision, Boolean includeEcho, Integer maxSize,
        Boolean includeHtml, Boolean doNeuter, Boolean doForce, Msg message) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final CreateTaskExceptionRequest request = new CreateTaskExceptionRequest();
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
            createTaskExceptionHandler,
            zsc,
            XMLDocumentUtilities.toElement(request),
            rctxt);
        CreateTaskExceptionResponse resp = null;
        if (response != null) {
            resp = XMLDocumentUtilities.fromElement(response,
                CreateTaskExceptionResponse.class);
        }
        return resp;
    }

    /**
     * Modifies a task.
     *
     * @param rctxt The request context
     * @param id Invite ID of default invite
     * @param componentNumber Component number of default invite
     * @param modifiedSequence Change sequence of fetched version. Used for conflict detection
     * @param revision Revision
     * @param includeEcho Denotes whether to return modified task in the response
     * @param maxSize Maximum inline length
     * @param includeHtml Denotes whether to include html in response echo
     * @param doNeuter Denotes whether to neuter elements of the echo (e.g. images)
     * @param doForce Denotes whether to force send the message
     * @param message Message
     * @return Modify appointment response
     * @throws ServiceException If there are issues executing the document
     */
    public ModifyTaskResponse taskModify(RequestContext rctxt, String id,
        Integer componentNumber, Integer modifiedSequence, Integer revision, Boolean includeEcho,
        Integer maxSize, Boolean includeHtml, Boolean doNeuter, Boolean doForce, Msg message)
        throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final ModifyTaskRequest request = new ModifyTaskRequest();
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
            modifyTaskHandler,
            zsc,
            XMLDocumentUtilities.toElement(request),
            rctxt);
        ModifyTaskResponse resp = null;
        if (response != null) {
            resp = XMLDocumentUtilities.fromElement(response,
                ModifyTaskResponse.class);
        }
        return resp;
    }

    /**
     * Cancels a task.
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
    public Boolean taskCancel(RequestContext rctxt, String id,
        Integer componentNumber, Integer modifiedSequence, Integer revision,
        InstanceRecurIdInfo instance, CalTZInfo timezone, Msg message)
        throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final CancelTaskRequest request = new CancelTaskRequest();
        request.setId(id);
        request.setComponentNum(componentNumber);
        request.setModifiedSequence(modifiedSequence);
        request.setRevision(revision);
        request.setInstance(instance);
        request.setTimezone(timezone);
        request.setMsg(message);
        XMLDocumentUtilities.executeDocument(
            cancelTaskHandler,
            zsc,
            XMLDocumentUtilities.toElement(request),
            rctxt);
        return true;
    }

}
