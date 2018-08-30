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

import java.util.List;
import java.util.stream.Collectors;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.util.ZimbraLog;
import com.zimbra.cs.account.AuthTokenException;
import com.zimbra.cs.session.Session;
import com.zimbra.cs.session.SessionCache;
import com.zimbra.cs.session.SoapSession;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.models.outputs.GQLSessionInfo;
import com.zimbra.graphql.repositories.IRepository;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.soap.ZimbraSoapContext;
import com.zimbra.soap.account.type.AuthToken;

/**
 * The ZNativeAuthRepository class.<br>
 * Contains direct Zimbra data access methods for auth.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.repositories.impl
 * @copyright Copyright © 2018
 */
public class ZNativeAuthRepository extends ZRepository implements IRepository {

    /**
     * Creates an instance.
     */
    public ZNativeAuthRepository() {
        super();
    }

    /**
     * Retrieves a list of sessions associated with the request account.<br>
     * Sessions are retrieved from the acting server's SessionCache.
     *
     * @param rctxt The request context
     * @return List of associated sessions
     * @throws ServiceException If there are issues retrieving the session information
     */
    public List<GQLSessionInfo> sessions(RequestContext rctxt) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final String accountId = zsc.getRequestedAccountId();
        // get all sessions by account id
        return SessionCache.getAllSessions(accountId)
            .stream()
            // filter out non-imap/soap types
            .filter(p -> Session.Type.IMAP.equals(p.getSessionType())
                      || Session.Type.SOAP.equals(p.getSessionType()))
            // build session info list
            .map(p -> toSessionInfo(p))
            .collect(Collectors.toList());
    }

    /**
     * Builds a GQLSessionInfo response object from a Session.
     *
     * @param session The session data
     * @return GQLSessionInfo response
     */
    protected GQLSessionInfo toSessionInfo(Session session) {
        final GQLSessionInfo sessionInfo = new GQLSessionInfo();
        sessionInfo.setAuthToken(getAuthToken(session));
        sessionInfo.setCreatedDate(session.getCreationTime());
        sessionInfo.setLastAccessed(session.getLastAccessTime());
        sessionInfo.setUserAgent(session.getUserAgent());
        sessionInfo.setRequestIPAddress(session.getRequestIPAddress());
        return sessionInfo;
    }

    /**
     * Retrieves auth token from session and converts to response token.
     *
     * @param session The session to retrieve auth token from
     * @return Response AuthToken
     */
    protected AuthToken getAuthToken(Session session) {
        AuthToken responseToken = null;
        // fetch auth token if soap session
        if (session != null && session instanceof SoapSession) {
            try {
                final com.zimbra.cs.account.AuthToken authToken = ((SoapSession)session).getAuthToken();
                if (authToken != null) {
                    responseToken = new AuthToken(authToken.getEncoded(), false);
                    responseToken.setLifetime(authToken.getExpires());
                }
            } catch (final AuthTokenException e) {
                ZimbraLog.extensions.error(
                    "Unable to determine auth token associated with session id: %s.",
                    session.getSessionId());
                // don't fail all on missing authToken
            }
        }
        return responseToken;
    }

}