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

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.Element;
import com.zimbra.common.soap.MailConstants;
import com.zimbra.cs.service.mail.ContactAction;
import com.zimbra.cs.service.mail.CreateContact;
import com.zimbra.cs.service.mail.GetContacts;
import com.zimbra.cs.service.mail.ItemAction;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.models.inputs.GQLGetContactsRequestInput;
import com.zimbra.graphql.repositories.IRepository;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.XMLDocumentUtilities;
import com.zimbra.soap.ZimbraSoapContext;
import com.zimbra.soap.mail.message.ContactActionRequest;
import com.zimbra.soap.mail.message.CreateContactRequest;
import com.zimbra.soap.mail.message.GetContactsRequest;
import com.zimbra.soap.mail.message.GetContactsResponse;
import com.zimbra.soap.mail.type.ContactActionSelector;
import com.zimbra.soap.mail.type.ContactInfo;
import com.zimbra.soap.mail.type.ContactSpec;
import com.zimbra.soap.mail.type.FolderActionResult;

/**
 * The ZXMLContactRepository class.<br>
 * Contains XML document based data access methods for contacts.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.repositories.impl
 * @copyright Copyright © 2018
 */
public class ZXMLContactRepository extends ZXMLItemRepository implements IRepository {

    /**
     * The createContact document handler.
     */
    protected final CreateContact createContactHandler;

    /**
     * The getContact document handler.
     */
    protected final GetContacts getContactHandler;

    /**
     * Creates an instance with default document handlers.
     */
    public ZXMLContactRepository() {
        this(new ContactAction(), new CreateContact(), new GetContacts());
    }

    /**
     * Creates an instance with specified handlers.
     *
     * @param actionHandler The item action handler
     * @param createHandler The create contact handler
     * @param getHandler The get contacts handler
     */
    public ZXMLContactRepository(ItemAction actionHandler, CreateContact createHandler,
        GetContacts getHandler) {
        super(actionHandler);
        this.createContactHandler = createHandler;
        this.getContactHandler = getHandler;
    }

    /**
     * Retrieves a contact by given properties.
     *
     * @param rctxt The request context
     * @param input The contact fetch input
     * @return Fetch contact results
     * @throws ServiceException If there are issues executing the document
     */
    public List<ContactInfo> contacts(RequestContext rctxt, GQLGetContactsRequestInput input)
        throws ServiceException {
        // get auth context
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        // map the request params
        final GetContactsRequest req = new GetContactsRequest();
        if (input != null) {
            req.setFolderId(input.getFolderId());
            req.setSortBy(input.getSortBy());
            req.setSync(input.getDoSync());
            req.setDerefGroupMember(input.getDoDerefGroupMember());
            req.setIncludeMemberOf(input.getDoGetMemberOf());
            req.setReturnHiddenAttrs(input.getDoGetHiddenAttrs());
            req.setReturnCertInfo(input.getDoGetCertInfo());
            req.setWantImapUid(input.getDoGetImapUid());
            req.setMaxMembers(input.getMaxMembers());
            req.setAttributes(input.getAttributes());
            req.setMemberAttributes(input.getMemberAttributes());
            req.setContacts(input.getContacts());
        }
        // execute
        final Element response = XMLDocumentUtilities.executeDocument(
            getContactHandler,
            zsc,
            XMLDocumentUtilities.toElement(req));
        if (response != null) {
            final GetContactsResponse contactsResponse = XMLDocumentUtilities.fromElement(
                response,
                GetContactsResponse.class);
            if (contactsResponse != null) {
                return contactsResponse.getContacts();
            }
        }
        return Collections.emptyList();
    }

    /**
     * Create a contact with given properties.
     *
     * @param rctxt The request context
     * @param doGetImapUid Denotes whether to return IMAP UID
     * @param doVerbose If set, the returned <cn> is just a placeholder containing the new contact ID
     * @param doGetModifiedSequence Denotes whether to return the modified sequence
     * @param contactToCreate The contact to create
     * @return The newly created contact
     * @throws ServiceException If there are issues executing the document
     */
    public ContactInfo contactCreate(RequestContext rctxt, Boolean doGetImapUid, Boolean doVerbose,
        Boolean doGetModifiedSequence, ContactSpec contactToCreate) throws ServiceException {
        // get auth context
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        // execute
        final CreateContactRequest req = new CreateContactRequest(contactToCreate);
        req.setVerbose(doVerbose);
        req.setWantImapUid(doGetImapUid);
        req.setWantModifiedSequence(doGetModifiedSequence);
        final Element response = XMLDocumentUtilities.executeDocument(
            createContactHandler,
            zsc,
            XMLDocumentUtilities.toElement(req));
        ContactInfo zCreatedContact = null;
        if (response != null) {
            zCreatedContact = XMLDocumentUtilities
                .fromElement(response.getElement(MailConstants.E_CONTACT), ContactInfo.class);
        }
        return zCreatedContact;
    }

    /**
     * Performs a contact action with given properties.
     *
     * @param rctxt The request context
     * @param input The properties
     * @return Action result (contact action DOES return a FolderActionResult)
     * @throws ServiceException If there are issues executing the document
     */
    public FolderActionResult contactAction(RequestContext rctxt, ContactActionSelector input)
        throws ServiceException {
        final ContactActionRequest req = new ContactActionRequest(input);
        final Element response = super.action(rctxt, req);
        FolderActionResult result = null;
        if (response != null) {
            result = XMLDocumentUtilities.fromElement(response.getElement(MailConstants.E_ACTION),
                FolderActionResult.class);
        }
        return result;
    }

}