/*
 * ***** BEGIN LICENSE BLOCK *****
 * Zimbra GraphQL Extension
 * Copyright (C) 2018, 2019 Synacor, Inc.
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
import java.util.List;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.Element;
import com.zimbra.common.soap.MailConstants;
import com.zimbra.cs.service.account.GetShareInfo;
import com.zimbra.cs.service.mail.CreateFolder;
import com.zimbra.cs.service.mail.CreateMountpoint;
import com.zimbra.cs.service.mail.CreateSearchFolder;
import com.zimbra.cs.service.mail.FolderAction;
import com.zimbra.cs.service.mail.GetFolder;
import com.zimbra.cs.service.mail.GetSearchFolder;
import com.zimbra.cs.service.mail.ItemAction;
import com.zimbra.cs.service.mail.ModifySearchFolder;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.models.inputs.GQLFolderSelector;
import com.zimbra.graphql.models.inputs.GQLOwnerSelector;
import com.zimbra.graphql.repositories.IRepository;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.GQLUtilities;
import com.zimbra.graphql.utilities.XMLDocumentUtilities;
import com.zimbra.soap.ZimbraSoapContext;
import com.zimbra.soap.account.message.GetShareInfoRequest;
import com.zimbra.soap.account.message.GetShareInfoResponse;
import com.zimbra.soap.mail.message.CreateFolderRequest;
import com.zimbra.soap.mail.message.CreateMountpointRequest;
import com.zimbra.soap.mail.message.CreateSearchFolderRequest;
import com.zimbra.soap.mail.message.FolderActionRequest;
import com.zimbra.soap.mail.message.GetFolderRequest;
import com.zimbra.soap.mail.message.GetSearchFolderRequest;
import com.zimbra.soap.mail.message.ModifySearchFolderRequest;
import com.zimbra.soap.mail.type.Folder;
import com.zimbra.soap.mail.type.FolderActionResult;
import com.zimbra.soap.mail.type.FolderActionSelector;
import com.zimbra.soap.mail.type.GetFolderSpec;
import com.zimbra.soap.mail.type.ModifySearchFolderSpec;
import com.zimbra.soap.mail.type.Mountpoint;
import com.zimbra.soap.mail.type.NewFolderSpec;
import com.zimbra.soap.mail.type.NewMountpointSpec;
import com.zimbra.soap.mail.type.NewSearchFolderSpec;
import com.zimbra.soap.mail.type.SearchFolder;
import com.zimbra.soap.type.AccountSelector;
import com.zimbra.soap.type.GranteeChooser;
import com.zimbra.soap.type.ShareInfo;

/**
 * The ZXMLFolderRepository class.<br>
 * Contains XML document based data access methods for folders.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.repositories.impl
 */
public class ZXMLFolderRepository extends ZXMLItemRepository implements IRepository {

    /**
     * The createFolder document handler.
     */
    protected final CreateFolder createFolderHandler;

    /**
     * The createSearchFolder document handler.
     */
    protected final CreateSearchFolder createSearchFolderHandler;

    /**
     * The getFolder document handler.
     */
    protected final GetFolder getFolderHandler;

    /**
     * The getSearchFolder document handler.
     */
    protected final GetSearchFolder getSearchFolderHandler;

    /**
     * The modifySearchFolder document handler.
     */
    protected final ModifySearchFolder modifySearchFolderHandler;

    /**
     * The createMountpoint document handler.
     */
    protected final CreateMountpoint createMountpointHandler;

    /**
     * The getShareInfo document handler.
     */
    protected final GetShareInfo getShareInfoHandler;

    /**
     * Creates an instance with default document handlers.
     */
    public ZXMLFolderRepository() {
        this(new FolderAction(), new CreateFolder(), new GetFolder(), new CreateSearchFolder(),
            new GetSearchFolder(), new ModifySearchFolder(), new CreateMountpoint(), new GetShareInfo());
    }

    /**
     * Creates an instance with specified handlers.
     *
     * @param actionHandler The item action handler
     * @param createHandler The create folder handler
     * @param getHandler The get folder handler
     * @param createSearchFolderHandler The create search folder handler
     * @param getSearchFolderHandler The get search folder handler
     * @param modifySearchFolderHandler The modify search folder handler
     * @param createMountpointHandler The modify search folder handler
     */
    public ZXMLFolderRepository(ItemAction actionHandler, CreateFolder createHandler,
        GetFolder getHandler, CreateSearchFolder createSearchFolderHandler,
        GetSearchFolder getSearchFolderHandler, ModifySearchFolder modifySearchFolderHandler,
        CreateMountpoint createMountpointHandler, GetShareInfo getShareInfoHandler) {
        super(actionHandler);
        this.createFolderHandler = createHandler;
        this.getFolderHandler = getHandler;
        this.createSearchFolderHandler = createSearchFolderHandler;
        this.getSearchFolderHandler = getSearchFolderHandler;
        this.modifySearchFolderHandler = modifySearchFolderHandler;
        this.createMountpointHandler = createMountpointHandler;
        this.getShareInfoHandler = getShareInfoHandler;
    }

