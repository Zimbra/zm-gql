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
import com.zimbra.cs.service.mail.CancelAppointment;
import com.zimbra.cs.service.mail.CreateAppointment;
import com.zimbra.cs.service.mail.CreateAppointmentException;
import com.zimbra.cs.service.mail.GetFreeBusy;
import com.zimbra.cs.service.mail.ModifyAppointment;
import com.zimbra.cs.service.mail.SendInviteReply;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.models.inputs.GQLInviteReplyVerbInput;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.XMLDocumentUtilities;
import com.zimbra.soap.ZimbraSoapContext;


/**
 * Test class for {@link ZXMLCalendarRepository}.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({GQLAuthUtilities.class, XMLDocumentUtilities.class, ZimbraSoapContext.class})
public class ZXMLCalendarRepositoryTest {

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
     * Test method for {@link ZXMLCalendarRepository#appointmentCreate}<br>
     * Validates that the create appointment request is executed.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testAppointmentCreate() throws Exception {
        final ZXMLCalendarRepository repository = PowerMock
            .createPartialMockForAllMethodsExcept(ZXMLCalendarRepository.class, "appointmentCreate");

        // expect to create a zimbra soap context
        GQLAuthUtilities.getZimbraSoapContext(rctxt);
        PowerMock.expectLastCall().andReturn(mockZsc);
        // expect to unmarshall a request
        XMLDocumentUtilities.toElement(anyObject());
        PowerMock.expectLastCall().andReturn(mockRequest);
        // expect to execute an element on the CreateAppointment document handler
        expect(XMLDocumentUtilities
                .executeDocument(anyObject(CreateAppointment.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
            .andReturn(null);

        PowerMock.replay(GQLAuthUtilities.class);
        PowerMock.replay(XMLDocumentUtilities.class);

        repository.appointmentCreate(rctxt, false, 0, false, false, false, null);

        PowerMock.verify(GQLAuthUtilities.class);
        PowerMock.verify(XMLDocumentUtilities.class);
    }

    /**
     * Test method for {@link ZXMLCalendarRepository#appointmentExceptionCreate}<br>
     * Validates that the create appointment exception request is executed.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testAppointmentExceptionCreate() throws Exception {
        final ZXMLCalendarRepository repository = PowerMock
            .createPartialMockForAllMethodsExcept(ZXMLCalendarRepository.class, "appointmentExceptionCreate");

        // expect to create a zimbra soap context
        GQLAuthUtilities.getZimbraSoapContext(rctxt);
        PowerMock.expectLastCall().andReturn(mockZsc);
        // expect to unmarshall a request
        XMLDocumentUtilities.toElement(anyObject());
        PowerMock.expectLastCall().andReturn(mockRequest);
        // expect to execute an element on the CreateAppointmentException document handler
        expect(XMLDocumentUtilities
                .executeDocument(anyObject(CreateAppointmentException.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
            .andReturn(null);

        PowerMock.replay(GQLAuthUtilities.class);
        PowerMock.replay(XMLDocumentUtilities.class);

        repository.appointmentExceptionCreate(rctxt, "some-id", 0, 0, 0, false, 0, false, false, false, null);

        PowerMock.verify(GQLAuthUtilities.class);
        PowerMock.verify(XMLDocumentUtilities.class);
    }

    /**
     * Test method for {@link ZXMLCalendarRepository#appointmentModify}<br>
     * Validates that the modify appointment request is executed.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testAppointmentModify() throws Exception {
        final ZXMLCalendarRepository repository = PowerMock
            .createPartialMockForAllMethodsExcept(ZXMLCalendarRepository.class, "appointmentModify");

        // expect to create a zimbra soap context
        GQLAuthUtilities.getZimbraSoapContext(rctxt);
        PowerMock.expectLastCall().andReturn(mockZsc);
        // expect to unmarshall a request
        XMLDocumentUtilities.toElement(anyObject());
        PowerMock.expectLastCall().andReturn(mockRequest);
        // expect to execute an element on the ModifyAppointment document handler
        expect(XMLDocumentUtilities
                .executeDocument(anyObject(ModifyAppointment.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
            .andReturn(null);

        PowerMock.replay(GQLAuthUtilities.class);
        PowerMock.replay(XMLDocumentUtilities.class);

        repository.appointmentModify(rctxt, "some-id", 0, 0, 0, false, 0, false, false, false, null);

        PowerMock.verify(GQLAuthUtilities.class);
        PowerMock.verify(XMLDocumentUtilities.class);
    }

    /**
     * Test method for {@link ZXMLCalendarRepository#inviteReply}<br>
     * Validates that the invite reply request is executed.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testInviteReply() throws Exception {
        final ZXMLCalendarRepository repository = PowerMock
            .createPartialMockForAllMethodsExcept(ZXMLCalendarRepository.class, "inviteReply");

        // expect to create a zimbra soap context
        GQLAuthUtilities.getZimbraSoapContext(rctxt);
        PowerMock.expectLastCall().andReturn(mockZsc);
        // expect to unmarshall a request
        XMLDocumentUtilities.toElement(anyObject());
        PowerMock.expectLastCall().andReturn(mockRequest);
        // expect to execute an element on the SendInviteReply document handler
        expect(XMLDocumentUtilities
                .executeDocument(anyObject(SendInviteReply.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
            .andReturn(null);

        PowerMock.replay(GQLAuthUtilities.class);
        PowerMock.replay(XMLDocumentUtilities.class);

        repository.inviteReply(rctxt, "some-id", 0, GQLInviteReplyVerbInput.ACCEPT, false, "ident", null, null, null);

        PowerMock.verify(GQLAuthUtilities.class);
        PowerMock.verify(XMLDocumentUtilities.class);
    }

    /**
     * Test method for {@link ZXMLCalendarRepository#appointmentCancel}<br>
     * Validates that the cancel appointment request is executed.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testAppointmentCancel() throws Exception {
        final ZXMLCalendarRepository repository = PowerMock
            .createPartialMockForAllMethodsExcept(ZXMLCalendarRepository.class, "appointmentCancel");

        // expect to create a zimbra soap context
        GQLAuthUtilities.getZimbraSoapContext(rctxt);
        PowerMock.expectLastCall().andReturn(mockZsc);
        // expect to unmarshall a request
        XMLDocumentUtilities.toElement(anyObject());
        PowerMock.expectLastCall().andReturn(mockRequest);
        // expect to execute an element on the CancelAppointment document handler
        expect(XMLDocumentUtilities
                .executeDocument(anyObject(CancelAppointment.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
            .andReturn(null);

        PowerMock.replay(GQLAuthUtilities.class);
        PowerMock.replay(XMLDocumentUtilities.class);

        repository.appointmentCancel(rctxt,"some-id", 0, 0, 0, null, null, null);

        PowerMock.verify(GQLAuthUtilities.class);
        PowerMock.verify(XMLDocumentUtilities.class);
    }

    /**
     * Test method for {@link ZXMLCalendarRepository#freeBusy}<br>
     * Validates that the free busy request is executed.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testFreeBusy() throws Exception {
        final ZXMLCalendarRepository repository = PowerMock
            .createPartialMockForAllMethodsExcept(ZXMLCalendarRepository.class, "freeBusy");

        // expect to create a zimbra soap context
        GQLAuthUtilities.getZimbraSoapContext(rctxt);
        PowerMock.expectLastCall().andReturn(mockZsc);
        // expect to unmarshall a request
        XMLDocumentUtilities.toElement(anyObject());
        PowerMock.expectLastCall().andReturn(mockRequest);
        // expect to execute an element on the GetFreeBusy document handler
        expect(XMLDocumentUtilities
                .executeDocument(anyObject(GetFreeBusy.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
            .andReturn(null);

        PowerMock.replay(GQLAuthUtilities.class);
        PowerMock.replay(XMLDocumentUtilities.class);

        repository.freeBusy(rctxt, 0L, 0L, null, "test1@zimbra.com", null, null);

        PowerMock.verify(GQLAuthUtilities.class);
        PowerMock.verify(XMLDocumentUtilities.class);
    }
}
