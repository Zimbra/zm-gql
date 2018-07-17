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