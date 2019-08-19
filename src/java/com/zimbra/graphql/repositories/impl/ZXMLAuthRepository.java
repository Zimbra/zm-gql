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

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.Element;
import com.zimbra.cs.service.account.Auth;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.models.inputs.GQLAuthRequestInput;
import com.zimbra.graphql.repositories.IRepository;
import com.zimbra.graphql.repositories.ZXMLRepository;
import com.zimbra.graphql.utilities.XMLDocumentUtilities;
import com.zimbra.soap.account.message.AuthRequest;
import com.zimbra.soap.account.message.AuthResponse;

/**
 * The ZXMLAuthRepository class.<br>
 * Contains XML document based data access methods for auth.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.repositories.impl
 * @copyright Copyright © 2018
 */
public class ZXMLAuthRepository extends ZXMLRepository implements IRepository {

    /**
     * The auth document handler.
     */
    protected final Auth authHandler;

    /**
     * Creates an instance with default document handlers.
     */
    public ZXMLAuthRepository() {
        this(new Auth());
    }

    /**
     * Creates an instance with specified handlers.
     *
     * @param authHandler The auth handler
     */
    public ZXMLAuthRepository(Auth authHandler) {
        super();
        this.authHandler = authHandler;
    }

    /**
     * Performs an auth request with given properties.
     *
     * @param rctxt The request context
     * @param authInput Auth properties
     * @return The auth response
     * @throws ServiceException If there are issues executing the document
     */
    public AuthResponse authenticate(RequestContext rctxt, GQLAuthRequestInput authInput)
        throws ServiceException {
        final AuthRequest req = new AuthRequest();
        req.setAccount(authInput.getAccount());
        req.setPassword(authInput.getPassword());
        req.setPreauth(authInput.getPreauthToken());
        req.setAuthToken(authInput.getAuthToken());
        req.setJwtToken(authInput.getJwtAuthToken());
        req.setTrustedDeviceToken(authInput.getTrustedDeviceToken());
        req.setTokenType(authInput.getTokenType());
        req.setRecoveryCode(authInput.getReceoveryCode());
        req.setTwoFactorCode(authInput.getTwoFactorCode());
        req.setVirtualHost(authInput.getVirtualHost());
        req.setDeviceId(authInput.getDeviceId());
        req.setPersistAuthTokenCookie(authInput.getDoPersistCookie());
        req.setCsrfSupported(authInput.getIsCsrfSupported());
        req.setDeviceTrusted(authInput.getIsDeviceTrusted());
        req.setGenerateDeviceId(authInput.getDoGenerateDeviceId());
        req.setPrefs(authInput.getPrefs());
        req.setAttrs(authInput.getAttrs());
        // execute
        final Element response = XMLDocumentUtilities.executeDocumentAsGuest(
            authHandler,
            XMLDocumentUtilities.toElement(req),
            rctxt);
        AuthResponse zAuthResponse = null;
        if (response != null) {
            zAuthResponse = XMLDocumentUtilities
                .fromElement(response, AuthResponse.class);
        }
        return zAuthResponse;
    }

}