package com.zimbra.graphql.utilities;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.zimbra.common.service.ServiceException;
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
import com.zimbra.cs.service.AuthProvider;
import com.zimbra.graphql.models.AuthContext;

public class GQLAuthUtilities {
    /**
     * Creates an auth context for the request.
     *
     * @param req The http request
     * @return An auth context
     */
    public static AuthContext buildContext(HttpServletRequest req) {
        AuthToken token = null;
        Account account = null;
        try {
            final Cookie [] cookies = req.getCookies();
            final Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", req.getHeader("Authorization"));
            token = getAuthToken(cookies, headers);
            account = getAccount(token);
        } catch (final ServiceException e) {
            ZimbraLog.extensions.debug("Could not authenticate the user.");
            // continue on to create empty auth context
            // some resources may not require auth
        }
        final AuthContext context = new AuthContext();
        context.setAuthToken(token);
        context.setAccount(account);
        // TODO: include operation context details
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
            throw ServiceException.PERM_DENIED(HttpServletResponse.SC_UNAUTHORIZED + ": "
                + L10nUtil.getMessage(MsgKey.errMustAuthenticate));
        }
        return authToken;
    }

    /**
     * Returns the requesting user's account.<br>
     * Throws an exception if an account cannot be retrieved.
     *
     * @param authToken The auth token to retrieve the account with
     * @return The requesting user's account
     * @throws ServiceException If there are issues retrieving the account
     */
    protected static Account getAccount(AuthToken authToken) throws ServiceException {
        Account account = null;
        if (authToken != null) {
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
        } else {
            throw ServiceException.PERM_DENIED(HttpServletResponse.SC_UNAUTHORIZED + ": "
                + L10nUtil.getMessage(MsgKey.errMustAuthenticate));
        }

        if (account == null) {
            throw ServiceException.PERM_DENIED(HttpServletResponse.SC_UNAUTHORIZED + ": "
                + L10nUtil.getMessage(MsgKey.errMustAuthenticate));
        }

        ZimbraLog.extensions.debug("Account is:%s", account);

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
}
