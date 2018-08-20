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

import com.google.common.collect.Lists;
import com.zimbra.soap.type.AttributeName;
import com.zimbra.soap.type.Id;

import io.leangen.graphql.annotations.GraphQLInputField;
import io.leangen.graphql.annotations.types.GraphQLType;

/**
 * The GQLContactsRequestInput class.<br>
 * Contains contacts request properties.
 * @see com.zimbra.soap.mail.message.GetContactsRequest
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models.inputs
 * @copyright Copyright © 2018
 */
@GraphQLType(name="GetContactsRequest", description="Input for get contacts request.")
public class GQLGetContactsRequestInput {

    private String folderId;
    private String sortBy;
    private Boolean doSync;
    private Boolean doDerefGroupMember;
    private Boolean doGetMemberOf;
    private Boolean doGetHiddenAttrs;
    private Boolean doGetCertInfo;
    private Boolean goGetImapUid;
    private Long maxMembers;
    @GraphQLInputField(name="attributes", description="If present, return only the specified attribute(s).")
    private final List<AttributeName> attributes = Lists.newArrayList();
    @GraphQLInputField(name="memberAttributes", description="If present, return only the specified attribute(s) for derefed members, applicable only when doDerefGroupMember is set.")
    private final List<AttributeName> memberAttributes = Lists.newArrayList();
    @GraphQLInputField(name="contacts", description="If present, only get the specified contact(s).")
    private final List<Id> contacts = Lists.newArrayList();

    public String getFolderId() {
        return folderId;
    }

    @GraphQLInputField(name="folderId", description="If is present, return only contacts in the specified folder")
    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getSortBy() {
        return sortBy;
    }

    @GraphQLInputField(name="sortBy", description="Sort By")
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Boolean getDoSync() {
        return doSync;
    }

    @GraphQLInputField(name="doSync", description="Denotes whether to return modified date (md) on contacts")
    public void setDoSync(Boolean doSync) {
        this.doSync = doSync;
    }

    public Boolean getDoDerefGroupMember() {
        return doDerefGroupMember;
    }

    @GraphQLInputField(name="doDerefGroupMember", description="Denotes whether to deref contact group members")
    public void setDoDerefGroupMember(Boolean doDerefGroupMember) {
        this.doDerefGroupMember = doDerefGroupMember;
    }

    public Boolean getDoGetMemberOf() {
        return doGetMemberOf;
    }

    @GraphQLInputField(name="doGetMemberOf", description="Denotes whether to include the list of contact groups this contact is a member of")
    public void setDoGetMemberOf(Boolean doGetMemberOf) {
        this.doGetMemberOf = doGetMemberOf;
    }

    public Boolean getDoGetHiddenAttrs() {
        return doGetHiddenAttrs;
    }

    @GraphQLInputField(name="doGetHiddenAttrs", description="Denotes whether to return contact hidden attrs defined in zimbraContactHiddenAttributes")
    public void setDoGetHiddenAttrs(Boolean doGetHiddenAttrs) {
        this.doGetHiddenAttrs = doGetHiddenAttrs;
    }

    public Boolean getDoGetCertInfo() {
        return doGetCertInfo;
    }

    @GraphQLInputField(name="doGetCertInfo", description="Denotes whether to return smime certificate info")
    public void setDoGetCertInfo(Boolean doGetCertInfo) {
        this.doGetCertInfo = doGetCertInfo;
    }

    public Boolean getDoGetImapUid() {
        return goGetImapUid;
    }

    @GraphQLInputField(name="doGetImapUid", description="Set to return IMAP UID.  (default is unset.)")
    public void setDoGetImapUid(Boolean doGetImapUid) {
        this.goGetImapUid = doGetImapUid;
    }

    public Long getMaxMembers() {
        return maxMembers;
    }

    @GraphQLInputField(name="maxMembers", description="Set to return IMAP UID.  (default is unset.)")
    public void setMaxMembers(Long maxMembers) {
        this.maxMembers = maxMembers;
    }

    public List<AttributeName> getAttributes() {
        return attributes;
    }

    @GraphQLInputField(name="attributes", description="If present, return only the specified attribute(s).")
    public void addAttributes(List<AttributeName> attrs) {
        this.attributes.addAll(attrs);
    }

    public List<AttributeName> getMemberAttributes() {
        return memberAttributes;
    }

    @GraphQLInputField(name="memberAttributes", description="If present, return only the specified attribute(s) for derefed members, applicable only when doDerefGroupMember is set.")
    public void addMemberAttributes(List<AttributeName> attrs) {
        this.memberAttributes.addAll(attrs);
    }

    public List<Id> getContacts() {
        return contacts;
    }

    @GraphQLInputField(name="contacts", description="If present, only get the specified contact(s).")
    public void addContacts(List<Id> contacts) {
        this.contacts.addAll(contacts);
    }

}
