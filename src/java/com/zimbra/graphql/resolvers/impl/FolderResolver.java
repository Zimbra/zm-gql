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
package com.zimbra.graphql.resolvers.impl;

import java.util.List;

import com.zimbra.common.gql.GqlConstants;
import com.zimbra.common.service.ServiceException;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.models.inputs.GQLFolderSelector;
import com.zimbra.graphql.models.inputs.GQLOwnerSelector;
import com.zimbra.graphql.repositories.impl.ZXMLFolderRepository;
import com.zimbra.graphql.resolvers.IResolver;
import com.zimbra.soap.mail.type.Folder;
import com.zimbra.soap.mail.type.FolderActionResult;
import com.zimbra.soap.mail.type.FolderActionSelector;
import com.zimbra.soap.mail.type.GetFolderSpec;
import com.zimbra.soap.mail.type.ModifySearchFolderSpec;
import com.zimbra.soap.mail.type.Mountpoint;
import com.zimbra.soap.mail.type.NewFolderSpec;
import com.zimbra.soap.mail.type.NewSearchFolderSpec;
import com.zimbra.soap.mail.type.SearchFolder;
import com.zimbra.soap.type.AccountSelector;
import com.zimbra.soap.type.GranteeChooser;
import com.zimbra.soap.type.ShareInfo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLRootContext;

/**
 * The FolderResolver class.<br>
 * Contains folder schema resources.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.resolvers.impl
 * @copyright Copyright © 2018
 */
public class FolderResolver implements IResolver {

    protected ZXMLFolderRepository folderRepository = null;