    /**
     * Retrieves a folder by given properties.
     *
     * @param rctxt The request context
     * @param visible Whether to include all visible subfolders
     * @param needGranteeName Whether to return the grantee name
     * @param view Filter results by folder view
     * @param depth Filter subfolder tree depth
     * @param traverseMountpoints Whether or not to traverse one level of mountpoints
     * @param getFolder The primary folder identifiers
     * @return Fetch folder results
     * @throws ServiceException If there are issues executing the document
     */
    public Folder folder(RequestContext rctxt, Boolean visible,
        Boolean needGranteeName, Folder.View view, Integer depth, Boolean traverseMountpoints,
        GetFolderSpec getFolder) throws ServiceException {
        // get auth context
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        // map the request params
        final GetFolderRequest req = new GetFolderRequest();
        req.setFolder(getFolder);
        req.setNeedGranteeName(needGranteeName);
        req.setTraverseMountpoints(traverseMountpoints);
        req.setTreeDepth(depth);
        if (view != null) {
            req.setViewConstraint(view.name());
        }
        req.setVisible(visible);
        // execute
        final Element response = XMLDocumentUtilities.executeDocument(
            getFolderHandler,
            zsc,
            XMLDocumentUtilities.toElement(req),
            rctxt);
        Folder folder = null;
        if (response != null) {
            folder = XMLDocumentUtilities.fromElement(response.getElement(MailConstants.E_FOLDER),
                Folder.class);
        }
        return folder;
    }

    /**
     * Create a folder with given properties.
     *
     * @param rctxt The request context
     * @param folderToCreate New folder properties
     * @return The newly created folder
     * @throws ServiceException If there are issues executing the document
     */
    public Folder createFolder(RequestContext rctxt,
        NewFolderSpec folderToCreate) throws ServiceException {
        // get auth context
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        // execute
        final CreateFolderRequest req = new CreateFolderRequest(folderToCreate);
        final Element response = XMLDocumentUtilities.executeDocument(
            createFolderHandler,
            zsc,
            XMLDocumentUtilities.toElement(req),
            rctxt);
        Folder zCreatedFolder = null;
        if (response != null) {
            zCreatedFolder = XMLDocumentUtilities
                .fromElement(response.getElement(MailConstants.E_FOLDER), Folder.class);
        }
        return zCreatedFolder;
    }

    /**
     * Performs a folder action with given properties.
     *
     * @param rctxt The request context
     * @param input The properties
     * @return Action result
     * @throws ServiceException If there are issues executing the document
     */
    public FolderActionResult action(RequestContext rctxt, FolderActionSelector input)
        throws ServiceException {
        final FolderActionRequest req = new FolderActionRequest(input);
        final Element response = super.action(rctxt, req);
        FolderActionResult result = null;
        if (response != null) {
            result = XMLDocumentUtilities.fromElement(response.getElement(MailConstants.E_ACTION),
                FolderActionResult.class);
        }
        return result;
    }

    /**
     * Get search folders with given properties.
     *
     * @param rctxt The request context
     * @return A list of search folders
     * @throws ServiceException If there are issues executing the document
     */
    public List<SearchFolder> searchFolderGet(RequestContext rctxt)
            throws ServiceException {
        // get auth context
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        // execute
        final GetSearchFolderRequest req = new GetSearchFolderRequest();
        final Element response = XMLDocumentUtilities.executeDocument(
            getSearchFolderHandler,
            zsc,
            XMLDocumentUtilities.toElement(req),
            rctxt);
        final List<SearchFolder> searchFolders = new ArrayList<SearchFolder>();
        if (response != null && response.hasChildren()) {
            final List<Element> searches = response.listElements(MailConstants.E_SEARCH);
            for (final Element search : searches) {
                final SearchFolder sf = XMLDocumentUtilities.fromElement(search, SearchFolder.class);
                searchFolders.add(sf);
            }
        }
        return searchFolders;
    }

    /**
     * Create a search folder with given properties.
     *
     * @param rctxt The request context
     * @param searchFolder New search folder spec
     * @return The newly created folder
     * @throws ServiceException If there are issues executing the document
     */
    public SearchFolder searchFolderCreate(RequestContext rctxt, NewSearchFolderSpec searchFolder)
            throws ServiceException {
        // get auth context
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        // execute
        final CreateSearchFolderRequest req = new CreateSearchFolderRequest(searchFolder);
        final Element response = XMLDocumentUtilities.executeDocument(
            createSearchFolderHandler,
            zsc,
            XMLDocumentUtilities.toElement(req),
            rctxt);
        SearchFolder zCreatedSearchFolder = null;
        if (response != null) {
            zCreatedSearchFolder = XMLDocumentUtilities.fromElement(response.getElement(MailConstants.E_SEARCH),
                    SearchFolder.class);
        }
        return zCreatedSearchFolder;
    }

