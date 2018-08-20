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
import java.util.List;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.Element;
import com.zimbra.common.soap.MailConstants;
import com.zimbra.cs.service.mail.CreateFolder;
import com.zimbra.cs.service.mail.CreateSearchFolder;
import com.zimbra.cs.service.mail.FolderAction;
import com.zimbra.cs.service.mail.GetFolder;
import com.zimbra.cs.service.mail.GetSearchFolder;
import com.zimbra.cs.service.mail.ItemAction;
import com.zimbra.cs.service.mail.ModifySearchFolder;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.repositories.IRepository;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.XMLDocumentUtilities;
import com.zimbra.soap.ZimbraSoapContext;
import com.zimbra.soap.mail.message.CreateFolderRequest;
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
import com.zimbra.soap.mail.type.NewFolderSpec;
import com.zimbra.soap.mail.type.NewSearchFolderSpec;
import com.zimbra.soap.mail.type.SearchFolder;

/**
 * The ZXMLFolderRepository class.<br>
 * Contains XML document based data access methods for folders.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.repositories.impl
 * @copyright Copyright © 2018
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
     * Creates an instance with default document handlers.
     */
    public ZXMLFolderRepository() {
        this(new FolderAction(), new CreateFolder(), new GetFolder(), new CreateSearchFolder(),
            new GetSearchFolder(), new ModifySearchFolder());
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
     */
    public ZXMLFolderRepository(ItemAction actionHandler, CreateFolder createHandler,
        GetFolder getHandler, CreateSearchFolder createSearchFolderHandler,
        GetSearchFolder getSearchFolderHandler, ModifySearchFolder modifySearchFolderHandler) {
        super(actionHandler);
        this.createFolderHandler = createHandler;
        this.getFolderHandler = getHandler;
        this.createSearchFolderHandler = createSearchFolderHandler;
        this.getSearchFolderHandler = getSearchFolderHandler;
        this.modifySearchFolderHandler = modifySearchFolderHandler;
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
            XMLDocumentUtilities.toElement(req));
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
     * @param account The account to create the folder
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
            XMLDocumentUtilities.toElement(req));
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
            XMLDocumentUtilities.toElement(req));
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
            XMLDocumentUtilities.toElement(req));
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
            XMLDocumentUtilities.toElement(req));
        SearchFolder modifiedSearchFolder = null;
        if (response != null) {
            modifiedSearchFolder = XMLDocumentUtilities.fromElement(response.getElement(MailConstants.E_SEARCH),
                    SearchFolder.class);
        }
        return modifiedSearchFolder;
    }

}