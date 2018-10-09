/*
 * ***** BEGIN LICENSE BLOCK *****
 * Zimbra Collaboration Suite Server
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

package com.zimbra.graphql.models.outputs;

import java.util.List;

import com.google.common.collect.Lists;
import com.zimbra.common.gql.GqlConstants;
import com.zimbra.soap.type.NamedValue;
import com.zimbra.soap.account.type.LicenseInfo;

import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLType;

@GraphQLType(name=GqlConstants.CLASS_ACCOUNT_INFO, description="account info response data")
public class AccountInfo {
    @GraphQLNonNull
    @GraphQLQuery(name=GqlConstants.NAME)
    private String name;
    @GraphQLNonNull
    @GraphQLQuery(name=GqlConstants.ATTRIBUTES, description="account attributes")
    private List<NamedValue> attrs = Lists.newArrayList();
    @GraphQLQuery(name=GqlConstants.SOAP_URL, description="soap url")
    private String soapURL;
    @GraphQLQuery(name=GqlConstants.PUBLIC_URL, description="public url")
    private String publicURL;
    @GraphQLQuery(name=GqlConstants.CHANGE_PASSWORD_URL, description="change password url")
    private String changePasswordURL;
    @GraphQLQuery(name=GqlConstants.COMMUNITY_URL, description="community url")
    private String communityURL;
    @GraphQLQuery(name=GqlConstants.ADMIN_URL, description="admin url")
    private String adminURL;
    @GraphQLQuery(name=GqlConstants.BOSH_URL, description="bosh url")
    private String boshURL;
    @GraphQLQuery(name=GqlConstants.LICENSE, description="License details")
    private LicenseInfo License;

    /*
     * default constructor
     */
    public AccountInfo() {
        this.name = null;
        this.attrs = null;
    }

    /**
     * @param name
     * @param attrs
     */
    public AccountInfo(String name, List<NamedValue> attrs) {
        this.name = name;
        this.attrs = attrs;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the attrs
     */
    public List<NamedValue> getAttrs() {
        return attrs;
    }

    /**
     * @param attrs the attrs to set
     */
    public void setAttrs(List<NamedValue> attrs) {
        this.attrs = attrs;
    }

    /**
     * @return the soapURL
     */
    public String getSoapURL() {
        return soapURL;
    }

    /**
     * @param soapURL the soapURL to set
     */
    public void setSoapURL(String soapURL) {
        this.soapURL = soapURL;
    }

    /**
     * @return the publicURL
     */
    public String getPublicURL() {
        return publicURL;
    }

    /**
     * @param publicURL the publicURL to set
     */
    public void setPublicURL(String publicURL) {
        this.publicURL = publicURL;
    }

    /**
     * @return the changePasswordURL
     */
    public String getChangePasswordURL() {
        return changePasswordURL;
    }

    /**
     * @param changePasswordURL the changePasswordURL to set
     */
    public void setChangePasswordURL(String changePasswordURL) {
        this.changePasswordURL = changePasswordURL;
    }

    /**
     * @return the communityURL
     */
    public String getCommunityURL() {
        return communityURL;
    }

    /**
     * @param communityURL the communityURL to set
     */
    public void setCommunityURL(String communityURL) {
        this.communityURL = communityURL;
    }

    /**
     * @return the adminURL
     */
    public String getAdminURL() {
        return adminURL;
    }

    /**
     * @param adminURL the adminURL to set
     */
    public void setAdminURL(String adminURL) {
        this.adminURL = adminURL;
    }

    /**
     * @return the boshURL
     */
    public String getBoshURL() {
        return boshURL;
    }

    /**
     * @param boshURL the boshURL to set
     */
    public void setBoshURL(String boshURL) {
        this.boshURL = boshURL;
    }
    
    /**
     * @return the LicenseInfo
     */
    public LicenseInfo getLicense() {
        return License;
    }

    /**
     * @param LicenseInfo the LicenseInfo to set
     */
    public void setLicense(LicenseInfo License) {
        this.License = License;
    }

}
