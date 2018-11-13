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
package com.zimbra.graphql.repositories.impl;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.zimbra.common.soap.Element;
import com.zimbra.cs.service.mail.GetMsg;
import com.zimbra.cs.service.mail.SendMsg;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.XMLDocumentUtilities;
import com.zimbra.soap.ZimbraSoapContext;


/**
 * Test class for {@link ZXMLMessageRepository}.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({GQLAuthUtilities.class, XMLDocumentUtilities.class, ZimbraSoapContext.class})
public class ZXMLMessageRepositoryTest {

    /**
     * Mock soap context for testing.
     */
    protected ZimbraSoapContext mockZsc;

    /**
     * Mock request element for testing.
     */
    protected Element mockRequest;

    /**
     * Mock response element for testing.
     */
    protected Element mockResponse;

    /**
     * Mock request context for testing.
     */
    protected RequestContext rctxt;

    /**
     * Setup for tests.
     *
     * @throws Exception If there are issues mocking
     */
    @Before
    public void setUp() throws Exception {
        mockZsc = EasyMock.createMock(ZimbraSoapContext.class);
        mockRequest = EasyMock.createMock(Element.class);
        mockResponse = EasyMock.createMock(Element.class);
        rctxt = EasyMock.createMock(RequestContext.class);

        PowerMock.mockStaticPartial(GQLAuthUtilities.class, "getZimbraSoapContext");
        PowerMock.mockStaticPartial(XMLDocumentUtilities.class, "executeDocument", "fromElement", "toElement");
    }


    /**
     * Test method for {@link ZXMLMessageRepository#message}<br>
     * Validates that the message request is executed.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testMessage() throws Exception {
        final ZXMLMessageRepository repository = PowerMock
            .createPartialMockForAllMethodsExcept(ZXMLMessageRepository.class, "message");

        // expect to create a zimbra soap context
        GQLAuthUtilities.getZimbraSoapContext(rctxt);
        PowerMock.expectLastCall().andReturn(mockZsc);
        // expect to unmarshall a request
        XMLDocumentUtilities.toElement(anyObject());
        PowerMock.expectLastCall().andReturn(mockRequest);
        // expect to execute an element on the GetMsg document handler
        expect(XMLDocumentUtilities
                .executeDocument(anyObject(GetMsg.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
            .andReturn(null);

        PowerMock.replay(GQLAuthUtilities.class);
        PowerMock.replay(XMLDocumentUtilities.class);

        repository.message(rctxt, null);

        PowerMock.verify(GQLAuthUtilities.class);
        PowerMock.verify(XMLDocumentUtilities.class);
    }

    /**
     * Test method for {@link ZXMLMessageRepository#messageSend}<br>
     * Validates that the send message request is executed.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testMessageSend() throws Exception {
        final ZXMLMessageRepository repository = PowerMock
            .createPartialMockForAllMethodsExcept(ZXMLMessageRepository.class, "messageSend");

        // expect to create a zimbra soap context
        GQLAuthUtilities.getZimbraSoapContext(rctxt);
        PowerMock.expectLastCall().andReturn(mockZsc);
        // expect to unmarshall a request
        XMLDocumentUtilities.toElement(anyObject());
        PowerMock.expectLastCall().andReturn(mockRequest);
        // expect to execute an element on the SendMsg document handler
        expect(XMLDocumentUtilities
                .executeDocument(anyObject(SendMsg.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
            .andReturn(null);

        PowerMock.replay(GQLAuthUtilities.class);
        PowerMock.replay(XMLDocumentUtilities.class);

        repository.messageSend(rctxt, false, false, false, false, null, null);

        PowerMock.verify(GQLAuthUtilities.class);
        PowerMock.verify(XMLDocumentUtilities.class);
    }
}
