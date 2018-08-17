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

import com.zimbra.soap.mail.type.CalTZInfo;
import com.zimbra.soap.type.AttributeName;
import com.zimbra.soap.type.CursorInfo;
import com.zimbra.soap.type.MsgContent;
import com.zimbra.soap.type.WantRecipsSetting;
import com.zimbra.soap.type.ZmBoolean;

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
@GraphQLType(name="SearchRequest", description="Input for search request.")
public class GQLSearchRequestInput {

    protected Boolean includeTagDeleted;
    protected Boolean includeTagMuted;
    protected String allowableTaskStatus;
    protected Long calItemExpandStart;
    protected Long calItemExpandEnd;
    protected String query;
    protected Boolean inDumpster;
    protected String searchTypes;
    protected String groupBy;
    protected Boolean quick;
    protected String sortBy;
    protected String fetch;
    protected Boolean markRead;
    protected Integer maxInlinedLength;
    protected Boolean wantHtml;
    protected Boolean needCanExpand;
    protected Boolean neuterImages;
    protected WantRecipsSetting wantRecipients;
    protected Boolean prefetch;
    protected String resultMode;
    protected Boolean fullConversation;
    protected String field;
    protected Integer limit;
    protected Integer offset;
    protected CalTZInfo calTz;
    protected String locale;
    protected CursorInfo cursor;
    protected MsgContent msgContent;
    protected Boolean includeMemberOf;
    protected List<AttributeName> headers;

    /**
     * Gets the value of includeTagDeleted.
     *
     * @return The value of includeTagDeleted
     */
    public Boolean getIncludeTagDeleted() {
        return includeTagDeleted;
    }

    /**
     * Include items with the `deleted` tag set in search results
     *
     * @param includeTagDeleted The boolean to set includeTagDeleted to
     */
    @GraphQLInputField(name = "includeTagDeleted", description = "Include items with the `deleted` tag set in search results")
    public void setIncludeTagDeleted(Boolean includeTagDeleted) {
        this.includeTagDeleted = includeTagDeleted;
    }

    /**
     * Gets the value of includeTagMuted.
     *
     * @return the includeTagMuted value
     */
    public Boolean getIncludeTagMuted() {
        return includeTagMuted;
    }

    /**
     * Include items with the `muted` tag set in search results.
     *
     * @param includeTagMuted the boolean to set
     */
    @GraphQLInputField(name = "includeTagMuted", description = "Include items with the `muted` tag set in search results")
    public void setIncludeTagMuted(Boolean includeTagMuted) {
        this.includeTagMuted = includeTagMuted;
    }

    /**
     * Gets the value of allowableTaskStatus.
     *
     * @return the allowableTaskStatus value setting
     */
    public String getAllowableTaskStatus() {
        return allowableTaskStatus;
    }

    /**
     * Comma separated list of allowable Task statuses. Valid values : `NEED`, `INPR`, `WAITING`, `DEFERRED`, `COMP`.
     *
     * @param allowableTaskStatus The comma separated values for allowableTaskStatus to set
     */
    @GraphQLInputField(name = "allowableTaskStatus", description = "Comma separated list of allowable Task statuses. Valid values : `NEED`, `INPR`, `WAITING`, `DEFERRED`, `COMP`")
    public void setAllowableTaskStatus(String allowableTaskStatus) {
        this.allowableTaskStatus = allowableTaskStatus;
    }

    /**
     * Gets the value of calItemExpandStart.
     *
     * @return the calItemExpandStart value
     */
    public Long getCalItemExpandStart() {
        return calItemExpandStart;
    }

    /**
     * Start time in milliseconds for the range to `includeMemberOf` instances for calendar items.
     *
     * @param calItemExpandStart the calItemExpandStart value to set
     */
    @GraphQLInputField(name = "calItemExpandStart", description="Start time in milliseconds for the range to `includeMemberOf` instances for calendar items. If `calExpandInstStart` and `calExpandInstEnd` are specified, and the search types `includeMemberOf` calendar item types (e.g. appointment), then the search results `includeMemberOf` the instances for calendar items within that range in the form described in the description of the response. ***IMPORTANT NOTE: Calendar Items that have no instances within that range are COMPLETELY EXCLUDED from the results. Calendar Items with no data (such as Tasks with no date specified) are included, but with no instance information***")
    public void setCalItemExpandStart(Long calItemExpandStart) {
        this.calItemExpandStart = calItemExpandStart;
    }

    /**
     * Gets the value of calItemExpandEnd.
     *
     * @return the calItemExpandEnd value
     */
    public Long getCalItemExpandEnd() {
        return calItemExpandEnd;
    }

