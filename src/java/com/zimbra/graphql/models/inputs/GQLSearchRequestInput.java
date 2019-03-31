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
package com.zimbra.graphql.models.inputs;

import java.util.List;

import com.zimbra.common.gql.GqlConstants;
import com.zimbra.soap.mail.type.CalTZInfo;
import com.zimbra.soap.type.AttributeName;
import com.zimbra.soap.type.MsgContent;
import com.zimbra.soap.type.WantRecipsSetting;
import com.zimbra.soap.type.ZmBoolean;

import io.leangen.graphql.annotations.GraphQLIgnore;
import io.leangen.graphql.annotations.GraphQLInputField;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.types.GraphQLType;

/**
 * The GQLSearchRequestInput class.<br>
 * Contains search request properties.
 * @see com.zimbra.soap.account.message.SearchRequest
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models.inputs
 * @copyright Copyright © 2018
 */
@GraphQLType(name=GqlConstants.CLASS_SEARCH_REQUEST, description="Input for search request.")
public class GQLSearchRequestInput extends GQLBasicSearchRequestInput {

    protected Boolean includeTagDeleted;
    protected Boolean includeTagMuted;
    protected String allowableTaskStatus;
    protected Long calItemExpandStart;
    protected Long calItemExpandEnd;
    protected String query;
    protected Boolean inDumpster;
    @GraphQLIgnore
    protected String searchTypes;
    protected String groupBy;
    protected String fetch;
    protected Boolean markRead;
    protected Integer maxInlinedLength;
    protected Boolean includeHtml;
    protected Boolean includeIsExpandable;
    protected Boolean neuterImages;
    protected WantRecipsSetting includeRecipients;
    protected Boolean prefetch;
    protected String resultMode;
    protected Boolean fullConversation;
    protected String field;
    protected CalTZInfo calTz;
    protected MsgContent msgContent;
    protected Boolean includeMemberOf;
    protected List<AttributeName> headers;

    public Boolean getIncludeTagDeleted() {
        return includeTagDeleted;
    }

    @GraphQLInputField(name = GqlConstants.INCLUDE_TAG_DELETED, description = "Include items with the `deleted` tag set in search results")
    public void setIncludeTagDeleted(Boolean includeTagDeleted) {
        this.includeTagDeleted = includeTagDeleted;
    }

    public Boolean getIncludeTagMuted() {
        return includeTagMuted;
    }

    @GraphQLInputField(name = GqlConstants.INCLUDE_TAG_MUTED, description = "Include items with the `muted` tag set in search results")
    public void setIncludeTagMuted(Boolean includeTagMuted) {
        this.includeTagMuted = includeTagMuted;
    }

    public String getAllowableTaskStatus() {
        return allowableTaskStatus;
    }

    @GraphQLInputField(name = GqlConstants.ALLOWABLE_TASK_STATUS, description = "Comma separated list of allowable Task statuses. Valid values : `NEED`, `INPR`, `WAITING`, `DEFERRED`, `COMP`")
    public void setAllowableTaskStatus(String allowableTaskStatus) {
        this.allowableTaskStatus = allowableTaskStatus;
    }

    public Long getCalItemExpandStart() {
        return calItemExpandStart;
    }

    @GraphQLInputField(name = GqlConstants.CALENDAR_ITEM_EXPANDED_START, description="Start time in milliseconds for the range to `includeMemberOf` instances for calendar items. If `calExpandInstStart` and `calExpandInstEnd` are specified, and the search types `includeMemberOf` calendar item types (e.g. appointment), then the search results `includeMemberOf` the instances for calendar items within that range in the form described in the description of the response. ***IMPORTANT NOTE: Calendar Items that have no instances within that range are COMPLETELY EXCLUDED from the results. Calendar Items with no data (such as Tasks with no date specified) are included, but with no instance information***")
    public void setCalItemExpandStart(Long calItemExpandStart) {
        this.calItemExpandStart = calItemExpandStart;
    }

    public Long getCalItemExpandEnd() {
        return calItemExpandEnd;
    }

    @GraphQLInputField(name = GqlConstants.CALENDAR_ITEM_EXPANDED_END, description="End time in milliseconds for the range to `includeMemberOf` instances for calendar items from.")
    public void setCalItemExpandEnd(Long calItemExpandEnd) {
        this.calItemExpandEnd = calItemExpandEnd;
    }

    public String getQuery() {
        return query;
    }

    @GraphQLInputField(name = GqlConstants.QUERY, description="Query string to use for the search")
    public void setQuery(@GraphQLNonNull String query) {
        this.query = query;
    }

    public Boolean getInDumpster() {
        return inDumpster;
    }

    @GraphQLInputField(name = GqlConstants.IN_DUMPSTER, description="Search dumpster data instead of live data.")
    public void setInDumpster(Boolean inDumpster) {
        this.inDumpster = inDumpster;
    }

    @GraphQLIgnore
    public String getSearchTypes() {
        return searchTypes;
    }

    @GraphQLIgnore
    public void setSearchTypes(String searchTypes) {
        this.searchTypes = searchTypes;
    }

    public String getGroupBy() {
        return groupBy;
    }

    @Deprecated
    @GraphQLInputField(name = GqlConstants.GROUP_BY, description="Deprecated.  Use `searchTypes` instead")
    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public String getFetch() {
        return fetch;
    }

