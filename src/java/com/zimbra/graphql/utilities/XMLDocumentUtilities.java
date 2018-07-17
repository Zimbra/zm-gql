package com.zimbra.graphql.utilities;

import java.util.HashMap;
import java.util.Map;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.Element;
import com.zimbra.soap.DocumentHandler;
import com.zimbra.soap.JaxbUtil;
import com.zimbra.soap.SoapEngine;
import com.zimbra.soap.ZimbraSoapContext;

public class XMLDocumentUtilities {

    /**
     * Executes a given request on a document handler.
     *
     * @param handler The handler to handle the request
     * @param request The request to execute
     * @param zsc The zimbra soap context for the request
     * @return The document response
     * @throws ServiceException If there are issues executing the document
     */
    public static Element executeDocument(DocumentHandler handler, Element request, ZimbraSoapContext zsc)
        throws ServiceException {
        final Map<String, Object> context = new HashMap<String, Object>();
        context.put(SoapEngine.ZIMBRA_CONTEXT, zsc);
        return handler.handle(request, context);
    }

    /**
     * Creates an xml element from a Zimbra object.
     *
     * @see JaxbUtil#jaxbToElement(Object)
     */
    public static Element toElement(Object o) throws ServiceException {
        return JaxbUtil.jaxbToElement(o);
    }

    /**
     * Creates a Zimbra object from an xml element.
     *
     * @see JaxbUtil#elementToJaxb(Element, Class)
     */
    public static <T> T fromElement(Element e, Class<T> klass) throws ServiceException {
        return JaxbUtil.elementToJaxb(e, klass);
    }

}
