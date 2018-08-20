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
import com.zimbra.cs.service.account.EndSession;
import com.zimbra.cs.service.account.GetAccountInfo;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.models.outputs.AccountInfo;
import com.zimbra.graphql.repositories.IRepository;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.XMLDocumentUtilities;
import com.zimbra.soap.ZimbraSoapContext;
import com.zimbra.soap.account.message.EndSessionRequest;
import com.zimbra.soap.account.message.GetAccountInfoRequest;
import com.zimbra.soap.account.message.GetAccountInfoResponse;
import com.zimbra.soap.type.AccountBy;
import com.zimbra.soap.type.AccountSelector;

/**
 * The ZXMLAccountRepository class.<br>
 * Contains XML document based data access methods for account.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.repositories.impl
 * @copyright Copyright © 2018
 */
public class ZXMLAccountRepository extends ZXMLRepository implements IRepository {

    /**
     * The getAccountInfo document handler.
     */
    protected final GetAccountInfo accountInfoHandler;

    /**
     * The endSession document handler.
     */
    protected final EndSession endSessionHandler;

    /**
     * Creates an instance with default document handlers.
     */
    public ZXMLAccountRepository() {
        this(new GetAccountInfo(), new EndSession());
    }

    /**
     * Creates an instance with specified handlers.
     *
     * @param accountInfoHandler The getAccountInfo handler
     * @param endSessionHandler The endSession handler
     */
    public ZXMLAccountRepository(GetAccountInfo accountInfoHandler, EndSession endSessionHandler) {
        super();
        this.accountInfoHandler = accountInfoHandler;
        this.endSessionHandler = endSessionHandler;
    }

    /**
     * Retrieves account info for the given session.
     *
     * @param rctxt The request context
     * @return The account info for the current requesting user
     * @throws ServiceException If there are issues executing the document
     */
    public AccountInfo accountInfoGet(RequestContext rctxt) throws ServiceException{
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final AccountInfo info = new AccountInfo();
        final AccountSelector selector = new AccountSelector(AccountBy.id,
            zsc.getAuthToken().getAccountId());
        final GetAccountInfoRequest request = new GetAccountInfoRequest(selector);
        final Element response = XMLDocumentUtilities.executeDocument(
                accountInfoHandler,
                zsc,
                XMLDocumentUtilities.toElement(request));
        if (response != null) {
            final GetAccountInfoResponse resp = XMLDocumentUtilities.fromElement(response,
                GetAccountInfoResponse.class);
            info.setName(resp.getName());
            info.setAttrs(resp.getAttrs());
            info.setSoapURL(resp.getSoapURL());
            info.setPublicURL(resp.getPublicURL());
            info.setCommunityURL(resp.getCommunityURL());
            info.setChangePasswordURL(resp.getChangePasswordURL());
            info.setAdminURL(resp.getAdminURL());
            info.setBoshURL(resp.getBoshURL());
        }
        return info;
    }

    /**
     * Ends session for the currently requesting user.
     *
     * @param rctxt The request context
     * @param clearCookies Denotes whether to clear cookies for the requester
     * @throws ServiceException If there are issues executing the document
     */
    public void accountEndSession(RequestContext rctxt, Boolean clearCookies) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final EndSessionRequest request = new EndSessionRequest();
        request.setLogOff(clearCookies);
        final Element response = XMLDocumentUtilities.executeDocument(
                endSessionHandler,
                zsc,
                XMLDocumentUtilities.toElement(request));
        if (response == null) {
            throw ServiceException.FAILURE("EndSessionRequest failed", null);
        }
    }

}