    /**
     * Time in milliseconds to end the span of time started by `calItemExpandStart` for calendar items from.
     *
     * @param calItemExpandEnd The calItemExpandEnd value to set
     */
    @GraphQLInputField(name = "calItemExpandEnd", description="End time in milliseconds for the range to `includeMemberOf` instances for calendar items from.")
    public void setCalItemExpandEnd(Long calItemExpandEnd) {
        this.calItemExpandEnd = calItemExpandEnd;
    }

    /**
     * Gets the value of query.
     *
     * @return The query value
     */
    @GraphQLNonNull
    public String getQuery() {
        return query;
    }

    /**
     * Query string to use for the search.
     *
     * @param query The query string to use
     */
    @GraphQLInputField(name = "query", description="Query string to use for the search")
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * Gets the value of inDumpster.
     *
     * @return The inDumpster value
     */
    public Boolean getInDumpster() {
        return inDumpster;
    }

    /**
     * Searches in the dumpster data instead of live data.
     *
     * @param inDumpster The inDumpster value to set
     */
    @GraphQLInputField(name = "inDumpster", description="Search dumpster data instead of live data.")
    public void setInDumpster(Boolean inDumpster) {
        this.inDumpster = inDumpster;
    }

    /**
     * Gets the value of searchTypes.
     *
     * @return The searchTypes value
     */
    public String getSearchTypes() {
        return searchTypes;
    }

    /**
     * Set a comma separated list of search types.<br>
     * Legal values are: conversation, message, contact, appointment, task, wiki, document.
     *
     * @param searchTypes The searchTypes string to set
     */
    @GraphQLInputField(name = "searchTypes", description="Comma separated list of search types. Legal values are: conversation, message, contact, appointment, task, wiki, document")
    public void setSearchTypes(String searchTypes) {
        this.searchTypes = searchTypes;
    }

    /**
     * Gets the value of groupBy.
     *
     * @return The groupBy value
     */
    public String getGroupBy() {
        return groupBy;
    }

    /**
     * Deprecated.  Use `searchTypes` instead.
     *
     * @param groupBy the groupBy to set
     */
    @Deprecated
    @GraphQLInputField(name = "groupBy", description="Deprecated.  Use `searchTypes` instead")
    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    /**
     * Gets the value of quick.
     *
     * @return the quick
     */
    public Boolean getQuick() {
        return quick;
    }

    /**
     * Setting for searches to bypass indexing before search.
     *
     * @param quick The quick boolean to set
     */
    @GraphQLInputField(name = "quick", description="For performance reasons, skip indexing before search which lowers latencies. i.e. recent messages may not be included in the search results.")
    public void setQuick(Boolean quick) {
        this.quick = quick;
    }

    /**
     * Gets the value of sortBy.
     *
     * @return the sortBy
     */
    public String getSortBy() {
        return sortBy;
    }

    /**
     * Sets sort by value.<br>
     * Possible values: `none, dateAsc, dateDesc, subjAsc, subjDesc, nameAsc, nameDesc,
     * rcptAsc, rcptDesc, attachAsc, attachDesc, flagAsc, flagDesc, priorityAsc, priorityDesc, idAsc, idDesc,
     * readAsc, readDesc`.
     *
     * @param sortBy The sortBy to set
     */
    @GraphQLInputField(name = "sortBy", description="SortBy setting. Possible values: `none, dateAsc, dateDesc, subjAsc, subjDesc, nameAsc, nameDesc, rcptAsc, rcptDesc, attachAsc, attachDesc, flagAsc, flagDesc, priorityAsc, priorityDesc, idAsc, idDesc, readAsc, readDesc`. If `sortBy` is \"none\" then cursors MUST NOT be used, and some searches are impossible (searches that require intersection of complex sub-ops). Server will throw an IllegalArgumentException if the search is invalid. ADDITIONAL SORT MODES FOR TASKS: valid only if types=\"task\" (and task alone): `taskDueAsc, taskDueDesc, taskStatusAsc, taskStatusDesc, taskPercCompletedAsc, taskPercCompletedDesc`")
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    /**
     * Gets the value of fetch.
     *
     * @return The fetch value
     */
    public String getFetch() {
        return fetch;
    }

    /**
     * Setting for hit expansion.
     *
     * @param fetch The fetch value to set
     */
    @GraphQLInputField(name = "fetch", description="Select setting for hit expansion. `1` or `first`: first hit expanded inline (messages only at present); `{item-id}`: only the message with the given {item-id} is expanded inline; `{item-id-1,item-id-2,...,item-id-n}`: messages with ids in the comma-separated list will be expanded; `all`: all messages are expanded inline; `!`: only the first message in the conversation will be expanded, whether it's a hit or not; `u` or `unread`: all unread hits are expanded; `u1` or `unread-first`: any unread hits are expanded, otherwise the first hit is expanded.; `u1!`: any unread hits are expanded, otherwise the first hit and the first message are expanded (those may be the same); `hits`: all hits are expanded; `hits!`: all hits are expanded if there are any, otherwise the first message is expanded; wantHtml: true, is also specified, inlined hits will return HTML parts if available; if markRead: true, is also specified, inlined hits will be marked as read; if neuterImages: false, is also specified, images in inlined HTML parts will not be \"neutered\";  if `header`s are requested, any matching headers are included in inlined message hits; if max=\"{max-inlined-length}\" is specified, inlined body content in limited to the given length; if the part is truncated, truncated=\"1\" is specified on the `mp` in question")
    public void setFetch(String fetch) {
        this.fetch = fetch;
    }

