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
import com.zimbra.cs.account.Account;
import com.zimbra.cs.mailbox.OperationContext;
import com.zimbra.cs.service.mail.CreateFolder;
import com.zimbra.cs.service.mail.CreateSearchFolder;
import com.zimbra.cs.service.mail.FolderAction;
import com.zimbra.cs.service.mail.GetFolder;
import com.zimbra.cs.service.mail.GetSearchFolder;
import com.zimbra.cs.service.mail.ItemAction;
import com.zimbra.cs.service.mail.ModifySearchFolder;
import com.zimbra.graphql.repositories.IRepository;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.XMLDocumentUtilities;
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
     * The getFolder document handler.
     */
    protected final GetSearchFolder getSearchFolderHandler;

    /**
     * The getFolder document handler.
     */
    protected final ModifySearchFolder modifySearchFolderHandler;

    /**
     * Creates an instance with default document handlers.
     */
    public ZXMLFolderRepository() {
        super(new FolderAction());
        createFolderHandler = new CreateFolder();
        getFolderHandler = new GetFolder();
        createSearchFolderHandler = new CreateSearchFolder();
        getSearchFolderHandler = new GetSearchFolder();
        modifySearchFolderHandler = new ModifySearchFolder();
    }

    /**
     * Creates an instance with specified handlers.
     *
     * @param actionHandler The item action handler.
     * @param createHandler The create folder handler
     * @param getHandler The get folder handler
     * @param createSearchFolderHandler The create search folder handler
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
     * @param octxt The operation context
     * @param account The account to search for folder
     * @param visible Whether to include all visible subfolders
     * @param needGranteeName Whether to return the grantee name
     * @param view Filter results by folder view
     * @param depth Filter subfolder tree depth
     * @param traverseMountpoints Whether or not to traverse one level of mountpoints
     * @param getFolder The primary folder identifiers
     * @return Fetch reuslts
     * @throws ServiceException If there are issues executing the document
     */
    public Folder getFolder(OperationContext octxt, Account account, Boolean visible,
        Boolean needGranteeName, Folder.View view, Integer depth, Boolean traverseMountpoints,
        GetFolderSpec getFolder) throws ServiceException {
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
            XMLDocumentUtilities.toElement(req),
            GQLAuthUtilities.getZimbraSoapContext(octxt, account));
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
     * @param octxt The operation context
     * @param account The account to create the folder
     * @param folderToCreate New folder properties
     * @return The newly created folder
     * @throws ServiceException If there are issues executing the document
     */
    public Folder createFolder(OperationContext octxt, Account account,
        NewFolderSpec folderToCreate) throws ServiceException {
        // execute
        final CreateFolderRequest req = new CreateFolderRequest(folderToCreate);
        final Element response = XMLDocumentUtilities.executeDocument(
            createFolderHandler,
            XMLDocumentUtilities.toElement(req),
            GQLAuthUtilities.getZimbraSoapContext(octxt, account));
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
     * @param octxt The operation context
     * @param account The account to perform the action
     * @param input The properties
     * @return Action result
     * @throws ServiceException If there are issues executing the document
     */
    public FolderActionResult action(OperationContext octxt, Account account,
        FolderActionSelector input) throws ServiceException {
        final FolderActionRequest req = new FolderActionRequest(input);
        final Element response = super.action(octxt, account, req);
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
     * @param octxt The operation context
     * @param account The account to create the folder
     * @return The newly created folder
     * @throws ServiceException If there are issues executing the document
     */
    public List<SearchFolder> getSearchFolder(OperationContext octxt, Account account)
            throws ServiceException {
        // execute
        final GetSearchFolderRequest req = new GetSearchFolderRequest();
        final Element response = XMLDocumentUtilities.executeDocument(getSearchFolderHandler,
                XMLDocumentUtilities.toElement(req), GQLAuthUtilities.getZimbraSoapContext(octxt, account));
        List<SearchFolder> searchFolders = new ArrayList<SearchFolder>();
        if (response != null && response.hasChildren()) {
            List<Element> searches = response.listElements(MailConstants.E_SEARCH);
            for (Element search : searches) {
                SearchFolder sf = XMLDocumentUtilities.fromElement(search, SearchFolder.class);
                searchFolders.add(sf);
            }
        }
        return searchFolders;
    }

    /**
     * Create a search folder with given properties.
     *
     * @param octxt The operation context
     * @param account The account to create the folder
     * @param searchFolder New search folder spec
     * @return The newly created folder
     * @throws ServiceException If there are issues executing the document
     */
    public SearchFolder createSearchFolder(OperationContext octxt, Account account, NewSearchFolderSpec searchFolder)
            throws ServiceException {
        // execute
        final CreateSearchFolderRequest req = new CreateSearchFolderRequest(searchFolder);
        final Element response = XMLDocumentUtilities.executeDocument(createSearchFolderHandler,
                XMLDocumentUtilities.toElement(req), GQLAuthUtilities.getZimbraSoapContext(octxt, account));
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
     * @param octxt The operation context
     * @param account The account to create the folder
     * @param searchFolder New search folder spec
     * @return The newly created folder
     * @throws ServiceException If there are issues executing the document
     */
    public SearchFolder modifySearchFolder(OperationContext octxt, Account account, ModifySearchFolderSpec searchFolder)
            throws ServiceException {
        // execute
        final ModifySearchFolderRequest req = new ModifySearchFolderRequest(searchFolder);
        final Element response = XMLDocumentUtilities.executeDocument(modifySearchFolderHandler,
                XMLDocumentUtilities.toElement(req), GQLAuthUtilities.getZimbraSoapContext(octxt, account));
        SearchFolder modifiedSearchFolder = null;
        if (response != null) {
            modifiedSearchFolder = XMLDocumentUtilities.fromElement(response.getElement(MailConstants.E_SEARCH),
                    SearchFolder.class);
        }
        return modifiedSearchFolder;
    }

}