    /**
     * Creates an instance with specified folder repository.
     *
     * @param folderRepository The folder repository
     */
    public FolderResolver(ZXMLFolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    @GraphQLQuery(description = "Retrieve a folder by given properties.")
    public Folder folder(@GraphQLArgument(name = "visible") Boolean visible,
        // default to false because the spec uses boolean
        @GraphQLArgument(name = "needGranteeName", defaultValue = "false") Boolean needGranteeName,
        @GraphQLArgument(name = "view") Folder.View view,
        @GraphQLArgument(name = "depth") Integer depth,
        @GraphQLArgument(name = "traverseMountpoints") Boolean traverseMountpoints,
        @GraphQLArgument(name = "getFolder") GetFolderSpec getFolder,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return folderRepository.folder(context, visible, needGranteeName, view, depth,
            traverseMountpoints, getFolder);
    }

    @GraphQLMutation(description = "Create a folder with given properties.")
    public Folder folderCreate(
        @GraphQLNonNull @GraphQLArgument(name = "folder") NewFolderSpec folder,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return folderRepository.createFolder(context, folder);
    }

    @GraphQLMutation(description = "Handles a folder action request.")
    public FolderActionResult folderAction(
        @GraphQLNonNull @GraphQLArgument(name = "input") FolderActionSelector input,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return folderRepository.action(context, input);
    }

    @GraphQLQuery(description = "Retrieve search folders for given user")
    public List<SearchFolder> searchFolderGet(@GraphQLRootContext RequestContext context) throws ServiceException {
        return folderRepository.searchFolderGet(context);
    }

    @GraphQLMutation(description = "Create a search folder with given properties.")
    public SearchFolder searchFolderCreate(
        @GraphQLNonNull @GraphQLArgument(name = "searchFolder") NewSearchFolderSpec searchFolder,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return folderRepository.searchFolderCreate(context, searchFolder);
    }

    @GraphQLMutation(description = "Modify existing search folder with given properties.")
    public SearchFolder searchFolderModify(
        @GraphQLNonNull @GraphQLArgument(name = "searchFolder") ModifySearchFolderSpec searchFolder,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return folderRepository.searchFolderModify(context, searchFolder);
    }

    @GraphQLMutation(description = "Create mountpoint with given properties.")
    public Mountpoint mountpointCreate(
        @GraphQLNonNull @GraphQLArgument(name = GqlConstants.NAME, description = "mountpoint name") String name,
        @GraphQLNonNull @GraphQLArgument(name = GqlConstants.PARENT_FOLDER_ID, description = "parent folder id") String parentFolderId,
        @GraphQLNonNull @GraphQLArgument(name = GqlConstants.OWNER_SELECTOR, description = "select owner of the linked-to resource by id or email address") GQLOwnerSelector ownerSelector,
        @GraphQLNonNull @GraphQLArgument(name = GqlConstants.OWNER, description = "id or email address of the owner") String owner,
        @GraphQLNonNull @GraphQLArgument(name = GqlConstants.REMOTE_FOLDER_SELECTOR, description = "select remote folder by id or path") GQLFolderSelector remoteFolderSeletor,
        @GraphQLNonNull @GraphQLArgument(name = GqlConstants.REMOTE_FOLDER, description = "remote folder id or path") String remoteFolder,
        @GraphQLArgument(name = GqlConstants.VIEW, description = "Default type for the folder, used by web client to decide which view to use. Possible values are conversation|message|contact|etc") String defaultView,
        @GraphQLArgument(name = GqlConstants.FLAGS, description = "Mountpoint flags") String flags,
        @GraphQLArgument(name = GqlConstants.COLOR, description = "color numeric, range 0-127, defaults to 0 if not present, client can display only 0-7") Byte color,
        @GraphQLArgument(name = GqlConstants.RGB, description = "RGB color in format #rrggbb where r,g and b are hex digits") String rgb,
        @GraphQLArgument(name = GqlConstants.URL, description = "URL (RSS, iCal, etc.) this folder syncs its contents to") String url,
        @GraphQLArgument(name = GqlConstants.FETCH_IF_EXISTS, description = "If set, the server will fetch the folder if it already exists rather than throwing mail.ALREADY_EXISTS exception") Boolean fetchIfExists,
        @GraphQLArgument(name = GqlConstants.REMINDER_ENABLED, description = "If set, client should display reminders for shared appointments/tasks") Boolean reminderEnabled,
        @GraphQLRootContext RequestContext context) throws ServiceException {
        return folderRepository.mountpointCreate(context, name, parentFolderId, ownerSelector, owner, remoteFolderSeletor, remoteFolder, defaultView, flags, color, rgb, url, fetchIfExists, reminderEnabled);
    }

    @GraphQLQuery(description = "Get information about folders shared with the authenticated user.  \n" +
            "* If *owner* is *not* specified the server will search the LDAP directory for published shares" + 
            " (zimbraSharedItem account attribute) accessible to the authed user.  \n" + 
            "* If *owner* *is* specified, the server will iterate through the owner's mailbox to discover all  \n" + 
            " shares applicable to the authed user, instead of looking at any of the published share info.  \n" + 
            "* All applicable shares will be returned, including any shares that are:  \n" + 
            "  * shared with the account directly \n" +
            "  * shared with any group(and parent groups) the account belongs (*is* supported) \n" + 
            "  * shared with the cos assigned to the account. (*is* supported) \n" + 
            "  * shared with the domain this account is in.(*is* supported) \n" + 
            "  * shared with all authed users (i.e. all Zimbra users) (*is* supported) \n" + 
            "  * shared with the public (*is* supported)")
    public List<ShareInfo> shareInfo(
            @GraphQLArgument(name = GqlConstants.INTERNAL, description = "Flags that have been proxied to this server because the specified \"owner account\" is homed here. Do not proxy in this case. (Used internally by ZCS)") Boolean internal,
            @GraphQLArgument(name = GqlConstants.INCLUDE_SELF, description = "Flag whether own shares should be included:\n" + 
                    "* 0:if shares owned by the requested account should not be included in the response\n" + 
                    "* 1:(default) include shares owned by the requested account") Boolean includeSelf,
            @GraphQLArgument(name = GqlConstants.GRANTEE, description = "Filter by the specified grantee type") GranteeChooser grantee,
            @GraphQLArgument(name = GqlConstants.OWNER, description = "Specifies the owner of the share") AccountSelector owner,
            @GraphQLRootContext RequestContext context) throws ServiceException {
        return folderRepository.shareInfo(context, internal, includeSelf, grantee, owner);
    }
}