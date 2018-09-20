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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.Element;
import com.zimbra.common.soap.MailConstants;
import com.zimbra.cs.service.account.EndSession;
import com.zimbra.cs.service.account.GetAccountInfo;
import com.zimbra.cs.service.account.GetPrefs;
import com.zimbra.cs.service.account.ModifyPrefs;
import com.zimbra.cs.service.mail.GetFilterRules;
import com.zimbra.cs.service.mail.ModifyFilterRules;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.models.inputs.GQLPrefInput;
import com.zimbra.graphql.models.outputs.AccountInfo;
import com.zimbra.graphql.repositories.IRepository;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.XMLDocumentUtilities;
import com.zimbra.soap.ZimbraSoapContext;
import com.zimbra.soap.account.message.EndSessionRequest;
import com.zimbra.soap.account.message.GetAccountInfoRequest;
import com.zimbra.soap.account.message.GetAccountInfoResponse;
import com.zimbra.soap.account.message.GetPrefsRequest;
import com.zimbra.soap.account.message.GetPrefsResponse;
import com.zimbra.soap.account.message.ModifyPrefsRequest;
import com.zimbra.soap.account.type.Pref;
import com.zimbra.soap.mail.message.GetFilterRulesRequest;
import com.zimbra.soap.mail.message.GetFilterRulesResponse;
import com.zimbra.soap.mail.message.GetSearchFolderRequest;
import com.zimbra.soap.mail.message.ModifyFilterRulesResponse;
import com.zimbra.soap.mail.type.FilterAction;
import com.zimbra.soap.mail.type.FilterRule;
import com.zimbra.soap.mail.type.SearchFolder;
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
     * The getFilterRules document handler.
     */
    protected final GetFilterRules getFilterRulesHandler;

    /**
     * The modifyFilterRules document handler.
     */
    protected final ModifyFilterRules modifyFilterRulesHandler;

    /**
     * Creates an instance with default document handlers.
     */
    public ZXMLAccountRepository() {
        this(new GetAccountInfo(), new EndSession(),
            new GetPrefs(), new ModifyPrefs(),
            new GetFilterRules(), new ModifyFilterRules());
    }

    /**
     * Creates an instance with specified handlers.
     *
     * @param accountInfoHandler The getAccountInfo handler
     * @param endSessionHandler The endSession handler
     * @param prefsHandler The prefs handler
     * @param modifyPrefsHandler The pref mutation handler
     * @param getFilterRulesHandler The filter rules handler
     * @param modifyFilterRulesHandler The filter rules mutation handler
     */
    public ZXMLAccountRepository(GetAccountInfo accountInfoHandler, EndSession endSessionHandler,
        GetPrefs prefsHandler, ModifyPrefs modifyPrefsHandler,
        GetFilterRules getFilterRulesHandler, ModifyFilterRules modifyFilterRulesHandler) {
        super();
        this.accountInfoHandler = accountInfoHandler;
        this.endSessionHandler = endSessionHandler;
        this.prefsHandler = prefsHandler;
        this.modifyPrefsHandler = modifyPrefsHandler;
        this.getFilterRulesHandler = getFilterRulesHandler;
        this.modifyFilterRulesHandler = modifyFilterRulesHandler;
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
            XMLDocumentUtilities.toElement(request));
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
            XMLDocumentUtilities.toElement(request));
        List<Pref> responsePrefs = request.getPrefs();
        if (response != null && !request.getPrefs().isEmpty()) {
            responsePrefs  = this.prefs(rctxt, responsePrefs);
        }
        return responsePrefs;
    }

    /**
     * Get filter rules.
     *
     * @param rctxt The request context
     * @return GetFilterRulesResponse The response object
     * @throws ServiceException If there are issues executing the document
     */
    public List<FilterRule> filterRules(RequestContext rctxt) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final GetFilterRulesRequest req = new GetFilterRulesRequest();
        final Element response = XMLDocumentUtilities.executeDocument(
            getFilterRulesHandler,
            zsc,
            XMLDocumentUtilities.toElement(req));
        List<FilterRule> resp = null;
        if (response != null) {
            final GetFilterRulesResponse prefFilters = XMLDocumentUtilities.fromElement(response, GetFilterRulesResponse.class);
            resp = prefFilters.getFilterRules();
        }
        //return Collections.emptyList();
        return resp;
    }

    /**
     * Modify filter rules.
     *
     * @param rctxt The request context
     * @param modifyFilterRulesRequest A ModifyFilterRulesRequest object
     * @return ModifyFilterRulesResponse The response object
     * @throws ServiceException If there are issues executing the document
    
    public Boolean filterRulesModify(RequestContext rctxt,
        List<FilterRule >modifyFilterRulesRequest) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final Element response = XMLDocumentUtilities.executeDocument(
            modifyFilterRulesHandler,
            zsc,
            XMLDocumentUtilities.toElement(modifyFilterRulesRequest));
        ModifyFilterRulesResponse responseObject = null;
        if (response != null) {
            responseObject = XMLDocumentUtilities.fromElement(response, ModifyFilterRulesResponse.class);
            if(responseObject != null) {
                return true;
            }
        }
        return false;
    }
 */
}
