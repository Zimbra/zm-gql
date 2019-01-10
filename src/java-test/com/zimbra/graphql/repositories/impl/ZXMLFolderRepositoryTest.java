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
import com.zimbra.cs.service.account.GetShareInfo;
import com.zimbra.cs.service.mail.CreateFolder;
import com.zimbra.cs.service.mail.CreateMountpoint;
import com.zimbra.cs.service.mail.CreateSearchFolder;
import com.zimbra.cs.service.mail.GetFolder;
import com.zimbra.cs.service.mail.GetSearchFolder;
import com.zimbra.cs.service.mail.ModifySearchFolder;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.models.inputs.GQLFolderSelector;
import com.zimbra.graphql.models.inputs.GQLOwnerSelector;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.XMLDocumentUtilities;
import com.zimbra.soap.ZimbraSoapContext;
import com.zimbra.soap.mail.type.Folder.View;


/**
 * Test class for {@link ZXMLFolderRepository}.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({GQLAuthUtilities.class, XMLDocumentUtilities.class, ZimbraSoapContext.class})
public class ZXMLFolderRepositoryTest {

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
     * Test method for {@link ZXMLFolderRepository#folder}<br>
     * Validates that the folder request is executed.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testFolder() throws Exception {
        final ZXMLFolderRepository repository = PowerMock
            .createPartialMockForAllMethodsExcept(ZXMLFolderRepository.class, "folder");

        // expect to create a zimbra soap context
        GQLAuthUtilities.getZimbraSoapContext(rctxt);
        PowerMock.expectLastCall().andReturn(mockZsc);
        // expect to unmarshall a request
        XMLDocumentUtilities.toElement(anyObject());
        PowerMock.expectLastCall().andReturn(mockRequest);
        // expect to execute an element on the GetFolder document handler
        expect(XMLDocumentUtilities
                .executeDocument(anyObject(GetFolder.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
            .andReturn(null);

        PowerMock.replay(GQLAuthUtilities.class);
        PowerMock.replay(XMLDocumentUtilities.class);

        repository.folder(rctxt, false, false, View.MESSAGE, null, false, null);

        PowerMock.verify(GQLAuthUtilities.class);
        PowerMock.verify(XMLDocumentUtilities.class);
    }

    /**
     * Test method for {@link ZXMLFolderRepository#createFolder}<br>
     * Validates that the create folder request is executed.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testFolderCreate() throws Exception {
        final ZXMLFolderRepository repository = PowerMock
            .createPartialMockForAllMethodsExcept(ZXMLFolderRepository.class, "createFolder");

        // expect to create a zimbra soap context
        GQLAuthUtilities.getZimbraSoapContext(rctxt);
        PowerMock.expectLastCall().andReturn(mockZsc);
        // expect to unmarshall a request
        XMLDocumentUtilities.toElement(anyObject());
        PowerMock.expectLastCall().andReturn(mockRequest);
        // expect to execute an element on the CreateFolder document handler
        expect(XMLDocumentUtilities
                .executeDocument(anyObject(CreateFolder.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
            .andReturn(null);

        PowerMock.replay(GQLAuthUtilities.class);
        PowerMock.replay(XMLDocumentUtilities.class);

        repository.createFolder(rctxt, null);

        PowerMock.verify(GQLAuthUtilities.class);
        PowerMock.verify(XMLDocumentUtilities.class);
    }

    /**
     * Test method for {@link ZXMLFolderRepository#searchFolderGet}<br>
     * Validates that the get search folder request is executed.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testSearchFolderGet() throws Exception {
        final ZXMLFolderRepository repository = PowerMock
            .createPartialMockForAllMethodsExcept(ZXMLFolderRepository.class, "searchFolderGet");

        // expect to create a zimbra soap context
        GQLAuthUtilities.getZimbraSoapContext(rctxt);
        PowerMock.expectLastCall().andReturn(mockZsc);
        // expect to unmarshall a request
        XMLDocumentUtilities.toElement(anyObject());
        PowerMock.expectLastCall().andReturn(mockRequest);
        // expect to execute an element on the GetSearchFolder document handler
        expect(XMLDocumentUtilities
                .executeDocument(anyObject(GetSearchFolder.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
            .andReturn(null);

        PowerMock.replay(GQLAuthUtilities.class);
        PowerMock.replay(XMLDocumentUtilities.class);

        repository.searchFolderGet(rctxt);

        PowerMock.verify(GQLAuthUtilities.class);
        PowerMock.verify(XMLDocumentUtilities.class);
    }

    /**
     * Test method for {@link ZXMLFolderRepository#searchFolderCreate}<br>
     * Validates that the create search folder request is executed.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testSearchFolderCreate() throws Exception {
        final ZXMLFolderRepository repository = PowerMock
            .createPartialMockForAllMethodsExcept(ZXMLFolderRepository.class, "searchFolderCreate");

        // expect to create a zimbra soap context
        GQLAuthUtilities.getZimbraSoapContext(rctxt);
        PowerMock.expectLastCall().andReturn(mockZsc);
        // expect to unmarshall a request
        XMLDocumentUtilities.toElement(anyObject());
        PowerMock.expectLastCall().andReturn(mockRequest);
        // expect to execute an element on the CreateSearchFolder document handler
        expect(XMLDocumentUtilities
                .executeDocument(anyObject(CreateSearchFolder.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
            .andReturn(null);

        PowerMock.replay(GQLAuthUtilities.class);
        PowerMock.replay(XMLDocumentUtilities.class);

        repository.searchFolderCreate(rctxt, null);

        PowerMock.verify(GQLAuthUtilities.class);
        PowerMock.verify(XMLDocumentUtilities.class);
    }

    /**
     * Test method for {@link ZXMLFolderRepository#searchFolderModify}<br>
     * Validates that the modify search folder request is executed.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testSearchFolderModify() throws Exception {
        final ZXMLFolderRepository repository = PowerMock
            .createPartialMockForAllMethodsExcept(ZXMLFolderRepository.class, "searchFolderModify");

        // expect to create a zimbra soap context
        GQLAuthUtilities.getZimbraSoapContext(rctxt);
        PowerMock.expectLastCall().andReturn(mockZsc);
        // expect to unmarshall a request
        XMLDocumentUtilities.toElement(anyObject());
        PowerMock.expectLastCall().andReturn(mockRequest);
        // expect to execute an element on the CreateSearchFolder document handler
        expect(XMLDocumentUtilities
                .executeDocument(anyObject(ModifySearchFolder.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
            .andReturn(null);

        PowerMock.replay(GQLAuthUtilities.class);
        PowerMock.replay(XMLDocumentUtilities.class);

        repository.searchFolderModify(rctxt, null);

        PowerMock.verify(GQLAuthUtilities.class);
        PowerMock.verify(XMLDocumentUtilities.class);
    }

    /**
     * Test method for {@link ZXMLFolderRepository#mountpointCreate}<br>
     * Validates that the create mountpoint request is executed.
     *
     * @throws Exception If there are any issues
     */
    @Test
    public void testMountpointCreate() throws Exception {
        final ZXMLFolderRepository repository = PowerMock
            .createPartialMockForAllMethodsExcept(ZXMLFolderRepository.class, "mountpointCreate");

        GQLAuthUtilities.getZimbraSoapContext(rctxt);
        PowerMock.expectLastCall().andReturn(mockZsc);
        XMLDocumentUtilities.toElement(anyObject());
        PowerMock.expectLastCall().andReturn(mockRequest);
        expect(XMLDocumentUtilities
                .executeDocument(anyObject(CreateMountpoint.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
            .andReturn(null);

        PowerMock.replay(GQLAuthUtilities.class);
        PowerMock.replay(XMLDocumentUtilities.class);

        repository.mountpointCreate(rctxt, "test",  "1",
                GQLOwnerSelector.EMAIL, "test@tets.com", GQLFolderSelector.ID, "1",
                null, null, null, null, null, false, false);

        PowerMock.verify(GQLAuthUtilities.class);
        PowerMock.verify(XMLDocumentUtilities.class);
    }

    /**
     * Test method for {@link ZXMLFolderRepository#shareInfo}<br>
     * Validates that the GetShareInfo request is executed.
     *
     * @throws Exception If there are any issues
     */
    @Test
    public void testShareInfo() throws Exception {
        final ZXMLFolderRepository repository = PowerMock
            .createPartialMockForAllMethodsExcept(ZXMLFolderRepository.class, "shareInfo");
        GQLAuthUtilities.getZimbraSoapContext(rctxt);
        PowerMock.expectLastCall().andReturn(mockZsc);
        XMLDocumentUtilities.toElement(anyObject());
        PowerMock.expectLastCall().andReturn(mockRequest);
        expect(XMLDocumentUtilities
                .executeDocument(anyObject(GetShareInfo.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
            .andReturn(null);

        PowerMock.replay(GQLAuthUtilities.class);
        PowerMock.replay(XMLDocumentUtilities.class);

        repository.shareInfo(rctxt, false, false, null, null);

        PowerMock.verify(GQLAuthUtilities.class);
        PowerMock.verify(XMLDocumentUtilities.class);
    }
}
