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

import java.util.List;

import com.zimbra.common.service.ServiceException;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.models.inputs.GQLGetContactsRequestInput;
import com.zimbra.graphql.repositories.impl.ZXMLContactRepository;
import com.zimbra.soap.mail.type.ContactActionSelector;
import com.zimbra.soap.mail.type.ContactInfo;
import com.zimbra.soap.mail.type.ContactSpec;
import com.zimbra.soap.mail.type.FolderActionResult;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLRootContext;

/**
 * The ContactResolver class.<br>
 * Contains contact schema resources.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.resolvers.impl
 * @copyright Copyright © 2018
 */
public class ContactResolver {

    protected ZXMLContactRepository contactRepository = null;

    /**
     * Creates an instance with specified contact repository.
     *
     * @param contactRepository The contact repository
     */
    public ContactResolver(ZXMLContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GraphQLQuery(description = "Retrieve contacts by given properties.")
    public List<ContactInfo> contacts(@GraphQLArgument(name="input") GQLGetContactsRequestInput input,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return contactRepository.contacts(context, input);
    }

    @GraphQLMutation(description = "Create a contact with given properties.")
    public ContactInfo contactCreate(
        @GraphQLArgument(name="doGetImapUid", description="Denotes whether to return IMAP UID") Boolean doGetImapUid,
        @GraphQLArgument(name="doVerbose", description="If set, the returned <cn> is just a placeholder containing the new contact ID") Boolean doVerbose,
        @GraphQLArgument(name="doGetModifiedSequence", description="Denotes whether to return the modified sequence") Boolean doGetModifiedSequence,
        @GraphQLArgument(name="contact", description="The contact to create") ContactSpec contact,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return contactRepository.contactCreate(context, doGetImapUid, doVerbose, doGetModifiedSequence, contact);
    }

    @GraphQLMutation(description = "Handles a contact action request.")
    public FolderActionResult contactAction(
        @GraphQLNonNull @GraphQLArgument(name="input") ContactActionSelector input,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return contactRepository.contactAction(context, input);
    }
}