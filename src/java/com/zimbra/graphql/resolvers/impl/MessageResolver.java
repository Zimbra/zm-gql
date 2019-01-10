/*
 * ***** BEGIN LICENSE BLOCK *****
 * Zimbra GraphQL Extension
 * Copyright (C) 2018, 2019 Synacor, Inc.
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
import com.zimbra.graphql.repositories.impl.ZXMLMessageRepository;
import com.zimbra.soap.mail.message.SendShareNotificationRequest.Action;
import com.zimbra.soap.mail.type.EmailAddrInfo;
import com.zimbra.soap.mail.type.Msg;
import com.zimbra.soap.mail.type.MsgSpec;
import com.zimbra.soap.mail.type.MsgToSend;
import com.zimbra.soap.mail.type.MsgWithGroupInfo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLRootContext;

/**
 * The MessageResolver class.<br>
 * Contains message schema resources.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.resolvers.impl
 */
public class MessageResolver {

    protected ZXMLMessageRepository messageRepository = null;

    /**
     * Creates an instance with specified message repository.
     *
     * @param messageRepository The message repository
     */
    public MessageResolver(ZXMLMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GraphQLQuery(description="Retrieve a message by given properties.")
    public Msg message(
        @GraphQLArgument(name=GqlConstants.GQL_MESSAGE_SPECIFICATIONS) MsgSpec messageSpecifications,
        @GraphQLRootContext RequestContext context) throws ServiceException {
       return messageRepository.message(context, messageSpecifications);
    }

    @GraphQLMutation(description = "Send a message with given input.")
    public MsgWithGroupInfo messageSend(
        @GraphQLArgument(name = GqlConstants.ADD_SENT_BY, description = "Denotes whether to add sent-by parameter when sending on behalf of someone.") Boolean addSentBy,
        @GraphQLArgument(name = GqlConstants.DO_SKIP_SAVED, description = "Denotes whether to skip saving a copy (regardless of account/identity settings).") Boolean doSkipSave,
        @GraphQLArgument(name = GqlConstants.DO_FETCH_SAVED, description = "Denotes whether to return a copy of sent message, if it was saved.") Boolean doFetchSaved,
        @GraphQLArgument(name = GqlConstants.SEND_UID, description = "Send UID.") String sendUid,
        @GraphQLArgument(name = GqlConstants.MESSAGE, description = "The message to send.") MsgToSend message,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        // TODO: False must be changed to Boolean isCalendarForward when isCalendarForward and invites are enabled 
        return messageRepository.messageSend(context, addSentBy, false, doSkipSave,
            doFetchSaved, sendUid, message);
    }

    @GraphQLMutation(description = "Send share notification with given input")
    public Boolean shareNotificationSend(
            @GraphQLNonNull @GraphQLArgument(name = GqlConstants.ITEM_ID, description = "shared item id.") String itemId,
            @GraphQLNonNull @GraphQLArgument(name = GqlConstants.EMAIL_ADDRESSES, description = "email addresses to which share notification has to be sent") List<EmailAddrInfo> emailAddresses,
            @GraphQLArgument(name = GqlConstants.NOTES, description = "notes") String notes,
            @GraphQLArgument(name = GqlConstants.ACTION, description = "sharing action") Action action,
            @GraphQLRootContext RequestContext context) throws ServiceException {
        return messageRepository.shareNotificationSend(context, itemId, emailAddresses, notes, action);
    }
}
