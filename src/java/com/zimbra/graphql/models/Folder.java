package com.zimbra.graphql.models;

import java.util.List;

/**
 * The Folder class.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
public class Folder {

    /**
     * The absolute path to this folder.
     */
    private String absFolderPath;

    /**
     * Folder ACL.
     */
    private ACL acl;

    /**
     * Folder color.
     */
    private Integer color;

    /**
     * Folder flags.
     */
    private String flags;

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
    private Integer nonFolderItemCount;

    /**
     * Total size of all of non-subfolder items in folder.
     */
    private Integer nonFolderItemCountTotal;

    /**
     * Folders this user has linked from other users nested in this Folder.
     */
    private List<Folder> linkedFolders;

    /**
     * Folders nested in this Folder.
     */
    private List<Folder> folders;

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
    private FolderView view;

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
    public ACL getAcl() {
        return acl;
    }

    /**
     * @param acl The ACL to set
     */
    public void setAcl(ACL acl) {
        this.acl = acl;
    }

    /**
     * @return The folder color
     */
    public Integer getColor() {
        return color;
    }

    /**
     * @param color The color to set
     */
    public void setColor(Integer color) {
        this.color = color;
    }

    /**
     * @return The folder flags
     */
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
     * @return The id
     */
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
    public Integer getNonFolderItemCount() {
        return nonFolderItemCount;
    }

    /**
     * @param nonFolderItemCount The non folder item count to set
     */
    public void setNonFolderItemCount(Integer nonFolderItemCount) {
        this.nonFolderItemCount = nonFolderItemCount;
    }

    /**
     * @return The non folder item count total
     */
    public Integer getNonFolderItemCountTotal() {
        return nonFolderItemCountTotal;
    }

    /**
     * @param nonFolderItemCountTotal The non folder item count total to set
     */
    public void setNonFolderItemCountTotal(Integer nonFolderItemCountTotal) {
        this.nonFolderItemCountTotal = nonFolderItemCountTotal;
    }

    /**
     * @return The linked folders
     */
    public List<Folder> getLinkedFolders() {
        return linkedFolders;
    }

    /**
     * @param linkedFolders The linked folders to set
     */
    public void setLinkedFolders(List<Folder> linkedFolders) {
        this.linkedFolders = linkedFolders;
    }

    /**
     * @return The folders
     */
    public List<Folder> getFolders() {
        return folders;
    }

    /**
     * @param folders The folders to set
     */
    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }

    /**
     * @return The owner
     */
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
    public FolderView getView() {
        return view;
    }

    /**
     * @param view The view to set
     */
    public void setView(FolderView view) {
        this.view = view;
    }

    /**
     * @return The parent folder id
     */
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
