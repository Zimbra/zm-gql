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

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.AccountConstants;
import com.zimbra.common.soap.Element;
import com.zimbra.cs.mailbox.Mailbox;
import com.zimbra.cs.mailbox.Metadata;
import com.zimbra.cs.mailbox.OperationContext;
import com.zimbra.cs.service.account.ChangePassword;
import com.zimbra.cs.service.account.CreateSignature;
import com.zimbra.cs.service.account.DeleteSignature;
import com.zimbra.cs.service.account.EndSession;
import com.zimbra.cs.service.account.GetAccountInfo;
import com.zimbra.cs.service.account.GetInfo;
import com.zimbra.cs.service.account.GetPrefs;
import com.zimbra.cs.service.account.GetSignatures;
import com.zimbra.cs.service.account.GetWhiteBlackList;
import com.zimbra.cs.service.account.ModifyPrefs;
import com.zimbra.cs.service.account.ModifySignature;
import com.zimbra.cs.service.account.ModifyWhiteBlackList;
import com.zimbra.cs.service.common.SoapGqlCommonService;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.models.inputs.GQLDiscoverRightsInput;
import com.zimbra.graphql.models.inputs.GQLPrefInput;
import com.zimbra.graphql.models.outputs.AccountInfo;
import com.zimbra.graphql.models.outputs.GQLDiscoverRightsInfo;
import com.zimbra.graphql.models.outputs.GQLMailboxMetadata;
import com.zimbra.graphql.models.outputs.GQLMailboxMetadataKeyValue;
import com.zimbra.graphql.models.outputs.GQLWhiteBlackListResponse;
import com.zimbra.graphql.repositories.IRepository;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.HandlerManager;
import com.zimbra.graphql.utilities.XMLDocumentUtilities;
import com.zimbra.soap.DocumentHandler;
import com.zimbra.soap.ZimbraSoapContext;
import com.zimbra.soap.account.message.ChangePasswordRequest;
import com.zimbra.soap.account.message.ChangePasswordResponse;
import com.zimbra.soap.account.message.CreateSignatureRequest;
import com.zimbra.soap.account.message.CreateSignatureResponse;
import com.zimbra.soap.account.message.DeleteSignatureRequest;
import com.zimbra.soap.account.message.EndSessionRequest;
import com.zimbra.soap.account.message.GetAccountInfoRequest;
import com.zimbra.soap.account.message.GetAccountInfoResponse;
import com.zimbra.soap.account.message.GetInfoRequest;
import com.zimbra.soap.account.message.GetInfoResponse;
import com.zimbra.soap.account.message.GetPrefsRequest;
import com.zimbra.soap.account.message.GetPrefsResponse;
import com.zimbra.soap.account.message.GetSignaturesRequest;
import com.zimbra.soap.account.message.GetSignaturesResponse;
import com.zimbra.soap.account.message.GetWhiteBlackListRequest;
import com.zimbra.soap.account.message.GetWhiteBlackListResponse;
import com.zimbra.soap.account.message.ModifyPrefsRequest;
import com.zimbra.soap.account.message.ModifySignatureRequest;
import com.zimbra.soap.account.message.ModifyWhiteBlackListRequest;
import com.zimbra.soap.account.type.NameId;
import com.zimbra.soap.account.type.Pref;
import com.zimbra.soap.account.type.Signature;
import com.zimbra.soap.type.AccountBy;
import com.zimbra.soap.type.AccountSelector;
import com.zimbra.soap.type.OpValue;

