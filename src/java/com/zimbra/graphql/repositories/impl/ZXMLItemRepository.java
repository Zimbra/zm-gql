package com.zimbra.graphql.repositories.impl;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.Element;
import com.zimbra.cs.account.Account;
import com.zimbra.cs.mailbox.OperationContext;
import com.zimbra.cs.service.mail.ItemAction;
import com.zimbra.graphql.repositories.IRepository;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.XMLDocumentUtilities;

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