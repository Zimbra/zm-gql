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
import com.zimbra.cs.service.mail.ItemAction;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.repositories.IRepository;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.XMLDocumentUtilities;
import com.zimbra.soap.ZimbraSoapContext;
import com.zimbra.soap.mail.message.ItemActionRequest;
import com.zimbra.soap.mail.type.ActionResult;
import com.zimbra.soap.mail.type.ActionSelector;

/**
 * The ZXMLItemRepository class.<br>
 * Contains XML document based data access methods for items.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.repositories.impl
 * @copyright Copyright © 2018
 */
public class ZXMLItemRepository extends ZXMLRepository implements IRepository {

    /**
     * The document handler for actions (e.g. folder, message, etc).
     */
    protected final ItemAction actionHandler;

    /**
     * Constructor.
     *
     * @param actionHandler The document handler for this instance
     */
    public ZXMLItemRepository(ItemAction actionHandler) {
        super();
        this.actionHandler = actionHandler;
    }

    /**
     * Performs an item action with given properties.
     *
     * @param rctxt The request context
     * @param req The request element
     * @return An XML response object
     * @throws ServiceException If there are issues executing the document
     */
    public Element action(RequestContext rctxt, Object req) throws ServiceException {
        final Element response = XMLDocumentUtilities.executeDocument(
            actionHandler,
            GQLAuthUtilities.getZimbraSoapContext(rctxt),
            XMLDocumentUtilities.toElement(req),
            rctxt);
        return response;
    }

    /**
     * Performs an item action with given properties.
     *
     * @param rctxt The request context
     * @param input The input properties to wrap in an item action request
     * @return Action result
     * @throws ServiceException If there are issues executing the document
     */
    public ActionResult itemAction(RequestContext rctxt, ActionSelector input) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final ItemActionRequest req = new ItemActionRequest(input);
        // execute
        final Element response = XMLDocumentUtilities.executeDocument(
            actionHandler,
            zsc,
            XMLDocumentUtilities.toElement(req),
            rctxt);
        // return operation results
        ActionResult result = null;
        if (response != null) {
            result = XMLDocumentUtilities.fromElement(response.getElement(MailConstants.E_ACTION),
                ActionResult.class);
        }
        return result;
    }

    /**
     * Performs a move action with given properties.
     *
     * @param rctxt The request context
     * @param ids Comma-separated item ids to act on
     * @param folderId The destination folder
     * @param constraints Target constraints
     * @return Action result
     * @throws ServiceException If there are issues executing the document
     */
    public ActionResult itemMove(RequestContext rctxt, String ids, String folderId,
        String constraint) throws ServiceException {
        final ActionSelector input = new ActionSelector(ids, MailConstants.OP_MOVE);
        input.setFolder(folderId);
        input.setConstraint(constraint);
        return itemAction(rctxt, input);
    }

    /**
     * Performs a copy action with given properties.
     *
     * @param rctxt The request context
     * @param ids Comma-separated item ids to act on
     * @param folderId The destination folder
     * @param constraints Target constraints
     * @return Action result
     * @throws ServiceException If there are issues executing the document
     */
    public ActionResult itemCopy(RequestContext rctxt, String ids, String folderId,
        String constraint) throws ServiceException {
        final ActionSelector input = new ActionSelector(ids, MailConstants.OP_COPY);
        input.setFolder(folderId);
        input.setConstraint(constraint);
        input.setNewlyCreatedIds(true);
        return itemAction(rctxt, input);
    }

    /**
     * Performs a delete action with given properties.
     *
     * @param rctxt The request context
     * @param ids Comma-separated item ids to act on
     * @param constraints Target constraints
     * @return Action result
     * @throws ServiceException If there are issues executing the document
     */
    public ActionResult itemDelete(RequestContext rctxt, String ids, String constraint)
        throws ServiceException {
        final ActionSelector input = new ActionSelector(ids, MailConstants.OP_HARD_DELETE);
        input.setConstraint(constraint);
        input.setNonExistentIds(true);
        return itemAction(rctxt, input);
    }

    /**
     * Performs a trash action with given properties.
     *
     * @param rctxt The request context
     * @param ids Comma-separated item ids to act on
     * @param constraints Target constraints
     * @return Action result
     * @throws ServiceException If there are issues executing the document
     */
    public ActionResult itemTrash(RequestContext rctxt, String ids, String constraint)
        throws ServiceException {
        final ActionSelector input = new ActionSelector(ids, MailConstants.OP_TRASH);
        input.setConstraint(constraint);
        return itemAction(rctxt, input);
    }

    /**
     * Performs a flag action with given properties.
     *
     * @param rctxt The request context
     * @param ids Comma-separated item ids to act on
     * @param flags The flags to add
     * @param constraints Target constraints
     * @return Action result
     * @throws ServiceException If there are issues executing the document
     */
    public ActionResult itemFlag(RequestContext rctxt, String ids, String flags,
        String constraint) throws ServiceException {
        final ActionSelector input = new ActionSelector(ids, MailConstants.OP_FLAG);
        input.setFlags(flags);
        input.setConstraint(constraint);
        return itemAction(rctxt, input);
    }

    /**
     * Performs an unflag action with given properties.
     *
     * @param rctxt The request context
     * @param ids Comma-separated item ids to act on
     * @param flags The flags to remove
     * @param constraints Target constraints
     * @return Action result
     * @throws ServiceException If there are issues executing the document
     */
    public ActionResult itemUnflag(RequestContext rctxt, String ids, String flags,
        String constraint) throws ServiceException {
        final ActionSelector input = new ActionSelector(ids, "!" + MailConstants.OP_FLAG);
        input.setFlags(flags);
        input.setConstraint(constraint);
        return itemAction(rctxt, input);
    }

    /**
     * Performs a tag action with given properties.
     *
     * @param rctxt The request context
     * @param ids Comma-separated item ids to act on
     * @param tagNames The tags to add
     * @param constraints Target constraints
     * @return Action result
     * @throws ServiceException If there are issues executing the document
     */
    public ActionResult itemTag(RequestContext rctxt, String ids, String tagNames,
        String constraint) throws ServiceException {
        final ActionSelector input = new ActionSelector(ids, MailConstants.OP_TAG);
        input.setTagNames(tagNames);
        input.setConstraint(constraint);
        return itemAction(rctxt, input);
    }

    /**
     * Performs an untag action with given properties.
     *
     * @param rctxt The request context
     * @param ids Comma-separated item ids to act on
     * @param tagNames The tags to remove
     * @param constraints Target constraints
     * @return Action result
     * @throws ServiceException If there are issues executing the document
     */
    public ActionResult itemUntag(RequestContext rctxt, String ids, String tagNames,
        String constraint) throws ServiceException {
        final ActionSelector input = new ActionSelector(ids, "!" + MailConstants.OP_TAG);
        input.setTagNames(tagNames);
        input.setConstraint(constraint);
        return itemAction(rctxt, input);
    }

}
