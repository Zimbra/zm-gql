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
package com.zimbra.graphql.utilities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.dom4j.QName;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.SoapProtocol;
import com.zimbra.common.util.L10nUtil;
import com.zimbra.common.util.L10nUtil.MsgKey;
import com.zimbra.common.util.ZimbraCookie;
import com.zimbra.common.util.ZimbraLog;
import com.zimbra.cs.account.Account;
import com.zimbra.cs.account.AuthToken;
import com.zimbra.cs.account.AuthTokenException;
import com.zimbra.cs.account.Provisioning;
import com.zimbra.cs.account.ZimbraAuthToken;
import com.zimbra.cs.account.ZimbraJWToken;
import com.zimbra.cs.mailbox.OperationContext;
import com.zimbra.cs.service.AuthProvider;
import com.zimbra.graphql.models.AuthContext;
import com.zimbra.soap.DocumentHandler;
import com.zimbra.soap.ZimbraSoapContext;

/**
 * The GQLAuthUtilities class.<br>
 * Contains utility methods to assist in request authentication.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.utilities
 * @copyright Copyright © 2018
 */
public class GQLAuthUtilities {

    /**
     * Creates an auth context for the request.
     *
     * @param req The http request
     * @param resp The http response
     * @return An auth context
     */
    public static AuthContext buildContext(HttpServletRequest req, HttpServletResponse resp) {
        AuthToken token = null;
        Account account = null;
        final AuthContext context = new AuthContext();
        context.setRawRequest(req);
        context.setRawResponse(resp);
        ZimbraLog.extensions.debug("Building request context.");
        try {
            final Cookie [] cookies = req.getCookies();
            final Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", req.getHeader("Authorization"));
            token = getAuthToken(cookies, headers);
            account = getAccount(token);
        } catch (final ServiceException e) {
            ZimbraLog.extensions.debug("Could not authenticate the user.");
            // if an exception occurred, auth was present but invalid
            // return an auth context with just the request, and response
            return context;
        }
        context.setAuthToken(token);
        context.setAccount(account);
        context.setOperationContext(getOperationContext(req, token));
        return context;
    }

    /**
     * Creates an operation context for a given http request and auth token.<br>
     * Returns `null` if no authToken or req is present.
     *
     * @param req The http request
     * @param authToken The auth token from the request
     * @return An operation context
     */
    protected static OperationContext getOperationContext(HttpServletRequest req,
        AuthToken authToken) {
        OperationContext octxt = null;
        if (req != null && authToken != null) {
            try {
                octxt = new OperationContext(authToken);
                octxt.setRequestIP(req.getRemoteAddr());
                octxt.setUserAgent(req.getHeader("User-Agent"));
                if (authToken != null) {
                    octxt.setmAuthTokenAccountId(authToken.getAccountId());
                    octxt.setmRequestedAccountId(authToken.getAccountId());
                }
                // TODO : handle change constraint, and real requestedAccountId
            } catch (final ServiceException e) {
                // continue on to return empty octxt
                // some resources may not require auth
            }
        }
        return octxt;
    }

    /**
     * Retrieves authToken from header or cookie.<br>
     * JWT is searched for as priority, then cookie.
     *
     * @param cookies Request cookies
     * @param headers Request headers
     * @return An auth token
     * @throws ServiceException If there are issues creating the auth token
     */
    protected static AuthToken getAuthToken(Cookie[] cookies, Map<String, String> headers)
        throws ServiceException {
        AuthToken authToken = null;
        // search for JWT auth first (priority)
        final String salt = getFromCookie(cookies, ZimbraCookie.COOKIE_ZM_JWT);
        final String jwtString = headers.get("Authorization");
        try {
            if (!StringUtils.isEmpty(jwtString) && !StringUtils.isEmpty(salt)) {
                authToken = ZimbraJWToken
                    .getJWToken(StringUtils.substringAfter(jwtString, "Bearer "), salt);
            }
            if (authToken == null) {
                // if we couldn't find a JWT, search for cookie auth
                final String cookieString = getFromCookie(cookies,
                    ZimbraCookie.authTokenCookieName(false));
                if (!StringUtils.isEmpty(cookieString)) {
                    authToken = ZimbraAuthToken.getAuthToken(cookieString);
                }

            }
        } catch (final AuthTokenException e) {
            ZimbraLog.extensions.info("Error authenticating user.");
            throw ServiceException.PERM_DENIED(HttpServletResponse.SC_UNAUTHORIZED + ": "
                + L10nUtil.getMessage(MsgKey.errMustAuthenticate));
        }
        return authToken;
    }

