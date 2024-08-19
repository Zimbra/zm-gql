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

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;

import org.apache.commons.lang.StringUtils;
import org.dom4j.QName;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.Element;
import com.zimbra.common.soap.HeaderConstants;
import com.zimbra.common.soap.SoapProtocol;
import com.zimbra.common.util.Constants;
import com.zimbra.common.util.L10nUtil;
import com.zimbra.common.util.L10nUtil.MsgKey;
import com.zimbra.common.util.StringUtil;
import com.zimbra.common.util.ZimbraCookie;
import com.zimbra.common.util.ZimbraLog;
import com.zimbra.cs.account.Account;
import com.zimbra.cs.account.AuthToken;
import com.zimbra.cs.account.AuthTokenException;
import com.zimbra.cs.account.Provisioning;
import com.zimbra.cs.account.ZimbraAuthToken;
import com.zimbra.cs.account.ZimbraJWToken;
import com.zimbra.cs.service.AuthProvider;
import com.zimbra.cs.servlet.util.CsrfUtil;
import com.zimbra.cs.servlet.CsrfFilter;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.soap.DocumentHandler;
import com.zimbra.soap.SoapEngine;
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
     * Creates a context for the request.
     *
     * @param req The http request
     * @param resp The http response
     * @return A request context
     */
    public static RequestContext buildContext(HttpServletRequest req, HttpServletResponse resp) {
        ZimbraLog.extensions.debug("Building request context.");
        final RequestContext context = new RequestContext();
        context.setRawRequest(req);
        context.setRawResponse(resp);
        return context;
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
            throw ServiceException.PERM_DENIED(L10nUtil.getMessage(MsgKey.errMustAuthenticate));
        }
        return authToken;
    }

    /**
     * Validates an auth token.<br>
     * Returns false if no authToken is present, or an account cannot
     * be retrieved with the specified credentials.
     *
     * @param authToken The auth token to retrieve the account with
     * @return True if the specified token is valid and can retrieve an account
     */
    protected static boolean isValidToken(AuthToken authToken) {
        Account account = null;
        ZimbraLog.extensions.debug("Validating request auth credentials.");
        if (authToken != null && authToken.isZimbraUser() && authToken.isRegistered()) {
            try {
                account = AuthProvider.validateAuthToken(
                    Provisioning.getInstance(), authToken, false);
                if (account != null) {
                    // token is valid if we got an account
                    ZimbraLog.extensions.debug("Account is:%s", account);
                    return true;
                }
            } catch (final ServiceException e) {
                ZimbraLog.extensions.debug(
                    "Failed to retrieve account with request credentials.", e);
            }
        }
        return false;
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
     * Creates a zimbra soap context given the current request.<br>
     * Ensures the qName and handler are not null.<br>
     * Enables sessions, tracks the user agent, and the raw request ip/port.
     *
     * @param qName The request QName
     * @param handler The document handler for this request
     * @param rctxt The request context
     * @return A zimbra soap context
     * @throws ServiceException If there are issues creating the soap context
     */
    public static ZimbraSoapContext getGuestZimbraSoapContext(QName qName, DocumentHandler handler,
        RequestContext rctxt) throws ServiceException {
        if (qName != null && handler != null) {
            final HttpServletRequest request = rctxt.getRawRequest();
            // create a ctxt element to enable session tracking and user agent specification
            final Element ctxtElement = Element.create(SoapProtocol.Soap12, HeaderConstants.CONTEXT);
            ctxtElement.addUniqueElement(HeaderConstants.E_SESSION);
            ctxtElement.addUniqueElement(HeaderConstants.E_USER_AGENT)
                .addAttribute(HeaderConstants.A_NAME, request.getHeader(HttpHeaders.USER_AGENT));
            // create a context map to track request ip and port
            final Map<String, Object> context = new HashMap<String, Object>();
            context.put(SoapEngine.REQUEST_IP, request.getRemoteAddr());
            context.put(SoapEngine.REQUEST_PORT, request.getRemotePort());
            return new ZimbraSoapContext(ctxtElement, qName, handler, context, SoapProtocol.Soap12);
        }
        throw ServiceException.INVALID_REQUEST("Invalid XML request.", null);
    }

    /**
     * Creates a zimbra soap context given the current context's request credentials.
     * Ensures the request contains an auth token.
     *
     * @param rctxt The request context
     * @return A zimbra soap context
     * @throws ServiceException If there are issues creating the soap context
     */
    public static ZimbraSoapContext getZimbraSoapContext(RequestContext rctxt)
        throws ServiceException {
        final HttpServletRequest req = rctxt.getRawRequest();
        final Cookie [] cookies = req.getCookies();
        final Map<String, String> headers = new HashMap<String, String>(1);
        headers.put("Authorization", req.getHeader("Authorization"));
        final AuthToken token = getAuthToken(cookies, headers);
        if (isValidToken(token) && isValidCsrf(token, req)) {
            return new ZimbraSoapContext(token, token.getAccountId(), SoapProtocol.Soap12,
                SoapProtocol.Soap12);
        }
        // if we couldn't retrieve a token, there was no auth on this request
        ZimbraLog.extensions.debug("Request could not be authenticated.");
        throw ServiceException.PERM_DENIED(L10nUtil.getMessage(MsgKey.errMustAuthenticate));
    }

    /**
     * CSRF implementation
     */
    private static boolean isValidCsrf(AuthToken authToken, HttpServletRequest req) {
        boolean doCsrfCheck = false;
        if (req.getAttribute(CsrfFilter.CSRF_TOKEN_CHECK) != null) {
            doCsrfCheck =  (Boolean) req.getAttribute(CsrfFilter.CSRF_TOKEN_CHECK);
        } else if (authToken != null  && authToken.isCsrfTokenEnabled()) {
            doCsrfCheck = true;
        }

        if(doCsrfCheck) {
            String csrfToken = req.getHeader(Constants.CSRF_TOKEN);
            if (StringUtil.isNullOrEmpty(csrfToken)) {
                ZimbraLog.extensions.debug("No CSRF token received.");
                return false;
            }

            //check for valid CSRF token
            if (!CsrfUtil.isValidCsrfToken(csrfToken, authToken)) {
                ZimbraLog.extensions.debug("CSRF check FAILED.");
                return false;
            }
        }
        return true;
    }
}