    /**
     * Gets the value of markRead.
     *
     * @return The markRead value
     */
    public Boolean getMarkRead() {
        return markRead;
    }

    /**
     * Sets inlined hits which will be marked as read if true.
     *
     * @param markRead The markRead to set
     */
    @GraphQLInputField(name = "markRead", description="Inlined hits will be marked as read")
    public void setMarkRead(Boolean markRead) {
        this.markRead = markRead;
    }

    /**
     * Gets the value of maxInlinedLength.
     *
     * @return the maxInlinedLength
     */
    public Integer getMaxInlinedLength() {
        return maxInlinedLength;
    }

    /**
     * Limits inlined body content.
     *
     * @param maxInlinedLength The maxInlinedLength to set
     */
    @GraphQLInputField(name = "maxInlinedLength", description="Limits inlined body content. If specified, inlined body content is limited to the given length; if the part is truncated, truncated=\"1\" is specified on the `messagePartHits` in question")
    public void setMaxInlinedLength(Integer maxInlinedLength) {
        this.maxInlinedLength = maxInlinedLength;
    }

    /**
     * Gets the value of wantHtml.
     *
     * @return The wantHtml value
     */
    public Boolean getWantHtml() {
        return wantHtml;
    }

    /**
     * Setting to return HTML parts if available, from inlined hits.
     *
     * @param wantHtml The wantHtml boolean to set
     */
    @GraphQLInputField(name = "wantHtml", description="Inlined hits to return HTML parts if available")
    public void setWantHtml(Boolean wantHtml) {
        this.wantHtml = wantHtml;
    }

    /**
     * Gets the value of needCanExpand.
     *
     * @return The needCanExpand value
     */
    public Boolean getNeedCanExpand() {
        return needCanExpand;
    }

    /**
     * Setting for expanding groups.
     *
     * @param needCanExpand the needCanExpand to set
     */
    @GraphQLInputField(name = "needCanExpand", description="If `needExp` is set in the request, two additional flags may be included in `emails` results elements for messages returned inline. (1) `isGroup`: set if the email address is a group. (2) `exp`: present only when `isGroup` set to true. Unset if the authenticated user does not have permission to expand group members")
    public void setNeedCanExpand(Boolean needCanExpand) {
        this.needCanExpand = needCanExpand;
    }

    /**
     * Gets the value of neuterImages.
     *
     * @return The neuterImages value
     */
    public Boolean getNeuterImages() {
        return neuterImages;
    }

    /**
     * Setting to stop images in inlined HTML parts.
     *
     * @param neuterImages The neuterImages to set
     */
    @GraphQLInputField(name = "neuterImages", description="Set to false to stop images in inlined HTML parts from being \"neutered\"")
    public void setNeuterImages(Boolean neuterImages) {
        this.neuterImages = neuterImages;
    }

    /**
     * Gets the value of wantRecipients.
     *
     * @return The wantRecipients
     */
    public WantRecipsSetting getWantRecipients() {
        return wantRecipients;
    }

    /**
     * Setting to include recipients.
     *
     * @param wantRecipients The wantRecipients value to set
     */
    @GraphQLInputField(name = "wantRecipients", description="Want recipients setting. If true, sent messages that contain the \"To:\" recipients instead of the sender. Returned conversations whose first hit was sent by the user will contain that hit's \"To:\" recipients instead of the conversation's sender list")
    public void setWantRecipients(WantRecipsSetting wantRecipients) {
        this.wantRecipients = wantRecipients;
    }

    /**
     * Gets the value of prefetch.
     *
     * @return the prefetch
     */
    public Boolean getPrefetch() {
        return prefetch;
    }

    /**
     * Prefetch setting.
     *
     * @param prefetch The prefetch boolean to set
     */
    @GraphQLInputField(name = "prefetch", description="Prefetch")
    public void setPrefetch(Boolean prefetch) {
        this.prefetch = prefetch;
    }

    /**
     * Gets the value of resultMode.
     *
     * @return The resultMode
     */
    public String getResultMode() {
        return resultMode;
    }

    /**
     * Setting specifies the type of result. NORMAL [default]: Everything, IDS: Only IDs.
     *
     * @param resultMode The resultMode to set
     */
    @GraphQLInputField(name = "resultMode", description="Specifies the type of result. NORMAL [default]: Everything, IDS: Only IDs")
    public void setResultMode(String resultMode) {
        this.resultMode = resultMode;
    }