import io.leangen.graphql.annotations.GraphQLNonNull;

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
     * Change password document handler.
     */
    private final ChangePassword changePasswordHandler;

    /**
     * GetWhiteBlackList document handler.
     */
    private final GetWhiteBlackList getWhiteBlackListHandler;

    /**
     * ModifyWhiteBlackList document handler.
     */
    private final ModifyWhiteBlackList modifyWhiteBlackListHandler;

    /**
     * GetSignatures document handler.
     */
    private final GetSignatures getSignaturesHandler;

    /**
     * CreateSignature document handler.
     */
    private final CreateSignature createSignatureHandler;

    /**
     * ModifySignature document handler.
     */
    private final ModifySignature modifySignatureHandler;

    /**
     * DeleteSignature document handler.
     */
    private final DeleteSignature deleteSignatureHandler;

    /**
     * Creates an instance with default document handlers.
     */
    public ZXMLAccountRepository() {
        this(new GetAccountInfo(), new EndSession(),
            new GetPrefs(), new ModifyPrefs(), new GetInfo(), new ChangePassword(),
            new GetWhiteBlackList(), new ModifyWhiteBlackList(), new GetSignatures(),
            new CreateSignature(), new ModifySignature(), new DeleteSignature());
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
     * @param getWhiteBlackListHandler The whiteblack list handler
     * @param modifyWhiteBlackListHandler The whiteblack list mutation handler
     * @param getSignaturesHandler Handler for retrieving signatures
     * @param createSignatureHandler Handler for creating signatures
     * @param modifySignatureHandler Handler for modifying signatures
     * @param deleteSignatureHandler Handler for deleting signatures
     */
    public ZXMLAccountRepository(GetAccountInfo accountInfoHandler, EndSession endSessionHandler,
         GetPrefs prefsHandler, ModifyPrefs modifyPrefsHandler, GetInfo infoHandler, ChangePassword changePasswordHandler,
         GetWhiteBlackList getWhiteBlackListHandler, ModifyWhiteBlackList modifyWhiteBlackListHandler,
         GetSignatures getSignaturesHandler, CreateSignature createSignatureHandler,
         ModifySignature modifySignatureHandler, DeleteSignature deleteSignatureHandler) {
        super();
        this.accountInfoHandler = accountInfoHandler;
        this.endSessionHandler = endSessionHandler;
        this.prefsHandler = prefsHandler;
        this.modifyPrefsHandler = modifyPrefsHandler;
        HandlerManager.registerHandler(AccountConstants.GET_INFO_REQUEST, infoHandler);
        this.changePasswordHandler = changePasswordHandler;
        this.getWhiteBlackListHandler = getWhiteBlackListHandler;
        this.modifyWhiteBlackListHandler = modifyWhiteBlackListHandler;
        this.getSignaturesHandler = getSignaturesHandler;
        this.createSignatureHandler = createSignatureHandler;
        this.modifySignatureHandler = modifySignatureHandler;
        this.deleteSignatureHandler = deleteSignatureHandler;
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
                HandlerManager.getHandler(AccountConstants.GET_INFO_REQUEST),
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
    public void accountEndSession(RequestContext rctxt, String sessionId, Boolean clearCookies,
        Boolean clearAllSoapSessions, Boolean excludeCurrentSession) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final EndSessionRequest request = new EndSessionRequest();
        request.setSessionId(sessionId);
        request.setLogOff(clearCookies);
        request.setClearAllSoapSessions(clearAllSoapSessions);
        request.setExcludeCurrentSession(excludeCurrentSession);
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
     * @param rctxt The request context
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
     * @param rctxt The request context
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
     * Modifies the selected account password.
     * @param rctxt The request context
     * @param acctSelector The account for which password is changed
     * @param oldPassword old Password
     * @param newPassword new password
     * @param virtualHost virtualHost
     *
     * @return Change password response
     * @throws ServiceException If there are issues executing the document
     */
    public ChangePasswordResponse changePassword(RequestContext rctxt, AccountSelector acctSelector,
        String oldPassword, String newPassword, String virtualHost) throws ServiceException {
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
        final ChangePasswordResponse resp = XMLDocumentUtilities.fromElement(response,
            ChangePasswordResponse.class);
        return resp;
    }

    /**
     * Retrieves white and black list entries.
     *
     * @param context The request context
     * @return White and black list entries
     * @throws ServiceException If there are issues executing the document
     */
    public GQLWhiteBlackListResponse whiteBlackList(RequestContext rctxt) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final GetWhiteBlackListRequest request = new GetWhiteBlackListRequest();
        final Element response = XMLDocumentUtilities.executeDocument(
            getWhiteBlackListHandler,
            zsc,
            XMLDocumentUtilities.toElement(request),
            rctxt);
        final GQLWhiteBlackListResponse gqlResponse = new GQLWhiteBlackListResponse();
        if (response != null) {
            final GetWhiteBlackListResponse lists = XMLDocumentUtilities
                .fromElement(response, GetWhiteBlackListResponse.class);
            gqlResponse.setWhiteListEntries(lists.getWhiteListEntries());
            gqlResponse.setBlackListEntries(lists.getBlackListEntries());
        }
        return gqlResponse;
    }

    /**
     * Modify white and black list entries.<br>
     * If no operation is present in a list, it means to remove all addresses in the list.
     *
     * @param context The request context
     * @param whiteListEntries Whitelist entry operations
     * @param blackListEntries Blacklist entry operations
     * @return True if modify was successful
     * @throws ServiceException If there are issues executing the document
     */
    public Boolean whiteBlackListModify(RequestContext rctxt, List<OpValue> whiteListEntries,
        List<OpValue> blackListEntries) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final ModifyWhiteBlackListRequest request = new ModifyWhiteBlackListRequest();
        request.setWhiteListEntries(whiteListEntries);
        request.setBlackListEntries(blackListEntries);
        XMLDocumentUtilities.executeDocument(
            modifyWhiteBlackListHandler,
            zsc,
            XMLDocumentUtilities.toElement(request),
            rctxt);
        return true;
    }

    /**
     * Retrieve a list of signatures associated with the requester.
     *
     * @param context The request context
     * @return A list of signatures
     * @throws ServiceException If there are issues executing the document
     */
    public List<Signature> signatures(RequestContext rctxt) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final GetSignaturesRequest request = new GetSignaturesRequest();
        final Element response = XMLDocumentUtilities.executeDocument(
            getSignaturesHandler,
            zsc,
            XMLDocumentUtilities.toElement(request),
            rctxt);
        final GetSignaturesResponse signatureResponse = XMLDocumentUtilities.fromElement(
            response,
            GetSignaturesResponse.class);
        if (signatureResponse != null) {
            return signatureResponse.getSignatures();
        }
        return Collections.emptyList();
    }

    /**
     * Create a signature.
     *
     * @param context The request context
     * @param signature The info of the signature to create
     * @return The name id of the created signature
     * @throws ServiceException If there are issues executing the document
     */
    public NameId signatureCreate(RequestContext rctxt, Signature signature) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final CreateSignatureRequest request = new CreateSignatureRequest(signature);
        final Element response = XMLDocumentUtilities.executeDocument(
            createSignatureHandler,
            zsc,
            XMLDocumentUtilities.toElement(request),
            rctxt);
        final CreateSignatureResponse signatureResponse = XMLDocumentUtilities.fromElement(
            response,
            CreateSignatureResponse.class);
        return signatureResponse.getSignature();
    }

    /**
     * Modifies a signature with given input.
     *
     * @param context The request context
     * @param id The id of the signature to modify
     * @param signature The signature info to modify
     * @return True if there are no issues modifying the signature
     * @throws ServiceException If there are issues executing the document
     */
    public Boolean signatureModify(RequestContext rctxt, String id, Signature signature) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        signature.setId(id);
        final ModifySignatureRequest request = new ModifySignatureRequest(signature);
        XMLDocumentUtilities.executeDocument(
            modifySignatureHandler,
            zsc,
            XMLDocumentUtilities.toElement(request),
            rctxt);
        return true;
    }

    /**
     * Deletes a specified signature.
     *
     * @param context The request context
     * @param signature The info of the signature to create
     * @return True if there are no issues deleting the signature
     * @throws ServiceException If there are issues executing the document
     */
    public Boolean signatureDelete(RequestContext rctxt, NameId identifier) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final DeleteSignatureRequest request = new DeleteSignatureRequest(identifier);
        XMLDocumentUtilities.executeDocument(
            deleteSignatureHandler,
            zsc,
            XMLDocumentUtilities.toElement(request),
            rctxt);
        return true;
    }

    private Metadata getMetadata(RequestContext rctxt, String section) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        Mailbox mbox = DocumentHandler.getRequestedMailbox(zsc);
        OperationContext octxt = DocumentHandler.getOperationContext(zsc, (Map<String, Object>)null);
        return mbox.getConfig(octxt, section);
    }

    @SuppressWarnings("unchecked")
    public GQLMailboxMetadata mailboxMetaData(RequestContext rctxt, String section) throws ServiceException {
        Metadata metadata = getMetadata(rctxt, section);

        GQLMailboxMetadata gmm = new GQLMailboxMetadata();
        gmm.setSection(section);
        if (metadata != null ) {
            Map<String, Object> metaMap = (Map<String, Object>) metadata.asMap();
            gmm.setMetadata(metaMap);
        }
        return gmm;
    }

    @SuppressWarnings("unchecked")
    public GQLMailboxMetadataKeyValue mailboxMetaDataForKey(RequestContext rctxt, String section, String key) throws ServiceException {
        Metadata metadata = getMetadata(rctxt, section);

        GQLMailboxMetadataKeyValue gmmkv = new GQLMailboxMetadataKeyValue();
        gmmkv.setSection(section);
        if (metadata != null ) {
            Map<String, Object> metaMap = (Map<String, Object>) metadata.asMap();
            gmmkv.setKey(key);
            gmmkv.setValue(metaMap.get(key));
        }
        return gmmkv;
    }

    public GQLDiscoverRightsInfo discoverRights(RequestContext rctxt, @GraphQLNonNull GQLDiscoverRightsInput input) throws ServiceException {
        GQLDiscoverRightsInfo dri = new GQLDiscoverRightsInfo();
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        SoapGqlCommonService sgcs = new SoapGqlCommonService(zsc);
        sgcs.discoverRights(input.getRights(), true);
        return dri;
    }
}
