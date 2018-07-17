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

import com.zimbra.common.service.ServiceException;
import com.zimbra.graphql.models.AuthContext;
import com.zimbra.graphql.repositories.impl.ZXMLFolderRepository;
import com.zimbra.soap.mail.type.Folder;
import com.zimbra.soap.mail.type.FolderActionResult;
import com.zimbra.soap.mail.type.FolderActionSelector;
import com.zimbra.soap.mail.type.GetFolderSpec;
import com.zimbra.soap.mail.type.NewFolderSpec;

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
public class FolderResolver {

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
        @GraphQLRootContext AuthContext context) throws ServiceException {
        return folderRepository.getFolder(context.getOperationContext(), context.getAccount(),
            visible, needGranteeName, view, depth, traverseMountpoints, getFolder);
    }

    @GraphQLMutation(description = "Create a folder with given properties.")
    public Folder createFolder(
        @GraphQLNonNull @GraphQLArgument(name = "folder") NewFolderSpec folder,
        @GraphQLRootContext AuthContext context) throws ServiceException {
        return folderRepository.createFolder(context.getOperationContext(), context.getAccount(),
            folder);
    }

    @GraphQLMutation(description = "Handles a folder action request.")
    public FolderActionResult action(
        @GraphQLNonNull @GraphQLArgument(name = "input") FolderActionSelector input,
        @GraphQLRootContext AuthContext context) throws ServiceException {
        return folderRepository.action(context.getOperationContext(), context.getAccount(), input);
    }

}