    /**
     * Gets the value of fullConversation.
     *
     * @return The fullConversation value
     */
    public ZmBoolean getFullConversation() {
        return ZmBoolean.fromBool(fullConversation);
    }

    /**
     * The setting for full conversations.
     *
     * @param fullConversation The fullConversation boolean to set
     */
    @GraphQLInputField(name = "fullConversation", description="Full conversations")
    public void setFullConversation(Boolean fullConversation) {
        this.fullConversation = fullConversation;
    }

    /**
     * Gets the value of field.
     *
     * @return The field value
     */
    public String getField() {
        return field;
    }

    /**
     * The setting of the field to search on, by default, searches the CONTENT field.<br>
     * 'content:' [the default] or 'subject:', etc.
     *
     * @param field The field value to set
     */
    @GraphQLInputField(name = "field", description="By default, searches the CONTENT field. 'content:' [the default] or 'subject:', etc.")
    public void setField(String field) {
        this.field = field;
    }

    /**
     * Gets the value of limit.
     *
     * @return The limit value
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * The maximum number of results to return. It defaults to 10 if not specified,
     * and is capped by 1000
     *
     * @param limit The limit value to set
     */
    @GraphQLInputField(name = "limit", description="The maximum number of results to return. It defaults to 10 if not specified, and is capped by 1000")
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * Gets the value of offset.
     *
     * @return The offset value
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * Specifies the 0-based offset into the results list to return as the first result for
     * this search operation. For example, limit=10 offset=30 will return the 31st through 40th
     * results inclusive.
     *
     * @param offset The offset value to set
     */
    @GraphQLInputField(name = "offset", description="Specifies the 0-based offset into the results list to return as the first result for this search operation. For example, limit=10 offset=30 will return the 31st through 40th results inclusive.")
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * Gets the value of calTz.
     *
     * @return The calTz value
     */
    public CalTZInfo getCalTz() {
        return calTz;
    }

    /**
     * Sets the timezone specification.
     *
     * @param calTz The CalTZInfo value to set
     */
    @GraphQLInputField(name = "calTz", description="Timezone specification")
    public void setCalTz(CalTZInfo calTz) {
        this.calTz = calTz;
    }

    /**
     * Gets the value of locale.
     *
     * @return The locale value
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Sets the client locale identification.
     * Value is of the form LL-CC[-V+].
     * - LL 2 character language code
     * - CC is 2 character country code
     * - V+ is optional variant identifier string
     *
     * @param locale The locale to set
     */
    @GraphQLInputField(name = "locale", description="Client locale identification. Value is of the form LL-CC[-V+]. LL 2 character language code; CC is 2 character country code; V+ is optional variant identifier string")
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * Gets the value of cursor.
     *
     * @return The cursor value
     */
    public CursorInfo getCursor() {
        return cursor;
    }

    /**
     * Cursor specification to use.
     *
     * @param cursor The CursorInfo value to set
     */
    @GraphQLInputField(name = "cursor", description="Cursor specification.")
    public void setCursor(CursorInfo cursor) {
        this.cursor = cursor;
    }

    /**
     * Gets the value of msgContent.
     *
     * @return the msgContent
     */
    public MsgContent getMsgContent() {
        return msgContent;
    }

    /**
     * Message content the client expects in response.
     *
     * @param the msgContent to set
     */
    @GraphQLInputField(name = "msgContent", description="Message Content the client expects in response. full: The complete message; original: Only the Message and not quoted text; both: The complete message and also this message without quoted text ")
    public void setMsgContent(MsgContent msgContent) {
        this.msgContent = msgContent;
    }

    /**
     * Gets the value of includeMemberOf.
     *
     * @return the includeMemberOf setting value
     */
    public Boolean getIncludeMemberOf() {
        return includeMemberOf;
    }

    /**
     * List the contact groups this contact is a member of.
     *
     * @param includeMemberOf Boolean to list the contact groups this contact is a member of
     */
    @GraphQLInputField(name = "includeMemberOf", description="Include the list of contact groups this contact is a member of. Performance penalty associated with computing this information.")
    public void setIncludeMemberOf(Boolean includeMemberOf) {
        this.includeMemberOf = includeMemberOf;
    }

    /**
     * Gets the value of headers.
     *
     * @return the headers
     */
    public List<AttributeName> getHeaders() {
        return headers;
    }

    /**
     * List of requested header's to be included in inlined message hits.
     *
     * @param headers the list of headers to be included in inlined message hits
     */
    @GraphQLInputField(name = "headers", description="List of requested header's to be included in inlined message hits")
    public void setHeaders(List<AttributeName> headers) {
        this.headers = headers;
    }

}
