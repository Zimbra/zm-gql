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

import org.apache.commons.lang.StringUtils;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.Element;
import com.zimbra.cs.service.account.ChangePassword;
import com.zimbra.cs.service.account.EndSession;
import com.zimbra.cs.service.account.GetAccountInfo;
import com.zimbra.cs.service.account.GetInfo;
import com.zimbra.cs.service.account.GetPrefs;
import com.zimbra.cs.service.account.ModifyPrefs;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.models.inputs.GQLPrefInput;
import com.zimbra.graphql.models.outputs.AccountInfo;
import com.zimbra.graphql.repositories.IRepository;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.XMLDocumentUtilities;
import com.zimbra.soap.ZimbraSoapContext;
import com.zimbra.soap.account.message.ChangePasswordRequest;
import com.zimbra.soap.account.message.ChangePasswordResponse;
import com.zimbra.soap.account.message.EndSessionRequest;
import com.zimbra.soap.account.message.GetAccountInfoRequest;
import com.zimbra.soap.account.message.GetAccountInfoResponse;
import com.zimbra.soap.account.message.GetInfoRequest;
import com.zimbra.soap.account.message.GetInfoResponse;
import com.zimbra.soap.account.message.GetPrefsRequest;
import com.zimbra.soap.account.message.GetPrefsResponse;
import com.zimbra.soap.account.message.ModifyPrefsRequest;
import com.zimbra.soap.account.type.AuthToken;
import com.zimbra.soap.account.type.Pref;
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
     * Get prefs document handler.
     */
    private final GetPrefs prefsHandler;

    /**
     * Modify perfs document handler.
     */
    private final ModifyPrefs modifyPrefsHandler;

    /**
     * GetInfo document handler.
     */
    private final GetInfo infoHandler;
    
    /**
     * Change password document handler.
     */
    private final ChangePassword changePasswordHandler;

    /**
     * Creates an instance with default document handlers.
     */
    public ZXMLAccountRepository() {
        this(new GetAccountInfo(), new EndSession(),
            new GetPrefs(), new ModifyPrefs(), new GetInfo(), new ChangePassword());
    }

    /**
     * Creates an instance with specified handlers.
     *
     * @param accountInfoHandler The getAccountInfo handler
     * @param endSessionHandler The endSession handler
     * @param prefsHandler The prefs handler
     * @param modifyPrefsHandler The pref mutation handler
     * @param infoHandler Handler for GetInfo
     * @param changePasswordHandler Handler for change password
     */
    public ZXMLAccountRepository(GetAccountInfo accountInfoHandler, EndSession endSessionHandler,
        GetPrefs prefsHandler, ModifyPrefs modifyPrefsHandler, GetInfo infoHandler, ChangePassword changePasswordHandler) {
        super();
        this.accountInfoHandler = accountInfoHandler;
        this.endSessionHandler = endSessionHandler;
        this.prefsHandler = prefsHandler;
        this.modifyPrefsHandler = modifyPrefsHandler;
        this.infoHandler = infoHandler;
        this.changePasswordHandler = changePasswordHandler;
    }

    /**
     * Retrieves info.
     *
     * @param rctxt The request context
     * @param sections Comma separated list of sections to return information about
     * @param rights Comma separated list of rights to return information about
     * @return The GetInfoResponse as per the request
     * @throws ServiceException If there are issues executing the document
     */
    public GetInfoResponse info(RequestContext rctxt, String sections, String rights) throws ServiceException{
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final GetInfoRequest request = new GetInfoRequest();
        if (!StringUtils.isEmpty(sections)) {
            request.setSections(sections);
        }
        if (!StringUtils.isEmpty(rights)) {
            request.setRights(rights);
        }
        final Element response = XMLDocumentUtilities.executeDocument(
                infoHandler,
                zsc,
                XMLDocumentUtilities.toElement(request),
                rctxt);
        GetInfoResponse resp = null;
        if (response != null) {
            resp = XMLDocumentUtilities.fromElement(response,
                GetInfoResponse.class);
        }
        return resp;
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
                XMLDocumentUtilities.toElement(request),
                rctxt);
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
     * @param sessionId The id of a specific session to end
     * @param clearCookies Denotes whether to clear cookies for the requester
     * @throws ServiceException If there are issues executing the document
     */
    public void accountEndSession(RequestContext rctxt, String sessionId, Boolean clearCookies) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final EndSessionRequest request = new EndSessionRequest();
        request.setSessionId(sessionId);
        request.setLogOff(clearCookies);
        final Element response = XMLDocumentUtilities.executeDocument(
                endSessionHandler,
                zsc,
                XMLDocumentUtilities.toElement(request),
                rctxt);
        if (response == null) {
            throw ServiceException.FAILURE("EndSessionRequest failed", null);
        }
    }

    /**
     * Get prefs for the account.
     *
     * @param context The request context
     * @param prefs A list of pref names to get
     * @return A list of Pref objects
     * @throws ServiceException If there are issues executing the document
     */
    public List<Pref> prefs(RequestContext rctxt, List<Pref> prefs) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final GetPrefsRequest request = new GetPrefsRequest();
        request.setPref(prefs);
        final Element response = XMLDocumentUtilities.executeDocument(
            prefsHandler,
            zsc,
            XMLDocumentUtilities.toElement(request),
            rctxt);
        List<Pref> responsePrefs = null;
        if (response != null) {
            final GetPrefsResponse prefsResponse = XMLDocumentUtilities.fromElement(response, GetPrefsResponse.class);
            responsePrefs = prefsResponse.getPref();
        }
        return responsePrefs;
    }

    /**
     * Modify prefs on the account.
     *
     * @param context The request context
     * @param prefs A list of Pref objects to set
     * @return A list of updated Pref objects
     * @throws ServiceException If there are issues executing the document
     */
    public List<Pref> prefsModify(RequestContext rctxt, List<GQLPrefInput> prefs) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final ModifyPrefsRequest request = new ModifyPrefsRequest();
        for(final GQLPrefInput p: prefs) {
            final Pref pref = new Pref();
            pref.setName(p.getName());
            pref.setValue(p.getValue());
            request.addPref(pref);
        }
        final Element response = XMLDocumentUtilities.executeDocument(
            modifyPrefsHandler,
            zsc,
            XMLDocumentUtilities.toElement(request),
            rctxt);
        List<Pref> responsePrefs = request.getPrefs();
        if (response != null && !request.getPrefs().isEmpty()) {
            responsePrefs  = this.prefs(rctxt, responsePrefs);
        }
        return responsePrefs;
    }


    /**
     * 
     * @param acctSelector The account for which password is changed
     * @param oldPassword old Password
     * @param newPassword  new password
     * @param virtualHost  virtualHost
     * @param rctxt The request context
     * @return Change password response
     * @throws ServiceException If there are issues executing the document
     */
    public ChangePasswordResponse changePassword(AccountSelector acctSelector, String oldPassword, 
        String newPassword, String virtualHost, RequestContext rctxt) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final ChangePasswordRequest request = new ChangePasswordRequest();
        request.setOldPassword(oldPassword);
        request.setPassword(newPassword);
        request.setAccount(acctSelector);
        request.setVirtualHost(virtualHost);
        final Element response = XMLDocumentUtilities.executeDocument(
                changePasswordHandler,
                zsc,
                XMLDocumentUtilities.toElement(request),
                rctxt);
        if (response == null) {
            throw ServiceException.FAILURE("ChangePasswordRequest failed", null);
        }
        ChangePasswordResponse resp = XMLDocumentUtilities.fromElement(response,
            ChangePasswordResponse.class);
        return resp;
    }

}
