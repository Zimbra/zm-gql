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
package com.zimbra.graphql.utilities;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.Element;
import com.zimbra.common.util.ZimbraLog;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.soap.DocumentHandler;
import com.zimbra.soap.JaxbUtil;
import com.zimbra.soap.SoapEngine;
import com.zimbra.soap.SoapServlet;
import com.zimbra.soap.ZimbraSoapContext;

/**
 * The XMLDocumentUtilities class.<br>
 * Contains utility methods to assist in document execution.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.utilities
 * @copyright Copyright © 2018
 */
public class XMLDocumentUtilities {

    /**
     * Executes a given request on a document handler without
     * validating auth credentials.
     *
     * @param handler The handler to handle the request
     * @param request The request to execute
     * @param rctxt The graphql context for the request
     * @return The document response
     * @throws ServiceException If there are issues executing the document
     */
    public static Element executeDocumentAsGuest(DocumentHandler handler, Element request, RequestContext rctxt)
        throws ServiceException {
        final Map<String, Object> context = new HashMap<String, Object>();
        context.put(SoapEngine.ZIMBRA_CONTEXT,
            GQLAuthUtilities.getGuestZimbraSoapContext(request.getQName(), handler, rctxt));
        context.put(SoapServlet.SERVLET_REQUEST, rctxt.getRawRequest());
        context.put(SoapServlet.SERVLET_RESPONSE, rctxt.getRawResponse());
        return handler.handle(request, context);
    }

    /**
     * Executes a given request on a document handler.
     *
     * @param handler The handler to handle the request
     * @param zsc The zimbra soap context for the request
     * @param request The request to execute
     * @param rctxt The request context
     * @return The document response
     * @throws ServiceException If there are issues executing the document
     */
    public static Element executeDocument(DocumentHandler handler, ZimbraSoapContext zsc, Element request, RequestContext rctxt)
        throws ServiceException {
        final Map<String, Object> context = new HashMap<String, Object>();
        context.put(SoapEngine.ZIMBRA_CONTEXT, zsc);
        final HttpServletRequest req = rctxt.getRawRequest();
        context.put(SoapServlet.SERVLET_REQUEST, req);
        return handler.handle(request, context);
    }

    /**
     * Creates an xml element from a Zimbra object.
     *
     * @see JaxbUtil#jaxbToElement(Object)
     */
    public static Element toElement(Object o) throws ServiceException {
        Element element = JaxbUtil.jaxbToElement(o);
        if (element != null) {
            ZimbraLog.gql.debug("\n" + element.prettyPrint());
        }
        return element;
    }

    /**
     * Creates a Zimbra object from an xml element.
     *
     * @see JaxbUtil#elementToJaxb(Element, Class)
     */
    public static <T> T fromElement(Element e, Class<?> klass) throws ServiceException {
        if (e != null) {
            ZimbraLog.gql.debug("\n" + e.prettyPrint());
        }
        return JaxbUtil.elementToJaxb(e, klass);
    }

}