    /**
     * Create a search folder with given properties.
     *
     * @param rctxt The request context
     * @param searchFolder Modify search folder spec
     * @return The modified search folder
     * @throws ServiceException If there are issues executing the document
     */
    public SearchFolder searchFolderModify(RequestContext rctxt, ModifySearchFolderSpec searchFolder)
            throws ServiceException {
        // get auth context
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        // execute
        final ModifySearchFolderRequest req = new ModifySearchFolderRequest(searchFolder);
        final Element response = XMLDocumentUtilities.executeDocument(
            modifySearchFolderHandler,
            zsc,
            XMLDocumentUtilities.toElement(req),
            rctxt);
        SearchFolder modifiedSearchFolder = null;
        if (response != null) {
            modifiedSearchFolder = XMLDocumentUtilities.fromElement(response.getElement(MailConstants.E_SEARCH),
                    SearchFolder.class);
        }
        return modifiedSearchFolder;
    }

    /**
     * Create mountpoint with given properties
     *
     * @param rctxt
     *            The request context
     * @param name
     *            mountpoint name
     * @param folderId
     *            parent folder id
     * @param ownerSelector
     *            select owner of the linked-to resource by id or email address
     * @param owner
     *            id or email address of the owner
     * @param remoteFolderSeletor
     *            select remote folder by id or path
     * @param remoteFolder
     *            remote folder id or path
     * @param defaultView
     *            Default type for the folder, used by web client to decide
     *            which view to use. Possible values are
     *            conversation|message|contact|etc
     * @param flags
     *            mountpoint flags
     * @param color
     *            color numeric, range 0-127, defaults to 0 if not present,
     *            client can display only 0-7
     * @param rgb
     *            RGB color in format #rrggbb where r,g and b are hex digits
     * @param url
     *            URL (RSS, iCal, etc.) this folder syncs its contents to
     * @param fetchIfExists
     *            If set, the server will fetch the folder if it already exists
     *            rather than throwing mail.ALREADY_EXISTS exception
     * @param reminderEnabled
     *            If set, client should display reminders for shared
     *            appointments/tasks
     * @return created Mountpoint
     * @throws ServiceException
     */
    public Mountpoint mountpointCreate(RequestContext rctxt, String name, String folderId,
            GQLOwnerSelector ownerSelector, String owner, GQLFolderSelector remoteFolderSeletor, String remoteFolder,
            String defaultView, String flags, Byte color, String rgb, String url, Boolean fetchIfExists,
            Boolean reminderEnabled) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final NewMountpointSpec spec = new NewMountpointSpec(name);
        spec.setFolderId(folderId);
        if (ownerSelector == GQLOwnerSelector.ID) {
            spec.setOwnerId(owner);
        } else {
            spec.setOwnerName(owner);
        }
        if (remoteFolderSeletor == GQLFolderSelector.ID) {
            spec.setRemoteId(Integer.parseInt(remoteFolder));
        } else {
            spec.setPath(remoteFolder);
        }
        spec.setDefaultView(defaultView);
        spec.setFlags(flags);
        spec.setColor(color);
        spec.setRgb(rgb);
        spec.setUrl(url);
        spec.setFetchIfExists(fetchIfExists);
        spec.setReminderEnabled(reminderEnabled);
        final CreateMountpointRequest req = new CreateMountpointRequest(spec);
        final Element response = XMLDocumentUtilities.executeDocument(createMountpointHandler, zsc,
                XMLDocumentUtilities.toElement(req), rctxt);
        Mountpoint mountpoint = null;
        if (response != null) {
            mountpoint = XMLDocumentUtilities.fromElement(response.getElement(MailConstants.E_MOUNT),
                    Mountpoint.class);
        }
        return mountpoint;
    }

    /**
     * Get information about folders shared with the authenticated user.
     *
     * @param rctxt The request context
     * @param internal Flags that have been proxied to this server because the specified "owner account" is homed here. Do not proxy in this case. (Used internally by ZCS)
     * @param includeSelf Flag whether own shares should be included:
     *        0:if shares owned by the requested account should not be included in the response
     *        1:(default) include shares owned by the requested account
     * @param grantee Filter by the specified grantee type
     * @param owner Specifies the owner of the share
     * @return List of shares
     * @throws ServiceException
     */
    public List<ShareInfo> shareInfo(RequestContext rctxt, Boolean internal, Boolean includeSelf,
            GranteeChooser grantee, AccountSelector owner) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final GetShareInfoRequest req = new GetShareInfoRequest();
        req.setGrantee(grantee);
        req.setIncludeSelf(includeSelf);
        req.setInternal(internal);
        req.setOwner(owner);
        final Element response = XMLDocumentUtilities.executeDocument(getShareInfoHandler, zsc,
                XMLDocumentUtilities.toElement(req), rctxt);
        List<ShareInfo> shares = null;
        if (response != null) {
            final GetShareInfoResponse resp = XMLDocumentUtilities.fromElement(response, GetShareInfoResponse.class);
            shares = resp.getShares();
        }
        return GQLUtilities.emptyListIfNull(shares);
    }
}