    /**
     * Returns the requesting user's account.<br>
     * If no authToken is present, returns null (some requests do not require auth).<br>
     * Throws an exception if an account cannot be retrieved with non-null credentials.
     *
     * @param authToken The auth token to retrieve the account with
     * @return The requesting user's account
     * @throws ServiceException If there are issues retrieving the account
     */
    protected static Account getAccount(AuthToken authToken) throws ServiceException {
        Account account = null;
        if (authToken != null) {
            ZimbraLog.extensions.debug("Validating request auth credentials.");
            if (authToken.isZimbraUser()) {
                if (!authToken.isRegistered()) {
                    throw ServiceException.PERM_DENIED(HttpServletResponse.SC_UNAUTHORIZED + ": "
                        + L10nUtil.getMessage(MsgKey.errMustAuthenticate));
                }
                try {
                    account = AuthProvider.validateAuthToken(Provisioning.getInstance(),
                        authToken, false);
                } catch (final ServiceException e) {
                    throw ServiceException.PERM_DENIED(HttpServletResponse.SC_UNAUTHORIZED + ": "
                        + L10nUtil.getMessage(MsgKey.errMustAuthenticate));
                }
            } else {
                throw ServiceException.PERM_DENIED(HttpServletResponse.SC_UNAUTHORIZED + ": "
                    + L10nUtil.getMessage(MsgKey.errMustAuthenticate));
            }
            if (account == null) {
                throw ServiceException.PERM_DENIED(HttpServletResponse.SC_UNAUTHORIZED + ": "
                    + L10nUtil.getMessage(MsgKey.errMustAuthenticate));
            }
            ZimbraLog.extensions.debug("Account is:%s", account);
        }
        // do nothing if no token exists
        // some resources may not require auth

        return account;
    }

    /**
     * Retrieves a cookie from the cookie jar.
     *
     * @param cookies Cookie jar
     * @param cookieName The specific cookie we need
     * @return A cookie
     */
    private static String getFromCookie(Cookie [] cookies, String cookieName) {
        String cookie = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(cookieName)) {
                    cookie = cookies[i].getValue();
                    break;
                }
            }
        }
        return cookie;
    }

    /**
     * Creates a zimbra soap context given the current request.
     * Ensures the qName and handler are not null.
     *
     * @param qName The request QName
     * @param handler The document handler for this request
     * @return A zimbra soap context
     * @throws ServiceException If there are issues creating the soap context
     */
    public static ZimbraSoapContext getGuestZimbraSoapContext(QName qName, DocumentHandler handler)
        throws ServiceException {
        if (qName != null && handler != null) {
            return new ZimbraSoapContext(null, qName, handler, Collections.emptyMap(), SoapProtocol.Soap12);
        }
        throw ServiceException.INVALID_REQUEST("Invalid XML request.", null);
    }

    /**
     * Creates a zimbra soap context given the current context's request credentials.
     * Ensures the operation context and account are non null.
     *
     * @param octxt The operation context
     * @param account The requesting account
     * @return A zimbra soap context
     * @throws ServiceException If there are issues creating the soap context
     */
    public static ZimbraSoapContext getZimbraSoapContext(OperationContext octxt, Account account)
        throws ServiceException {
        if (octxt != null && account != null) {
            return new ZimbraSoapContext(octxt.getAuthToken(), account.getId(), SoapProtocol.Soap12,
                SoapProtocol.Soap12);
        }
        ZimbraLog.extensions.debug("Request could not be authenticated.");
        throw ServiceException.PERM_DENIED("Request could not be authenticated.");
    }

}
