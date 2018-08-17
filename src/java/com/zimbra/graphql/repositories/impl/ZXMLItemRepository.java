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
import com.zimbra.cs.account.Account;
import com.zimbra.cs.mailbox.OperationContext;
import com.zimbra.cs.service.mail.ItemAction;
import com.zimbra.graphql.repositories.IRepository;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.XMLDocumentUtilities;

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
     * @param octxt The operation context
     * @param account The account to perform the operation
     * @param input The properties
     * @return Action result
     * @throws ServiceException If there are issues executing the document
     */
    public Element action(OperationContext octxt, Account account, Object req) throws ServiceException {
        final Element response = XMLDocumentUtilities.executeDocument(
            actionHandler,
            XMLDocumentUtilities.toElement(req),
            GQLAuthUtilities.getZimbraSoapContext(octxt, account));
        return response;
    }

}
