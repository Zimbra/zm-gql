package com.zimbra.graphql.models;

import io.leangen.graphql.annotations.types.GraphQLType;

/**
 * The GQLFolderView enum.<br>
 * Represents the intended view mode of a folder.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models
 * @copyright Copyright © 2018
 */
@GraphQLType(name = "FolderView", description = "Represents the intended view mode of a folder.")
public enum GQLFolderView {
    search,
    folder,
    tag,
    conversation,
    message,
    contact,
    document,
    appointment,
    virtual,
    remote,
    wiki,
    task,
    chat,
    note,
    comment;

    public static GQLFolderView fromString(String view) {
        GQLFolderView fView = GQLFolderView.message;
        if (view != null) {
            try {
                fView = GQLFolderView.valueOf(view.toLowerCase());
            } catch (final IllegalArgumentException e) {
                // do nothing
            }
        }
        return fView;
    }
}
