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
import com.zimbra.cs.service.mail.ContactAction;
import com.zimbra.cs.service.mail.CreateContact;
import com.zimbra.cs.service.mail.GetContacts;
import com.zimbra.cs.service.mail.ModifyContact;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.XMLDocumentUtilities;
import com.zimbra.soap.ZimbraSoapContext;


/**
 * Test class for {@link ZXMLContactRepository}.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({GQLAuthUtilities.class, XMLDocumentUtilities.class, ZimbraSoapContext.class})
public class ZXMLContactRepositoryTest {

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
     * Test method for {@link ZXMLContactRepository#contacts}<br>
     * Validates that the contacts request is executed.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testContacts() throws Exception {
        final ZXMLContactRepository repository = PowerMock
            .createPartialMockForAllMethodsExcept(ZXMLContactRepository.class, "contacts");

        // expect to create a zimbra soap context
        GQLAuthUtilities.getZimbraSoapContext(rctxt);
        PowerMock.expectLastCall().andReturn(mockZsc);
        // expect to unmarshall a request
        XMLDocumentUtilities.toElement(anyObject());
        PowerMock.expectLastCall().andReturn(mockRequest);
        // expect to execute an element on the GetContacts document handler
        expect(XMLDocumentUtilities
                .executeDocument(anyObject(GetContacts.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
            .andReturn(null);

        PowerMock.replay(GQLAuthUtilities.class);
        PowerMock.replay(XMLDocumentUtilities.class);

        repository.contacts(rctxt, null);

        PowerMock.verify(GQLAuthUtilities.class);
        PowerMock.verify(XMLDocumentUtilities.class);
    }

    /**
     * Test method for {@link ZXMLContactRepository#contactCreate}<br>
     * Validates that the create contact request is executed.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testContactCreate() throws Exception {
        final ZXMLContactRepository repository = PowerMock
            .createPartialMockForAllMethodsExcept(ZXMLContactRepository.class, "contactCreate");

        // expect to create a zimbra soap context
        GQLAuthUtilities.getZimbraSoapContext(rctxt);
        PowerMock.expectLastCall().andReturn(mockZsc);
        // expect to unmarshall a request
        XMLDocumentUtilities.toElement(anyObject());
        PowerMock.expectLastCall().andReturn(mockRequest);
        // expect to execute an element on the CreateContact document handler
        expect(XMLDocumentUtilities
                .executeDocument(anyObject(CreateContact.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
            .andReturn(null);

        PowerMock.replay(GQLAuthUtilities.class);
        PowerMock.replay(XMLDocumentUtilities.class);

        repository.contactCreate(rctxt, false, false, false, null);

        PowerMock.verify(GQLAuthUtilities.class);
        PowerMock.verify(XMLDocumentUtilities.class);
    }

    /**
     * Test method for {@link ZXMLContactRepository#contactAction}<br>
     * Validates that the contact action request is executed.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testContactAction() throws Exception {
        final ZXMLContactRepository repository = PowerMock
            .createPartialMockForAllMethodsExcept(ZXMLContactRepository.class, "contactAction");

        // expect to create a zimbra soap context
        GQLAuthUtilities.getZimbraSoapContext(rctxt);
        PowerMock.expectLastCall().andReturn(mockZsc);
        // expect to unmarshall a request
        XMLDocumentUtilities.toElement(anyObject());
        PowerMock.expectLastCall().andReturn(mockRequest);
        // expect to execute an element on the ContactAction document handler
        expect(XMLDocumentUtilities
                .executeDocument(anyObject(ContactAction.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
            .andReturn(null);

        PowerMock.replay(GQLAuthUtilities.class);
        PowerMock.replay(XMLDocumentUtilities.class);

        repository.contactAction(rctxt, null);

        PowerMock.verify(GQLAuthUtilities.class);
        PowerMock.verify(XMLDocumentUtilities.class);
    }

    /**
     * Test method for {@link ZXMLContactRepository#contactModify}<br>
     * Validates that the modify contact request is executed.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testContactModify() throws Exception {
        final ZXMLContactRepository repository = PowerMock
            .createPartialMockForAllMethodsExcept(ZXMLContactRepository.class, "contactModify");

        // expect to create a zimbra soap context
        GQLAuthUtilities.getZimbraSoapContext(rctxt);
        PowerMock.expectLastCall().andReturn(mockZsc);
        // expect to unmarshall a request
        XMLDocumentUtilities.toElement(anyObject());
        PowerMock.expectLastCall().andReturn(mockRequest);
        // expect to execute an element on the ModifyContact document handler
        expect(XMLDocumentUtilities
                .executeDocument(anyObject(ModifyContact.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
            .andReturn(null);

        PowerMock.replay(GQLAuthUtilities.class);
        PowerMock.replay(XMLDocumentUtilities.class);

        repository.contactModify(rctxt, false, false, false, false, null);

        PowerMock.verify(GQLAuthUtilities.class);
        PowerMock.verify(XMLDocumentUtilities.class);
    }
}
