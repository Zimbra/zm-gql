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
package com.zimbra.graphql.resources;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.util.ZimbraLog;
import com.zimbra.cs.extension.ExtensionDispatcherServlet;
import com.zimbra.cs.extension.ExtensionException;
import com.zimbra.cs.extension.ZimbraExtension;
import com.zimbra.graphql.utilities.GQLConstants;

/**
 * GQLExtension class.<br>
 * Extends the ZimbraExtension class.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.resources
 * @copyright Copyright © 2018
 */
public class GQLExtension implements ZimbraExtension {

    @Override
    public void destroy() {
        ExtensionDispatcherServlet.unregister(this);
    }

    @Override
    public String getName() {
        return GQLConstants.API_NAME.getValue();
    }

    @Override
    public void init() throws ExtensionException, ServiceException {
        ZimbraLog.extensions.info("Registering %s", getName());
        ExtensionDispatcherServlet.register(this, new GQLServlet());
    }

}
