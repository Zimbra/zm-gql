package com.zimbra.graphql.models;

import java.util.List;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLType;

/**
 * The GQLFolder class.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
@GraphQLType(name = "Folder", description = "A mailbox folder.")
public class GQLFolder {

    /**
     * The absolute path to this folder.
     */
    private String absFolderPath;

    /**
     * Folder ACL.
     */
    private GQLACL acl;

    /**
     * Folder color.
     */
    private Long color;

    /**
     * Folder flags.
     */
    private String flags;


    /**
     * Folder tags.
     */
    private String[] tags;

    /**
     * Folder id.
     */
    private String id;

    /**
     * Folder uuid.
     */
    private String uuid;

    /**
     * Folder name.
     */
    private String name;

    /**
     * Number of non-subfolder items in folder.
     */
    private Long nonFolderItemCount;

    /**
     * Total size of all of non-subfolder items in folder.
     */
    private Long nonFolderItemCountTotal;

    /**
     * Folders this user has linked from other users nested in this Folder.
     */
    private List<GQLFolder> linkedFolders;

    /**
     * Folders nested in this Folder.
     */
    private List<GQLFolder> folders;

    /**
     * Folder owner.
     */
    private String owner;

    /**
     * Folder revision.
     */
    private Integer revision;

    /**
     * Folder view.
     */
    private GQLFolderView view;

    /**
     * Id of this folder's parent.
     */
    private String parentFolderId;

    /**
     * Unread count.
     */
    private Integer unread;

    /**
     * Folder query.
     */
    private String query;

    /**
     * @return The absolute folder path
     */
    @GraphQLQuery(name = "absFolderPath", description = "The absolute path to this folder.")
    public String getAbsFolderPath() {
        return absFolderPath;
    }

    /**
     * @param absFolderPath The absolute folder path to set
     */
    public void setAbsFolderPath(String absFolderPath) {
        this.absFolderPath = absFolderPath;
    }

    /**
     * @return The ACL
     */
    @GraphQLQuery(name = "acl", description = "Folder ACL.")
    public GQLACL getAcl() {
        return acl;
    }

    /**
     * @param acl The ACL to set
     */
    public void setAcl(GQLACL acl) {
        this.acl = acl;
    }

    /**
     * @return The folder color
     */
    @GraphQLQuery(name = "color", description = "Folder color.")
    public Long getColor() {
        return color;
    }

    /**
     * @param color The color to set
     */
    public void setColor(Long color) {
        this.color = color;
    }

    /**
     * @return The folder flags
     */
    @GraphQLQuery(name = "flags", description = "Folder flags.")
    public String getFlags() {
        return flags;
    }

    /**
     * @param flags The flags to set
     */
    public void setFlags(String flags) {
        this.flags = flags;
    }

    /**
     * @return The folder tags
     */
    @GraphQLQuery(name = "tags", description = "Folder tags.")
    public String[] getTags() {
        return tags;
    }

    /**
     * @param tags The tags to set
     */
    public void setTags(String[] tags) {
        this.tags = tags;
    }

    /**
     * @return The id
     */
    @GraphQLQuery(name = "id", description = "Folder id.")
    public String getId() {
        return id;
    }

    /**
     * @param id The id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The uuid
     */
    @GraphQLQuery(name = "uuid", description = "Folder uuid.")
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid The uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return The name
     */
    @GraphQLQuery(name = "name", description = "Folder name.")
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The non folder item count
     */
    @GraphQLQuery(name = "nonFolderItemCount", description = "Number of non-subfolder items in folder.")
    public Long getNonFolderItemCount() {
        return nonFolderItemCount;
    }

    /**
     * @param nonFolderItemCount The non folder item count to set
     */
    public void setNonFolderItemCount(Long nonFolderItemCount) {
        this.nonFolderItemCount = nonFolderItemCount;
    }

    /**
     * @return The non folder item count total
     */
    @GraphQLQuery(name = "nonFolderItemCountTotal", description = "Total size of all non-subfolder items in folder.")
    public Long getNonFolderItemCountTotal() {
        return nonFolderItemCountTotal;
    }

    /**
     * @param nonFolderItemCountTotal The non folder item count total to set
     */
    public void setNonFolderItemCountTotal(Long nonFolderItemCountTotal) {
        this.nonFolderItemCountTotal = nonFolderItemCountTotal;
    }

    /**
     * @return The linked folders
     */
    @GraphQLQuery(name = "linkedFolders", description = "Folders this user has linked from other users nested in this folder.")
    public List<GQLFolder> getLinkedFolders() {
        return linkedFolders;
    }

    /**
     * @param linkedFolders The linked folders to set
     */
    public void setLinkedFolders(List<GQLFolder> linkedFolders) {
        this.linkedFolders = linkedFolders;
    }

    /**
     * @return The folders
     */
    @GraphQLQuery(name = "folders", description = "Folders nested in this folder.")
    public List<GQLFolder> getFolders() {
        return folders;
    }

    /**
     * @param folders The folders to set
     */
    public void setFolders(List<GQLFolder> folders) {
        this.folders = folders;
    }

    /**
     * @return The owner
     */
    @GraphQLQuery(name = "owner", description = "Folder owner.")
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner The owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return The revision
     */
    @GraphQLQuery(name = "revision", description = "Folder revision.")
    public Integer getRevision() {
        return revision;
    }

    /**
     * @param revision The revision to set
     */
    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    /**
     * @return The view
     */
    @GraphQLQuery(name = "view", description = "Folder view.")
    public GQLFolderView getView() {
        return view;
    }

    /**
     * @param view The view to set
     */
    public void setView(GQLFolderView view) {
        this.view = view;
    }

    /**
     * @return The parent folder id
     */
    @GraphQLQuery(name = "parentFolderId", description = "Id of this folder's parent.")
    public String getParentFolderId() {
        return parentFolderId;
    }

    /**
     * @param parentFolderId The parent folder id to set
     */
    public void setParentFolderId(String parentFolderId) {
        this.parentFolderId = parentFolderId;
    }

    /**
     * @return The unread count
     */
    @GraphQLQuery(name = "unread", description = "Unread count.")
    public Integer getUnread() {
        return unread;
    }

    /**
     * @param unread The unread count to set
     */
    public void setUnread(Integer unread) {
        this.unread = unread;
    }

    /**
     * @return The query
     */
    @GraphQLQuery(name = "query", description = "Folder query.")
    public String getQuery() {
        return query;
    }

    /**
     * @param query The query to set
     */
    public void setQuery(String query) {
        this.query = query;
    }

}
