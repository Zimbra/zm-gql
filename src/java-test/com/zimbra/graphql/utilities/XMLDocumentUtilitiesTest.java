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

import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.QName;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.zimbra.common.soap.AccountConstants;
import com.zimbra.common.soap.Element;
import com.zimbra.common.soap.SoapProtocol;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.soap.DocumentHandler;
import com.zimbra.soap.SoapEngine;
import com.zimbra.soap.SoapServlet;
import com.zimbra.soap.ZimbraSoapContext;


/**
 * Test class for {@link XMLDocumentUtilities}.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({GQLAuthUtilities.class, ZimbraSoapContext.class})
public class XMLDocumentUtilitiesTest {

    /**
     * Test method for {@link XMLDocumentUtilities#executeDocumentAsGuest}<br>
     * Validates that the request is executed using the handler and a guest soap context.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testExecuteDocumentAsGuest() throws Exception {
        final QName qName = AccountConstants.AUTH_REQUEST;
        final ZimbraSoapContext mockZsc = EasyMock.createMock(ZimbraSoapContext.class);
        final DocumentHandler handler = EasyMock.createMock(DocumentHandler.class);
        final RequestContext rctxt = new RequestContext();
        final Element request = Element.create(SoapProtocol.Soap12, qName);
        final Map<String, Object> context = new HashMap<String, Object>();
        context.put(SoapEngine.ZIMBRA_CONTEXT, mockZsc);
        context.put(SoapServlet.SERVLET_REQUEST, rctxt.getRawRequest());
        context.put(SoapServlet.SERVLET_RESPONSE, rctxt.getRawResponse());

        PowerMock.mockStaticPartial(GQLAuthUtilities.class, "getGuestZimbraSoapContext");

        // expect to create a guest zimbra soap context
        GQLAuthUtilities.getGuestZimbraSoapContext(request.getQName(), handler, rctxt);
        PowerMock.expectLastCall().andReturn(mockZsc);
        // expect to handle the request with the necessary context params
        expect(handler.handle(eq(request), eq(context))).andReturn(null);

        replay(handler);
        PowerMock.replay(GQLAuthUtilities.class);

        XMLDocumentUtilities.executeDocumentAsGuest(handler, request, rctxt);

        verify(handler);
        PowerMock.verify(GQLAuthUtilities.class);
    }

    /**
     * Test method for {@link XMLDocumentUtilities#executeDocument}<br>
     * Validates that the request is executed using the handler and specified soap context.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testExecuteDocument() throws Exception {
        final ZimbraSoapContext mockZsc = EasyMock.createMock(ZimbraSoapContext.class);
        final DocumentHandler handler = EasyMock.createMock(DocumentHandler.class);
        final RequestContext rctxt = new RequestContext();
        final Element request = Element.create(SoapProtocol.Soap12, AccountConstants.AUTH_REQUEST);
        final Map<String, Object> context = new HashMap<String, Object>();
        context.put(SoapEngine.ZIMBRA_CONTEXT, mockZsc);
        context.put(SoapServlet.SERVLET_REQUEST, rctxt.getRawRequest());

        // expect to handle the request with the necessary context params
        expect(handler.handle(eq(request), eq(context))).andReturn(null);

        replay(handler);

        XMLDocumentUtilities.executeDocument(handler, mockZsc, request, rctxt);

        verify(handler);
    }

}
