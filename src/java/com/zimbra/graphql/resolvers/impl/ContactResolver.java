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

import com.zimbra.common.gql.GqlConstants;
import com.zimbra.common.service.ServiceException;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.models.inputs.GQLGetContactsRequestInput;
import com.zimbra.graphql.repositories.impl.ZXMLContactRepository;
import com.zimbra.soap.mail.type.ActionResult;
import com.zimbra.soap.mail.type.ContactInfo;
import com.zimbra.soap.mail.type.ContactSpec;
import com.zimbra.soap.mail.type.FolderActionResult;
import com.zimbra.soap.mail.type.NewContactAttr;

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

    @GraphQLQuery(description="Retrieve contacts by given properties.")
    public List<ContactInfo> contacts(@GraphQLArgument(name=GqlConstants.INPUT) GQLGetContactsRequestInput input,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return contactRepository.contacts(context, input);
    }

    @GraphQLMutation(description="Create a contact with given properties.")
    public ContactInfo contactCreate(
        @GraphQLArgument(name=GqlConstants.INCLUDE_IMAP_UID, description="Denotes whether to return IMAP UID") Boolean includeImapUid,
        @GraphQLArgument(name=GqlConstants.DO_VERBOSE, description="If set, the returned info is just a placeholder containing the new contact ID") Boolean doVerbose,
        @GraphQLArgument(name=GqlConstants.INCLUDE_MODIFIED_SEQUENCE, description="Denotes whether to return the modified sequence") Boolean includeModifiedSequence,
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.CONTACT, description="The contact to create") ContactSpec contact,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return contactRepository.contactCreate(context, includeImapUid, doVerbose, includeModifiedSequence, contact);
    }

    @GraphQLMutation(description="Moves the given contacts to the specified folder.")
    public ActionResult contactMove(
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.IDS, description="Comma-separated item ids to act on") String ids,
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.FOLDER_ID, description="The destination folder") String folderId,
        @GraphQLArgument(name=GqlConstants.CONSTRAINT, description="Target constraints") String constraint,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return contactRepository.itemMove(context, ids, folderId, constraint);
    }

    @GraphQLMutation(description="Copies the given contacts to the specified folder.")
    public ActionResult contactCopy(
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.IDS, description="Comma-separated item ids to act on") String ids,
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.FOLDER_ID, description="The destination folder") String folderId,
        @GraphQLArgument(name=GqlConstants.CONSTRAINT, description="Target constraints") String constraint,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return contactRepository.itemCopy(context, ids, folderId, constraint);
    }

    @GraphQLMutation(description="Deletes the given contacts.")
    public ActionResult contactDelete(
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.IDS, description="Comma-separated item ids to act on") String ids,
        @GraphQLArgument(name=GqlConstants.CONSTRAINT, description="Target constraints") String constraint,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return contactRepository.itemDelete(context, ids, constraint);
    }

    @GraphQLMutation(description="Adds the specified flags on the given contacts.")
    public ActionResult contactFlag(
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.IDS, description="Comma-separated item ids to act on") String ids,
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.FLAGS, description="The flags to add") String flags,
        @GraphQLArgument(name=GqlConstants.CONSTRAINT, description="Target constraints") String constraint,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return contactRepository.itemFlag(context, ids, flags, constraint);
    }

    @GraphQLMutation(description="Removes the specified flags from the given contacts.")
    public ActionResult contactUnflag(
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.IDS, description="Comma-separated item ids to act on") String ids,
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.FLAGS, description="The flags to remove") String flags,
        @GraphQLArgument(name=GqlConstants.CONSTRAINT, description="Target constraints") String constraint,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return contactRepository.itemUnflag(context, ids, flags, constraint);
    }

    @GraphQLMutation(description="Trashes the given contacts.")
    public ActionResult contactTrash(
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.IDS, description="Comma-separated item ids to act on") String ids,
        @GraphQLArgument(name=GqlConstants.CONSTRAINT, description="Target constraints") String constraint,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return contactRepository.itemTrash(context, ids, constraint);
    }

    @GraphQLMutation(description="Adds the specified tags to the given contacts.")
    public ActionResult contactTag(
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.IDS, description="Comma-separated item ids to act on") String ids,
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.TAG_NAMES, description="The tags to add") String tagNames,
        @GraphQLArgument(name=GqlConstants.CONSTRAINT, description="Target constraints") String constraint,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return contactRepository.itemTag(context, ids, tagNames, constraint);
    }

    @GraphQLMutation(description="Removes the specified tags from the given contacts.")
    public ActionResult contactUntag(
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.IDS, description="Comma-separated item ids to act on") String ids,
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.TAG_NAMES, description="The tags to remove") String tagNames,
        @GraphQLArgument(name=GqlConstants.CONSTRAINT, description="Target constraints") String constraint,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return contactRepository.itemUntag(context, ids, tagNames, constraint);
    }

    @GraphQLMutation(description="Updates a given contact with the specified properties.")
    public FolderActionResult contactUpdate(
        @GraphQLNonNull @GraphQLArgument(name=GqlConstants.IDS, description="Comma-separated item ids to act on") String ids,
        @GraphQLArgument(name=GqlConstants.FOLDER_ID, description="The destination folder") String folderId,
        @GraphQLArgument(name=GqlConstants.FLAGS, description="The flags to set") String flags,
        @GraphQLArgument(name=GqlConstants.TAG_NAMES, description="The tags to set") String tagNames,
        @GraphQLArgument(name=GqlConstants.RGB, description="RGB color in format #rrggbb where r,g and b are hex digits") String rgb,
        @GraphQLArgument(name=GqlConstants.COLOR, description="color numeric; range 0-127; defaults to 0 if not present; client can display only 0-7") Byte color,
        @GraphQLArgument(name=GqlConstants.ATTRIBUTES, description="The attributes to set") List<NewContactAttr> attributes,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return contactRepository.contactUpdate(context, ids, folderId, flags, tagNames, rgb, color, attributes);
    }
}