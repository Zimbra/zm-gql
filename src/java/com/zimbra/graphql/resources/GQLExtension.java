package com.zimbra.graphql.resources;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.util.ZimbraLog;
import com.zimbra.cs.extension.ExtensionDispatcherServlet;
import com.zimbra.cs.extension.ExtensionException;
import com.zimbra.cs.extension.ZimbraExtension;

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
        return "zm-gql";
    }

    @Override
    public void init() throws ExtensionException, ServiceException {
        ZimbraLog.extensions.info("Registering zm-gql");
        ExtensionDispatcherServlet.register(this, new GQLServlet());
    }

}