    @GraphQLInputField(name = GqlConstants.FETCH, description="Select setting for hit expansion. `1` or `first`: first hit expanded inline (messages only at present); `{item-id}`: only the message with the given {item-id} is expanded inline; `{item-id-1,item-id-2,...,item-id-n}`: messages with ids in the comma-separated list will be expanded; `all`: all messages are expanded inline; `!`: only the first message in the conversation will be expanded, whether it's a hit or not; `u` or `unread`: all unread hits are expanded; `u1` or `unread-first`: any unread hits are expanded, otherwise the first hit is expanded.; `u1!`: any unread hits are expanded, otherwise the first hit and the first message are expanded (those may be the same); `hits`: all hits are expanded; `hits!`: all hits are expanded if there are any, otherwise the first message is expanded; wantHtml: true, is also specified, inlined hits will return HTML parts if available; if markRead: true, is also specified, inlined hits will be marked as read; if neuterImages: false, is also specified, images in inlined HTML parts will not be \"neutered\";  if `header`s are requested, any matching headers are included in inlined message hits; if max=\"{max-inlined-length}\" is specified, inlined body content in limited to the given length; if the part is truncated, truncated=\"1\" is specified on the `mp` in question")
    public void setFetch(String fetch) {
        this.fetch = fetch;
    }

    public Boolean getMarkRead() {
        return markRead;
    }

    @GraphQLInputField(name = GqlConstants.MARK_READ, description="Inlined hits will be marked as read")
    public void setMarkRead(Boolean markRead) {
        this.markRead = markRead;
    }

    public Integer getMaxInlinedLength() {
        return maxInlinedLength;
    }

    @GraphQLInputField(name = GqlConstants.MAX_INLINED_LENGTH, description="Limits inlined body content. If specified, inlined body content is limited to the given length; if the part is truncated, truncated=\"1\" is specified on the `messagePartHits` in question")
    public void setMaxInlinedLength(Integer maxInlinedLength) {
        this.maxInlinedLength = maxInlinedLength;
    }

    public Boolean getIncludeHtml() {
        return includeHtml;
    }

    @GraphQLInputField(name = GqlConstants.INCLUDE_HTML, description="Inlined hits to return HTML parts if available")
    public void setIncludeHtml(Boolean includeHtml) {
        this.includeHtml = includeHtml;
    }

    public Boolean getIncludeIsExpandable() {
        return includeIsExpandable;
    }

    @GraphQLInputField(name = GqlConstants.INCLUDE_IS_EXPANDABLE, description="Denotes whether two additional flags may be included in `emails` results elements for messages returned inline. (1) `isGroup`: set if the email address is a group. (2) `exp`: present only when `isGroup` set to true. Unset if the authenticated user does not have permission to expand group members")
    public void setIncludeIsExpandable(Boolean includeIsExpandable) {
        this.includeIsExpandable = includeIsExpandable;
    }

    public Boolean getNeuterImages() {
        return neuterImages;
    }

    @GraphQLInputField(name = GqlConstants.NEUTER_IMAGES, description="Set to false to stop images in inlined HTML parts from being \"neutered\"")
    public void setNeuterImages(Boolean neuterImages) {
        this.neuterImages = neuterImages;
    }

    public WantRecipsSetting getIncludeRecipients() {
        return includeRecipients;
    }

    @GraphQLInputField(name = GqlConstants.INCLUDE_RECEPIENTS, description="If true, sent messages that contain the \"To:\" recipients instead of the sender. Returned conversations whose first hit was sent by the user will contain that hit's \"To:\" recipients instead of the conversation's sender list")
    public void setIncludeRecipients(WantRecipsSetting includeRecipients) {
        this.includeRecipients = includeRecipients;
    }

    public Boolean getPrefetch() {
        return prefetch;
    }

    @GraphQLInputField(name = GqlConstants.PREFETCH, description="Prefetch")
    public void setPrefetch(Boolean prefetch) {
        this.prefetch = prefetch;
    }

    public String getResultMode() {
        return resultMode;
    }

    @GraphQLInputField(name = GqlConstants.RESULT_MODE, description="Specifies the type of result. NORMAL [default]: Everything, IDS: Only IDs")
    public void setResultMode(String resultMode) {
        this.resultMode = resultMode;
    }

    public ZmBoolean getFullConversation() {
        return ZmBoolean.fromBool(fullConversation);
    }

    @GraphQLInputField(name = GqlConstants.FULL_CONVERSATION, description="Full conversations")
    public void setFullConversation(Boolean fullConversation) {
        this.fullConversation = fullConversation;
    }

    public String getField() {
        return field;
    }

    @GraphQLInputField(name = GqlConstants.FIELD, description="By default, searches the CONTENT field. 'content:' [the default] or 'subject:', etc.")
    public void setField(String field) {
        this.field = field;
    }

    public CalTZInfo getCalTz() {
        return calTz;
    }

    @GraphQLInputField(name = GqlConstants.CALENDAR_TIME_ZONE, description="Timezone specification")
    public void setCalTz(CalTZInfo calTz) {
        this.calTz = calTz;
    }

    public MsgContent getMsgContent() {
        return msgContent;
    }

    @GraphQLInputField(name = GqlConstants.MSG_CONTENT, description="Message Content the client expects in response. full: The complete message; original: Only the Message and not quoted text; both: The complete message and also this message without quoted text ")
    public void setMsgContent(MsgContent msgContent) {
        this.msgContent = msgContent;
    }

    public Boolean getIncludeMemberOf() {
        return includeMemberOf;
    }

    @GraphQLInputField(name = GqlConstants.INCLUDE_MEMBER_OF, description="Include the list of contact groups this contact is a member of. Performance penalty associated with computing this information.")
    public void setIncludeMemberOf(Boolean includeMemberOf) {
        this.includeMemberOf = includeMemberOf;
    }

    public List<AttributeName> getHeaders() {
        return headers;
    }

    @GraphQLInputField(name = GqlConstants.HEADERS, description="List of requested header's to be included in inlined message hits")
    public void setHeaders(List<AttributeName> headers) {
        this.headers = headers;
    }

}
