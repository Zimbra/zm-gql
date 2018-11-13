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

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.matches;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertNotNull;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

import org.dom4j.QName;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.Element;
import com.zimbra.common.soap.SoapProtocol;
import com.zimbra.common.util.L10nUtil;
import com.zimbra.common.util.L10nUtil.MsgKey;
import com.zimbra.cs.account.AuthToken;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.soap.DocumentHandler;
import com.zimbra.soap.ZimbraSoapContext;


/**
 * Test class for {@link GQLAuthUtilities}.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({GQLAuthUtilities.class, ZimbraSoapContext.class})
public class GQLAuthUtilitiesTest {

    /**
     * Test method for {@link GQLAuthUtilities#getZimbraSoapContext}<br>
     * Validates that credentials are valid and a ZimbraSoapContext is created.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testGetZimbraSoapContext() throws Exception {
        final String authToken = "test-token";
        final String accountId = "account-id";
        final Cookie[] cookies = new Cookie[] { new Cookie("ZM_AUTH_TOKEN", authToken) };
        final AuthToken mockToken = EasyMock.createMock(AuthToken.class);
        final HttpServletRequest mockRequest = EasyMock.createMock(HttpServletRequest.class);
        final RequestContext rctxt = new RequestContext();
        rctxt.setRawRequest(mockRequest);

        PowerMock.mockStaticPartial(GQLAuthUtilities.class, "getAuthToken", "isValidToken");

        // expect to get the accountId from auth token
        expect(mockToken.getAccountId()).andReturn(accountId);
        // expect to get the cookies from http request
        expect(mockRequest.getCookies()).andReturn(cookies);
        // expect to get the auth header from http request
        expect(mockRequest.getHeader(matches("Authorization"))).andReturn(authToken);
        // expect to create an auth token from the headers/cookies
        GQLAuthUtilities.getAuthToken(EasyMock.eq(cookies), EasyMock.anyObject());
        PowerMock.expectLastCall().andReturn(mockToken);
        // expect to validate the auth token
        GQLAuthUtilities.isValidToken(mockToken);
        PowerMock.expectLastCall().andReturn(true);
        // expect to create a new ZimbraSoapContext
        PowerMock
            .expectNew(ZimbraSoapContext.class, mockToken, accountId, SoapProtocol.Soap12, SoapProtocol.Soap12)
            .andReturn(EasyMock.createMock(ZimbraSoapContext.class));

        replay(mockToken);
        replay(mockRequest);
        PowerMock.replay(GQLAuthUtilities.class);
        PowerMock.replay(ZimbraSoapContext.class);

        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);

        assertNotNull(zsc);
        verify(mockToken);
        verify(mockRequest);
        PowerMock.verify(GQLAuthUtilities.class);
        PowerMock.verify(ZimbraSoapContext.class);
    }

    /**
     * Test method for {@link GQLAuthUtilities#getZimbraSoapContext}<br>
     * Validates that an exception is thrown is credentials are not valid.
     *
     * @throws Exception If there are issues testing
     */
    @Test(expected=ServiceException.class)
    public void testGetZimbraSoapContextInvalidToken() throws Exception {
        final String authToken = "test-token";
        final String accountId = "account-id";
        final Cookie[] cookies = new Cookie[] { new Cookie("ZM_AUTH_TOKEN", authToken) };
        final AuthToken mockToken = EasyMock.createMock(AuthToken.class);
        final HttpServletRequest mockRequest = EasyMock.createMock(HttpServletRequest.class);
        final RequestContext rctxt = new RequestContext();
        rctxt.setRawRequest(mockRequest);

        PowerMock.mockStaticPartial(GQLAuthUtilities.class, "getAuthToken", "isValidToken");

        // expect to get the accountId from auth token
        expect(mockToken.getAccountId()).andReturn(accountId);
        // expect to get the cookies from http request
        expect(mockRequest.getCookies()).andReturn(cookies);
        // expect to get the auth header from http request
        expect(mockRequest.getHeader(matches("Authorization"))).andReturn(authToken);
        // expect to create an auth token from the headers/cookies
        GQLAuthUtilities.getAuthToken(EasyMock.eq(cookies), EasyMock.anyObject());
        PowerMock.expectLastCall().andReturn(mockToken);
        // expect to validate the auth token
        GQLAuthUtilities.isValidToken(mockToken);
        PowerMock.expectLastCall().andReturn(false);


        replay(mockToken);
        replay(mockRequest);
        PowerMock.replay(GQLAuthUtilities.class);

        GQLAuthUtilities.getZimbraSoapContext(rctxt);
    }

    /**
     * Test method for {@link GQLAuthUtilities#getZimbraSoapContext}<br>
     * Validates that an exception is thrown if credentials cannot be read from request.
     *
     * @throws Exception If there are issues testing
     */
    @Test(expected=ServiceException.class)
    public void testGetZimbraSoapContextMissingOrUnreadableToken() throws Exception {
        final String authToken = "test-token";
        final String accountId = "account-id";
        final Cookie[] cookies = new Cookie[] { new Cookie("ZM_AUTH_TOKEN", authToken) };
        final AuthToken mockToken = EasyMock.createMock(AuthToken.class);
        final HttpServletRequest mockRequest = EasyMock.createMock(HttpServletRequest.class);
        final RequestContext rctxt = new RequestContext();
        rctxt.setRawRequest(mockRequest);

        PowerMock.mockStaticPartial(GQLAuthUtilities.class, "getAuthToken", "isValidToken");

        // expect to get the accountId from auth token
        expect(mockToken.getAccountId()).andReturn(accountId);
        // expect to get the cookies from http request
        expect(mockRequest.getCookies()).andReturn(cookies);
        // expect to get the auth header from http request
        expect(mockRequest.getHeader(matches("Authorization"))).andReturn(authToken);
        // expect to fail creating an auth token from the headers/cookies
        GQLAuthUtilities.getAuthToken(EasyMock.eq(cookies), EasyMock.anyObject());
        PowerMock.expectLastCall()
            .andThrow(ServiceException.PERM_DENIED(L10nUtil.getMessage(MsgKey.errMustAuthenticate)));


        replay(mockToken);
        replay(mockRequest);
        PowerMock.replay(GQLAuthUtilities.class);

        GQLAuthUtilities.getZimbraSoapContext(rctxt);
    }

    /**
     * Test method for {@link GQLAuthUtilities#getGuestZimbraSoapContext}<br>
     * Validates that a ZimbraSoapContext is created with request info.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testGetGuestZimbraSoapContext() throws Exception {
        final String userAgent = "junit";
        final String remoteAddr = "remote.addr";
        final int remotePort = 80;
        final QName qName = EasyMock.createMock(QName.class);
        final DocumentHandler handler = EasyMock.createMock(DocumentHandler.class);
        final HttpServletRequest mockRequest = EasyMock.createMock(HttpServletRequest.class);
        final RequestContext rctxt = new RequestContext();
        rctxt.setRawRequest(mockRequest);

        // expect to get the remote address from http request
        expect(mockRequest.getRemoteAddr()).andReturn(remoteAddr);
        // expect to get the remote port from http request
        expect(mockRequest.getRemotePort()).andReturn(remotePort);
        // expect to get the user agent header from http request
        expect(mockRequest.getHeader(HttpHeaders.USER_AGENT)).andReturn(userAgent);
        // expect to create a new ZimbraSoapContext
        PowerMock
            .expectNew(ZimbraSoapContext.class, anyObject(Element.class), eq(qName), eq(handler),
                anyObject(), eq(SoapProtocol.Soap12))
            .andReturn(EasyMock.createMock(ZimbraSoapContext.class));

        replay(mockRequest);
        PowerMock.replay(ZimbraSoapContext.class);

        final ZimbraSoapContext zsc = GQLAuthUtilities.getGuestZimbraSoapContext(qName, handler, rctxt);

        assertNotNull(zsc);
        PowerMock.verify(ZimbraSoapContext.class);
    }

    /**
     * Test method for {@link GQLAuthUtilities#getGuestZimbraSoapContext}<br>
     * Validates that an exception is thrown if the handler is null.
     *
     * @throws Exception If there are issues testing
     */
    @Test(expected=ServiceException.class)
    public void testGetGuestZimbraSoapContextNullHandler() throws Exception {
        GQLAuthUtilities.getGuestZimbraSoapContext(EasyMock.createMock(QName.class), null, null);
    }

    /**
     * Test method for {@link GQLAuthUtilities#getGuestZimbraSoapContext}<br>
     * Validates that an exception is thrown if the QName is null.
     *
     * @throws Exception If there are issues testing
     */
    @Test(expected=ServiceException.class)
    public void testGetGuestZimbraSoapContextNullQName() throws Exception {
        GQLAuthUtilities.getGuestZimbraSoapContext(null, EasyMock.createMock(DocumentHandler.class), null);
    }

}
