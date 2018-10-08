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

import java.util.ArrayList;
import java.util.List;

import com.zimbra.soap.account.type.Attr;
import com.zimbra.soap.account.type.AuthToken;
import com.zimbra.soap.account.type.PreAuth;
import com.zimbra.soap.account.type.Pref;
import com.zimbra.soap.type.AccountSelector;

import io.leangen.graphql.annotations.GraphQLInputField;
import io.leangen.graphql.annotations.types.GraphQLType;

/**
 * The GQLAuthRequestInput class.<br>
 * Contains auth request properties.
 * @see com.zimbra.soap.account.message.AuthRequest
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models.inputs
 * @copyright Copyright © 2018
 */
@GraphQLType(name="AuthRequest", description="Input for authentication request.")
public class GQLAuthRequestInput {

    protected AccountSelector account;
    protected String password;
    protected PreAuth preauthToken;
    protected AuthToken authToken;
    protected String jwtAuthToken;
    protected String trustedDeviceToken;
    protected String tokenType;
    protected String receoveryCode;
    protected String twoFactorCode;
    protected String virtualHost;
    protected String deviceId;
    protected Boolean doPersistCookie;
    protected Boolean isCsrfSupported;
    protected Boolean isDeviceTrusted;
    protected Boolean doGenerateDeviceId;
    protected List<Pref> prefs = new ArrayList<Pref>();
    protected List<Attr> attrs = new ArrayList<Attr>();

    public AccountSelector getAccount() {
        return account;
    }

    @GraphQLInputField(name="account", description="Specifies the account to authenticate against")
    public void setAccount(AccountSelector account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    @GraphQLInputField(name="password", description="Password to use in conjunction with an account")
    public void setPassword(String password) {
        this.password = password;
    }

    public PreAuth getPreauthToken() {
        return preauthToken;
    }

    @GraphQLInputField(name="preauthToken", description="<preauth> is an alternative to <account>. See preauth.txt")
    public void setPreauthToken(PreAuth preauthToken) {
        this.preauthToken = preauthToken;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    @GraphQLInputField(name="authToken", description="An authToken can be passed instead of account/password/preauth to validate an existing auth token.")
    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public String getJwtAuthToken() {
        return jwtAuthToken;
    }

    @GraphQLInputField(name="jwtAuthToken", description="JWT auth token")
    public void setJwtAuthToken(String jwtAuthToken) {
        this.jwtAuthToken = jwtAuthToken;
    }

    public String getTrustedDeviceToken() {
        return trustedDeviceToken;
    }

    @GraphQLInputField(name="trustedDeviceToken", description="Whether the client represents a trusted device")
    public void setTrustedDeviceToken(String trustedDeviceToken) {
        this.trustedDeviceToken = trustedDeviceToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    @GraphQLInputField(name="tokenType", description="Type of token to be returned, it can be auth or jwt")
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getReceoveryCode() {
        return receoveryCode;
    }

    @GraphQLInputField(name="recoveryCode", description="RecoveryCode to use in conjunction with an account in case of forgot password flow.")
    public void setReceoveryCode(String receoveryCode) {
        this.receoveryCode = receoveryCode;
    }

    public String getTwoFactorCode() {
        return twoFactorCode;
    }

    @GraphQLInputField(name="twoFactorCode", description="The TOTP code used for two-factor authentication")
    public void setTwoFactorCode(String twoFactorCode) {
        this.twoFactorCode = twoFactorCode;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    @GraphQLInputField(name="virtualHost", description="If specified (in conjunction with by=\"name\"), virtual-host is used to determine the domain of the account name, if it does not include a domain component.")
    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

    public String getDeviceId() {
        return deviceId;
    }

    @GraphQLInputField(name="deviceId", description="Unique device identifier; used to verify trusted mobile devices")
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Boolean getDoPersistCookie() {
        return doPersistCookie;
    }

    @GraphQLInputField(name="doPersistCookie", description="Denotes whether the auth token cookie in the response should be persisted when the browser exits")
    public void setDoPersistCookie(Boolean doPersistCookie) {
        this.doPersistCookie = doPersistCookie;
    }

    public Boolean getIsCsrfSupported() {
        return isCsrfSupported;
    }

    @GraphQLInputField(name="isCsrfSupported", description="Denotes whether the client supports CSRF token")
    public void setIsCsrfSupported(Boolean isCsrfSupported) {
        this.isCsrfSupported = isCsrfSupported;
    }

    public Boolean getIsDeviceTrusted() {
        return isDeviceTrusted;
    }

    @GraphQLInputField(name="isDeviceTrusted", description="Denotes whether the client represents a trusted device")
    public void setIsDeviceTrusted(Boolean isDeviceTrusted) {
        this.isDeviceTrusted = isDeviceTrusted;
    }

    public Boolean getDoGenerateDeviceId() {
        return doGenerateDeviceId;
    }

    @GraphQLInputField(name="doGenerateDeviceId", description="Denotes whether to generate a device id")
    public void setDoGenerateDeviceId(Boolean doGenerateDeviceId) {
        this.doGenerateDeviceId = doGenerateDeviceId;
    }

    public List<Pref> getPrefs() {
        return prefs;
    }

    @GraphQLInputField(name="prefs", description="Requested preference settings")
    public void setPrefs(List<Pref> prefs) {
        this.prefs = prefs;
    }

    public List<Attr> getAttrs() {
        return attrs;
    }

    @GraphQLInputField(name="attrs", description="Requested attribute settings. Only attributes that are allowed will be returned")
    public void setAttrs(List<Attr> attrs) {
        this.attrs = attrs;
    }

}
