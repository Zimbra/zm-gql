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
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.Collections;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.zimbra.common.soap.Element;
import com.zimbra.cs.account.AuthToken;
import com.zimbra.cs.service.account.ChangePassword;
import com.zimbra.cs.service.account.CreateSignature;
import com.zimbra.cs.service.account.DeleteSignature;
import com.zimbra.cs.service.account.EndSession;
import com.zimbra.cs.service.account.GetAccountInfo;
import com.zimbra.cs.service.account.GetInfo;
import com.zimbra.cs.service.account.GetPrefs;
import com.zimbra.cs.service.account.GetSignatures;
import com.zimbra.cs.service.account.GetWhiteBlackList;
import com.zimbra.cs.service.account.ModifyPrefs;
import com.zimbra.cs.service.account.ModifySignature;
import com.zimbra.cs.service.account.ModifyWhiteBlackList;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.XMLDocumentUtilities;
import com.zimbra.soap.ZimbraSoapContext;
import com.zimbra.soap.account.message.CreateSignatureResponse;
import com.zimbra.soap.account.type.NameId;
import com.zimbra.soap.account.type.Signature;


/**
 * Test class for {@link ZXMLAccountRepository}.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({GQLAuthUtilities.class, XMLDocumentUtilities.class, ZimbraSoapContext.class})
public class ZXMLAccountRepositoryTest {

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
     * Test method for {@link ZXMLAccountRepository#info}<br>
     * Validates that the info request is executed.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testInfo() throws Exception {
        final ZXMLAccountRepository repository = PowerMock
            .createPartialMockForAllMethodsExcept(ZXMLAccountRepository.class, "info");

        // expect to create a zimbra soap context
        GQLAuthUtilities.getZimbraSoapContext(rctxt);
        PowerMock.expectLastCall().andReturn(mockZsc);
        // expect to unmarshall a request
        XMLDocumentUtilities.toElement(anyObject());
        PowerMock.expectLastCall().andReturn(mockRequest);
        // expect to execute an element on the info document handler
        expect(XMLDocumentUtilities
                .executeDocument(anyObject(GetInfo.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
            .andReturn(null);

        PowerMock.replay(GQLAuthUtilities.class);
        PowerMock.replay(XMLDocumentUtilities.class);

        repository.info(rctxt, "", "");

        PowerMock.verify(GQLAuthUtilities.class);
        PowerMock.verify(XMLDocumentUtilities.class);
    }

    /**
    * Test method for {@link ZXMLAccountRepository#accountInfoGet}<br>
    * Validates that the accountInfo request is executed.
    *
    * @throws Exception If there are issues testing
    */
   @Test
   public void testAccountInfoGet() throws Exception {
       final String accountId = "account-id";
       final AuthToken mockToken = EasyMock.createMock(AuthToken.class);
       final ZXMLAccountRepository repository = PowerMock
           .createPartialMockForAllMethodsExcept(ZXMLAccountRepository.class, "accountInfoGet");

       // expect to create a zimbra soap context
       GQLAuthUtilities.getZimbraSoapContext(rctxt);
       PowerMock.expectLastCall().andReturn(mockZsc);
       // expect to get an accountId from the zsc's token
       expect(mockZsc.getAuthToken()).andReturn(mockToken);
       expect(mockToken.getAccountId()).andReturn(accountId);
       // expect to unmarshall a request
       XMLDocumentUtilities.toElement(anyObject());
       PowerMock.expectLastCall().andReturn(mockRequest);
       // expect to execute an element on the GetAccountInfo document handler
       expect(XMLDocumentUtilities
               .executeDocument(anyObject(GetAccountInfo.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
           .andReturn(null);

       replay(mockZsc);
       replay(mockToken);
       PowerMock.replay(GQLAuthUtilities.class);
       PowerMock.replay(XMLDocumentUtilities.class);

       repository.accountInfoGet(rctxt);

       verify(mockZsc);
       verify(mockToken);
       PowerMock.verify(GQLAuthUtilities.class);
       PowerMock.verify(XMLDocumentUtilities.class);
   }

   /**
    * Test method for {@link ZXMLAccountRepository#accountEndSession}<br>
    * Validates that the endSession request is executed.
    *
    * @throws Exception If there are issues testing
    */
   @Test
   public void testAccountEndSession() throws Exception {
       final ZXMLAccountRepository repository = PowerMock
           .createPartialMockForAllMethodsExcept(ZXMLAccountRepository.class, "accountEndSession");

       // expect to create a zimbra soap context
       GQLAuthUtilities.getZimbraSoapContext(rctxt);
       PowerMock.expectLastCall().andReturn(mockZsc);
       // expect to unmarshall a request
       XMLDocumentUtilities.toElement(anyObject());
       PowerMock.expectLastCall().andReturn(mockRequest);
       // expect to execute an element on the EndSession document handler
       expect(XMLDocumentUtilities
               .executeDocument(anyObject(EndSession.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
           .andReturn(EasyMock.createMock(Element.class));

       PowerMock.replay(GQLAuthUtilities.class);
       PowerMock.replay(XMLDocumentUtilities.class);

       repository.accountEndSession(rctxt, "session-id", true, true, false);

       PowerMock.verify(GQLAuthUtilities.class);
       PowerMock.verify(XMLDocumentUtilities.class);
   }

   /**
    * Test method for {@link ZXMLAccountRepository#prefs}<br>
    * Validates that the prefs request is executed.
    *
    * @throws Exception If there are issues testing
    */
   @Test
   public void testPrefs() throws Exception {
       final ZXMLAccountRepository repository = PowerMock
           .createPartialMockForAllMethodsExcept(ZXMLAccountRepository.class, "prefs");

       // expect to create a zimbra soap context
       GQLAuthUtilities.getZimbraSoapContext(rctxt);
       PowerMock.expectLastCall().andReturn(mockZsc);
       // expect to unmarshall a request
       XMLDocumentUtilities.toElement(anyObject());
       PowerMock.expectLastCall().andReturn(mockRequest);
       // expect to execute an element on the GetPrefs document handler
       expect(XMLDocumentUtilities
               .executeDocument(anyObject(GetPrefs.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
           .andReturn(null);

       PowerMock.replay(GQLAuthUtilities.class);
       PowerMock.replay(XMLDocumentUtilities.class);

       repository.prefs(rctxt, Collections.emptyList());

       PowerMock.verify(GQLAuthUtilities.class);
       PowerMock.verify(XMLDocumentUtilities.class);
   }

   /**
    * Test method for {@link ZXMLAccountRepository#prefsModify}<br>
    * Validates that the modify prefs request is executed.
    *
    * @throws Exception If there are issues testing
    */
   @Test
   public void testPrefsModify() throws Exception {
       final ZXMLAccountRepository repository = PowerMock
           .createPartialMockForAllMethodsExcept(ZXMLAccountRepository.class, "prefsModify");

       // expect to create a zimbra soap context
       GQLAuthUtilities.getZimbraSoapContext(rctxt);
       PowerMock.expectLastCall().andReturn(mockZsc);
       // expect to unmarshall a request
       XMLDocumentUtilities.toElement(anyObject());
       PowerMock.expectLastCall().andReturn(mockRequest);
       // expect to execute an element on the ModifyPrefs document handler
       expect(XMLDocumentUtilities
               .executeDocument(anyObject(ModifyPrefs.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
           .andReturn(null);

       PowerMock.replay(GQLAuthUtilities.class);
       PowerMock.replay(XMLDocumentUtilities.class);

       repository.prefsModify(rctxt, Collections.emptyList());

       PowerMock.verify(GQLAuthUtilities.class);
       PowerMock.verify(XMLDocumentUtilities.class);
   }

   /**
    * Test method for {@link ZXMLAccountRepository#changePassword}<br>
    * Validates that the change password request is executed.
    *
    * @throws Exception If there are issues testing
    */
   @Test
   public void testChangePassword() throws Exception {
       final ZXMLAccountRepository repository = PowerMock
           .createPartialMockForAllMethodsExcept(ZXMLAccountRepository.class, "changePassword");

       // expect to create a zimbra soap context
       GQLAuthUtilities.getZimbraSoapContext(rctxt);
       PowerMock.expectLastCall().andReturn(mockZsc);
       // expect to unmarshall a request
       XMLDocumentUtilities.toElement(anyObject());
       PowerMock.expectLastCall().andReturn(mockRequest);
       // expect to execute an element on the ChangePassword document handler
       expect(XMLDocumentUtilities
               .executeDocument(anyObject(ChangePassword.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
           .andReturn(mockResponse);
       // expect to marshall a response
       XMLDocumentUtilities.fromElement(eq(mockResponse), anyObject());
       PowerMock.expectLastCall().andReturn(null);

       PowerMock.replay(GQLAuthUtilities.class);
       PowerMock.replay(XMLDocumentUtilities.class);

       repository.changePassword(rctxt, null, "old-pass", "new-pass", "vhost");

       PowerMock.verify(GQLAuthUtilities.class);
       PowerMock.verify(XMLDocumentUtilities.class);
   }

   /**
    * Test method for {@link ZXMLAccountRepository#whiteBlackList}<br>
    * Validates that the white black list request is executed.
    *
    * @throws Exception If there are issues testing
    */
   @Test
   public void testWhiteBlackList() throws Exception {
       final ZXMLAccountRepository repository = PowerMock
           .createPartialMockForAllMethodsExcept(ZXMLAccountRepository.class, "whiteBlackList");

       // expect to create a zimbra soap context
       GQLAuthUtilities.getZimbraSoapContext(rctxt);
       PowerMock.expectLastCall().andReturn(mockZsc);
       // expect to unmarshall a request
       XMLDocumentUtilities.toElement(anyObject());
       PowerMock.expectLastCall().andReturn(mockRequest);
       // expect to execute an element on the GetWhiteBlackList document handler
       expect(XMLDocumentUtilities
               .executeDocument(anyObject(GetWhiteBlackList.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
           .andReturn(null);

       PowerMock.replay(GQLAuthUtilities.class);
       PowerMock.replay(XMLDocumentUtilities.class);

       repository.whiteBlackList(rctxt);

       PowerMock.verify(GQLAuthUtilities.class);
       PowerMock.verify(XMLDocumentUtilities.class);
   }

   /**
    * Test method for {@link ZXMLAccountRepository#whiteBlackListModify}<br>
    * Validates that the modify white black list request is executed.
    *
    * @throws Exception If there are issues testing
    */
   @Test
   public void testWhiteBlackListModify() throws Exception {
       final ZXMLAccountRepository repository = PowerMock
           .createPartialMockForAllMethodsExcept(ZXMLAccountRepository.class, "whiteBlackListModify");

       // expect to create a zimbra soap context
       GQLAuthUtilities.getZimbraSoapContext(rctxt);
       PowerMock.expectLastCall().andReturn(mockZsc);
       // expect to unmarshall a request
       XMLDocumentUtilities.toElement(anyObject());
       PowerMock.expectLastCall().andReturn(mockRequest);
       // expect to execute an element on the ModifyWhiteBlackList document handler
       expect(XMLDocumentUtilities
               .executeDocument(anyObject(ModifyWhiteBlackList.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
           .andReturn(null);

       PowerMock.replay(GQLAuthUtilities.class);
       PowerMock.replay(XMLDocumentUtilities.class);

       repository.whiteBlackListModify(rctxt, Collections.emptyList(), Collections.emptyList());

       PowerMock.verify(GQLAuthUtilities.class);
       PowerMock.verify(XMLDocumentUtilities.class);
   }

   /**
    * Test method for {@link ZXMLAccountRepository#signatures}<br>
    * Validates that the signatures request is executed.
    *
    * @throws Exception If there are issues testing
    */
   @Test
   public void testSignatures() throws Exception {
       final ZXMLAccountRepository repository = PowerMock
           .createPartialMockForAllMethodsExcept(ZXMLAccountRepository.class, "signatures");

       // expect to create a zimbra soap context
       GQLAuthUtilities.getZimbraSoapContext(rctxt);
       PowerMock.expectLastCall().andReturn(mockZsc);
       // expect to unmarshall a request
       XMLDocumentUtilities.toElement(anyObject());
       PowerMock.expectLastCall().andReturn(mockRequest);
       // expect to execute an element on the GetSignatures document handler
       expect(XMLDocumentUtilities
               .executeDocument(anyObject(GetSignatures.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
           .andReturn(mockResponse);
       // expect to marshall a response
       XMLDocumentUtilities.fromElement(eq(mockResponse), anyObject());
       PowerMock.expectLastCall().andReturn(null);

       PowerMock.replay(GQLAuthUtilities.class);
       PowerMock.replay(XMLDocumentUtilities.class);

       repository.signatures(rctxt);

       PowerMock.verify(GQLAuthUtilities.class);
       PowerMock.verify(XMLDocumentUtilities.class);
   }

   /**
    * Test method for {@link ZXMLAccountRepository#signatureCreate}<br>
    * Validates that the create signature request is executed.
    *
    * @throws Exception If there are issues testing
    */
   @Test
   public void testSignatureCreate() throws Exception {
       final CreateSignatureResponse mockSigResponse = EasyMock.createMock(CreateSignatureResponse.class);
       final ZXMLAccountRepository repository = PowerMock
           .createPartialMockForAllMethodsExcept(ZXMLAccountRepository.class, "signatureCreate");

       // expect to create a zimbra soap context
       GQLAuthUtilities.getZimbraSoapContext(rctxt);
       PowerMock.expectLastCall().andReturn(mockZsc);
       // expect to unmarshall a request
       XMLDocumentUtilities.toElement(anyObject());
       PowerMock.expectLastCall().andReturn(mockRequest);
       // expect to execute an element on the CreateSignature document handler
       expect(XMLDocumentUtilities
               .executeDocument(anyObject(CreateSignature.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
           .andReturn(mockResponse);
       // expect to marshall a response
       XMLDocumentUtilities.fromElement(eq(mockResponse), anyObject());
       PowerMock.expectLastCall().andReturn(mockSigResponse);

       PowerMock.replay(GQLAuthUtilities.class);
       PowerMock.replay(XMLDocumentUtilities.class);

       repository.signatureCreate(rctxt, null);

       PowerMock.verify(GQLAuthUtilities.class);
       PowerMock.verify(XMLDocumentUtilities.class);
   }

   /**
    * Test method for {@link ZXMLAccountRepository#signatureModify}<br>
    * Validates that the modify signature request is executed.
    *
    * @throws Exception If there are issues testing
    */
   @Test
   public void testSignatureModify() throws Exception {
       final Signature mockSignature = EasyMock.createMock(Signature.class);
       final ZXMLAccountRepository repository = PowerMock
           .createPartialMockForAllMethodsExcept(ZXMLAccountRepository.class, "signatureModify");

       // expect to create a zimbra soap context
       GQLAuthUtilities.getZimbraSoapContext(rctxt);
       PowerMock.expectLastCall().andReturn(mockZsc);
       // expect to unmarshall a request
       XMLDocumentUtilities.toElement(anyObject());
       PowerMock.expectLastCall().andReturn(mockRequest);
       // expect to execute an element on the ModifySignature document handler
       expect(XMLDocumentUtilities
               .executeDocument(anyObject(ModifySignature.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
           .andReturn(mockResponse);

       PowerMock.replay(GQLAuthUtilities.class);
       PowerMock.replay(XMLDocumentUtilities.class);

       repository.signatureModify(rctxt, "sig-id", mockSignature);

       PowerMock.verify(GQLAuthUtilities.class);
       PowerMock.verify(XMLDocumentUtilities.class);
   }

   /**
    * Test method for {@link ZXMLAccountRepository#signatureDelete}<br>
    * Validates that the delete signature request is executed.
    *
    * @throws Exception If there are issues testing
    */
   @Test
   public void testSignatureDelete() throws Exception {
       final ZXMLAccountRepository repository = PowerMock
           .createPartialMockForAllMethodsExcept(ZXMLAccountRepository.class, "signatureDelete");

       // expect to create a zimbra soap context
       GQLAuthUtilities.getZimbraSoapContext(rctxt);
       PowerMock.expectLastCall().andReturn(mockZsc);
       // expect to unmarshall a request
       XMLDocumentUtilities.toElement(anyObject());
       PowerMock.expectLastCall().andReturn(mockRequest);
       // expect to execute an element on the DeleteSignature document handler
       expect(XMLDocumentUtilities
               .executeDocument(anyObject(DeleteSignature.class), eq(mockZsc), eq(mockRequest), eq(rctxt)))
           .andReturn(mockResponse);

       PowerMock.replay(GQLAuthUtilities.class);
       PowerMock.replay(XMLDocumentUtilities.class);

       repository.signatureDelete(rctxt, new NameId(null, "sig-id"));

       PowerMock.verify(GQLAuthUtilities.class);
       PowerMock.verify(XMLDocumentUtilities.class);
   }
}
