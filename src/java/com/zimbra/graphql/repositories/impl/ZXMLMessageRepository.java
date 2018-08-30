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

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.Element;
import com.zimbra.common.soap.MailConstants;
import com.zimbra.cs.service.mail.GetMsg;
import com.zimbra.cs.service.mail.ItemAction;
import com.zimbra.cs.service.mail.MsgAction;
import com.zimbra.cs.service.mail.SendMsg;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.repositories.IRepository;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.XMLDocumentUtilities;
import com.zimbra.soap.ZimbraSoapContext;
import com.zimbra.soap.mail.message.GetMsgRequest;
import com.zimbra.soap.mail.message.SendMsgRequest;
import com.zimbra.soap.mail.type.Msg;
import com.zimbra.soap.mail.type.MsgSpec;
import com.zimbra.soap.mail.type.MsgToSend;
import com.zimbra.soap.mail.type.MsgWithGroupInfo;

/**
 * The ZXMLMessageRepository class.<br>
 * Contains XML document based data access methods for messages.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.repositories.impl
 * @copyright Copyright © 2018
 */
public class ZXMLMessageRepository extends ZXMLItemRepository implements IRepository {

    /**
     * The getMsg document handler.
     */
    protected final GetMsg getMessageHandler;

    /**
     * The sendMsg document handler.
     */
    protected final SendMsg sendMessageHandler;

    /**
     * Creates an instance with default document handlers.
     */
    public ZXMLMessageRepository() {
        this(new MsgAction(), new GetMsg(), new SendMsg());
    }

    /**
     * Creates an instance with specified handlers.
     *
     * @param actionHandler The item action handler
     * @param getHandler The get message handler
     * @param sendHandler The send message handler
     */
    public ZXMLMessageRepository(ItemAction actionHandler,
        GetMsg getHandler, SendMsg sendHandler) {
        super(actionHandler);
        this.getMessageHandler = getHandler;
        this.sendMessageHandler = sendHandler;
    }

    /**
     * Retrieves a message by given properties.
     *
     * @param rctxt The request context
     * @param MsgSpec The primary message identifiers
     * @return Fetch results
     * @throws ServiceException If there are issues executing the document
     */
    public Msg message(RequestContext rctxt, MsgSpec messageSpecifications)
        throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        // map the request params
        final GetMsgRequest req = new GetMsgRequest(messageSpecifications);
        // execute
        final Element response = XMLDocumentUtilities.executeDocument(
            getMessageHandler,
            zsc,
            XMLDocumentUtilities.toElement(req));
        Msg message = null;
        if (response != null) {
            message = XMLDocumentUtilities
                .fromElement(response.getElement(MailConstants.E_MSG), Msg.class);
        }
        return message;
    }

    /**
     * Sends a message with given input.
     *
     * @param rctxt The request context
     * @param addSentBy Denotes whether to add sent-by parameter when sending on
     *            behalf of someone
     * @param isCalendarForward Denotes whether this a forward of calendar invitation
     * @param doSkipSave Denotes whether to skip saving a copy (regardless of account/identity settings)
     * @param doFetchSaved Denotes whether to return a copy of sent message, if it was saved
     * @param sendUid Send UID
     * @param message The message to send
     * @return Message data with id
     * @throws ServiceException If there are issues executing the document
     */
    public MsgWithGroupInfo messageSend(RequestContext rctxt, Boolean addSentBy,
        Boolean isCalendarForward, Boolean doSkipSave, Boolean doFetchSaved, String sendUid,
        MsgToSend messageToSend) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        // map the request params
        final SendMsgRequest req = new SendMsgRequest();
        req.setNeedCalendarSentbyFixup(addSentBy);
        req.setIsCalendarForward(isCalendarForward);
        req.setNoSaveToSent(doSkipSave);
        req.setFetchSavedMsg(doFetchSaved);
        req.setSendUid(sendUid);
        req.setMsg(messageToSend);
        // execute
        final Element response = XMLDocumentUtilities.executeDocument(
            sendMessageHandler,
            zsc,
            XMLDocumentUtilities.toElement(req));
        MsgWithGroupInfo message = null;
        if (response != null) {
            message = XMLDocumentUtilities
                .fromElement(response.getElement(MailConstants.E_MSG), MsgWithGroupInfo.class);
        }
        return message;
    }
}
