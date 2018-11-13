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
package com.zimbra.graphql.resources;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponseWrapper;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.GQLUtilities;


/**
 * Test class for {@link GQLServlet}.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({GQLAuthUtilities.class, GQLUtilities.class})
public class GQLServletTest {

    /**
     * Mock http request for testing.
     */
    protected HttpServletRequestWrapper mockRequest;

    /**
     * Mock http response for testing.
     */
    protected HttpServletResponseWrapper mockResponse;

    /**
     * Object mapper.
     */
    protected ObjectMapper mapper = GQLUtilities.createDefaultMapper();

    /**
     * Setup for tests.
     *
     * @throws Exception If there are issues mocking
     */
    @Before
    public void setUp() throws Exception {
        mockRequest = EasyMock.createMock(HttpServletRequestWrapper.class);
        mockResponse = EasyMock.createMock(HttpServletResponseWrapper.class);

        PowerMock.mockStaticPartial(GQLUtilities.class, "decodeStream");
    }


    /**
     * Test method for {@link GQLServlet#doGet}<br>
     * Validates that the doGraphQLRequest is executed and response is sent.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testDoGet() throws Exception {
        final String query = "fake { name }";
        final String operationName = null;
        final String variables = null;
        final Map<String, Object> gqlResponse = new HashMap<String, Object>();
        final GQLServlet gqlServlet = PowerMock
            .createPartialMockForAllMethodsExcept(GQLServlet.class, "doGet");

        // expect to fetch the 3 request parameters
        expect(mockRequest.getParameter("query")).andReturn(query);
        expect(mockRequest.getParameter("operationName")).andReturn(operationName);
        expect(mockRequest.getParameter("variables")).andReturn(variables);
        // expect to execute the graphql request
        expect(gqlServlet.doGraphQLRequest(mockRequest, mockResponse, query, operationName, new HashMap<String, Object>())).andReturn(gqlResponse);
        gqlServlet.sendResponse(mockResponse, gqlResponse);
        PowerMock.expectLastCall().once();

        replay(gqlServlet);
        replay(mockRequest);

        gqlServlet.doGet(mockRequest, mockResponse);

        verify(gqlServlet);
        verify(mockRequest);
    }

    /**
     * Test method for {@link GQLServlet#doPost}<br>
     * Validates that the doGraphQLRequest is executed and response is sent.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testDoPost() throws Exception {
        final String query = "fake { name }";
        final String body = String.format("{\"query\": \"%s\"}", query);
        final Map<String, Object> gqlResponse = new HashMap<String, Object>();
        final JsonNode json = mapper.readTree(body.getBytes());
        final GQLServlet gqlServlet = PowerMock
            .createPartialMockForAllMethodsExcept(GQLServlet.class, "doPost");

        // expect to fetch request input stream
        expect(mockRequest.getInputStream()).andReturn(null);
        // expect to decode the request stream
        GQLUtilities.decodeStream(null, 0);
        PowerMock.expectLastCall().andReturn(body.getBytes());
        // expect to read the bytes into json
        expect(gqlServlet.readBytes(anyObject())).andReturn(json);
        // expect to execute the graphql request
        expect(gqlServlet.doGraphQLRequest(mockRequest, mockResponse, query, null, new HashMap<String, Object>())).andReturn(gqlResponse);
        gqlServlet.sendResponse(mockResponse, gqlResponse);
        PowerMock.expectLastCall().once();

        replay(gqlServlet);
        replay(mockRequest);
        PowerMock.replay(GQLUtilities.class);

        gqlServlet.doPost(mockRequest, mockResponse);

        verify(gqlServlet);
        verify(mockRequest);
        PowerMock.verify(GQLUtilities.class);
    }

    /**
     * Test method for {@link GQLServlet#doPost}<br>
     * Validates that the operationName is used when specified.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testDoPostOperationName() throws Exception {
        final String query = "query TestName { fake { name } }";
        final String operationName = "TestName";
        final String body = String.format("{\"query\": \"%s\", \"operationName\": \"%s\"}", query, operationName);
        final Map<String, Object> gqlResponse = new HashMap<String, Object>();
        final JsonNode json = mapper.readTree(body.getBytes());
        final GQLServlet gqlServlet = PowerMock
            .createPartialMockForAllMethodsExcept(GQLServlet.class, "doPost");

        // expect to fetch request input stream
        expect(mockRequest.getInputStream()).andReturn(null);
        // expect to decode the request stream
        GQLUtilities.decodeStream(null, 0);
        PowerMock.expectLastCall().andReturn(body.getBytes());
        // expect to read the bytes into json
        expect(gqlServlet.readBytes(anyObject())).andReturn(json);
        // expect to execute the graphql request
        expect(gqlServlet.doGraphQLRequest(mockRequest, mockResponse, query, operationName, new HashMap<String, Object>())).andReturn(gqlResponse);
        gqlServlet.sendResponse(mockResponse, gqlResponse);
        PowerMock.expectLastCall().once();

        replay(gqlServlet);
        replay(mockRequest);
        PowerMock.replay(GQLUtilities.class);

        gqlServlet.doPost(mockRequest, mockResponse);

        verify(gqlServlet);
        verify(mockRequest);
        PowerMock.verify(GQLUtilities.class);
    }

    /**
     * Test method for {@link GQLServlet#doPost}<br>
     * Validates that the variables argument is used when specified.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testDoPostVariables() throws Exception {
        final String query = "fake { name }";
        final String variables = "{\\\"test\\\": \\\"some-value\\\"}";
        final byte[] bodyBytes = String.format("{\"query\": \"%s\", \"variables\": \"%s\"}", query, variables).trim().getBytes();
        final Map<String, Object> variablesMap = new HashMap<String, Object>();
        variablesMap.put("test", "some-value");
        final Map<String, Object> gqlResponse = new HashMap<String, Object>();
        final GQLServlet gqlServlet = PowerMock
            .createPartialMockForAllMethodsExcept(GQLServlet.class, "doPost");

        // expect to fetch the request input stream
        expect(mockRequest.getInputStream()).andReturn(null);
        // expect to decode the request stream
        GQLUtilities.decodeStream(null, 0);
        PowerMock.expectLastCall().andReturn(bodyBytes);
        // expect to read the bytes into json
        expect(gqlServlet.readBytes(bodyBytes)).andReturn(mapper.readTree(bodyBytes));
        // expect to deserialize the variables string
        expect(gqlServlet.deserializeVariables(anyObject())).andReturn(variablesMap);
        // expect to execute the graphql request
        expect(gqlServlet.doGraphQLRequest(mockRequest, mockResponse, query, null, variablesMap)).andReturn(gqlResponse);
        gqlServlet.sendResponse(mockResponse, gqlResponse);
        PowerMock.expectLastCall().once();

        replay(gqlServlet);
        replay(mockRequest);
        PowerMock.replay(GQLUtilities.class);

        gqlServlet.doPost(mockRequest, mockResponse);

        verify(gqlServlet);
        verify(mockRequest);
        PowerMock.verify(GQLUtilities.class);
    }
}
