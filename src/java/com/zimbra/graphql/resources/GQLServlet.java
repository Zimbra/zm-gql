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

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.zimbra.common.localconfig.KnownKey;
import com.zimbra.common.localconfig.LC;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zimbra.common.service.ServiceException;
import com.zimbra.common.util.ZimbraLog;
import com.zimbra.cs.extension.ExtensionHttpHandler;
import com.zimbra.graphql.errors.GQLError;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.GQLConstants;
import com.zimbra.graphql.utilities.GQLUtilities;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphqlErrorHelper;
import graphql.execution.UnknownOperationException;
import org.apache.http.client.methods.HttpPost;

/**
 * The GQLServlet class.<br>
 * Application servlet for http requests, handles request parsing.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.resources
 * @copyright Copyright © 2018
 */
public class GQLServlet extends ExtensionHttpHandler {
    public final KnownKey zimbra_gql_enabled;

    /**
     * Object mapper.
     */
    protected final ObjectMapper mapper = GQLUtilities.createDefaultMapper();

    /**
     * GraphQL instance.
     */
    protected final GraphQL graphql;
    private String is_zimbra_gql_enabled;

    /**
     * Constructs an instance and sets up gql object with schema.
     */
    public GQLServlet() {
        graphql = GraphQL.newGraphQL(GQLSchemaBuilder.newInstance().build())
                .build();
        zimbra_gql_enabled = new KnownKey("zimbra_gql_enable_dangerous_deprecated_get_method");
        zimbra_gql_enabled.setDefault("false");
        is_zimbra_gql_enabled = StringUtils.defaultIfEmpty(LC.get("zimbra_gql_enable_dangerous_deprecated_get_method"), "false");
    }

    @Override
    public String getPath() {
        return GQLConstants.DEFAULT_SERVER_PATH.getValue();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        if ("true".equals(is_zimbra_gql_enabled)) {
            ZimbraLog.extensions.debug("Handling GET request.");
            // seek query param (string)
            final String query = req.getParameter("query");
            // seek operationName param (string)
            final String operationName = req.getParameter("operationName");
            // seek variables param (map {string -> object})
            final Map<String, Object> variables = new HashMap<String, Object>();
            final String rawVariables = req.getParameter("variables");
            try {
                if (!StringUtils.isEmpty(rawVariables)
                        && !StringUtils.equalsIgnoreCase(rawVariables, "null")) {
                    variables.putAll(deserializeVariables(rawVariables));
                }
                final Map<String, Object> result = doGraphQLRequest(req, resp, query, operationName, variables);
                sendResponse(resp, result);
            } catch (final ServiceException | UnknownOperationException e) {
                sendError(resp, e);
                return;
            }
        }
        else {
            resp.setHeader(org.apache.http.HttpHeaders.ALLOW, HttpPost.METHOD_NAME);
            resp.sendError(HttpStatus.SC_METHOD_NOT_ALLOWED, "HTTP GET requests are disabled on GraphQL endpoint, to re-enable contact Zimbra Support.");
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        ZimbraLog.extensions.debug("Handling POST request.");
        String query = null;
        String operationName = null;
        Map<String, Object> variables = new HashMap<String, Object>();
        try {
            // read the body into json
            ZimbraLog.extensions.debug("Reading http body.");
            final JsonNode jsonBody = readBytes(GQLUtilities.decodeStream(req.getInputStream(), 0));
            if (jsonBody != null && !jsonBody.isNull()) {
                // seek query param (string)
                if (jsonBody.has("query")) {
                    final JsonNode rawQueryNode = jsonBody.get("query");
                    if (rawQueryNode != null && !rawQueryNode.isNull()) {
                        query = rawQueryNode.asText();
                    }
                }
                // seek operationName param (string)
                if (jsonBody.has("operationName")) {
                    final JsonNode rawOpNameNode = jsonBody.get("operationName");
                    if (rawOpNameNode != null && !rawOpNameNode.isNull()) {
                        operationName = rawOpNameNode.asText();
                    }
                }
                // seek variables param (map {string -> object})
                if (jsonBody.has("variables")) {
                    final JsonNode rawVariablesNode = jsonBody.get("variables");
                    if (rawVariablesNode != null && !rawVariablesNode.isNull()) {
                        variables = deserializeVariables(rawVariablesNode.toString());
                    }
                }
            }
            final Map<String, Object> result = doGraphQLRequest(req, resp, query, operationName,
                variables);
            sendResponse(resp, result);
        } catch (final IOException | ServiceException | UnknownOperationException e) {
            sendError(resp, e);
            return;
        }
    }

    /**
     * Executes a gql request given parameters.
     *
     * @param req The http request
     * @param resp The http response
     * @param query The query from http request
     * @param operationName The operationName from http request
     * @param variables The variables from http request
     * @return A spec-based map of the query result
     */
    protected Map<String, Object> doGraphQLRequest(HttpServletRequest req, HttpServletResponse resp,
        String query, String operationName, Map<String, Object> variables) {
        // build gql request
        ZimbraLog.extensions.debug("Building graphql execution input.");
        final ExecutionInput input = ExecutionInput.newExecutionInput()
            .query(query)
            .operationName(operationName)
            .context(GQLAuthUtilities.buildContext(req, resp))
            .variables(variables)
            .build();
        // execute
        ZimbraLog.extensions.debug("Executing graphql request.");
        final ExecutionResult executionResult = graphql.execute(input);
        // result to spec
        final Map<String, Object> result = executionResult.toSpecification();
        return result;
    }

    /**
     * Transforms an exception into an error response before sending.
     *
     * @param resp The http response
     * @param e The exception
     * @throws IOException If there are issues converting the result to json or writing out
     */
    protected void sendError(HttpServletResponse resp, Exception e) throws IOException {
        ZimbraLog.extensions.debug("An error has occurred before graphql execution : %s.",
            e.getMessage());
        ZimbraLog.extensions.debug(e);
        // result map based on specification
        // since we are not yet executing a request
        // do not handle extensions and partial data
        final Map<String, Object> result = new LinkedHashMap<String, Object>(1);
        result.put("errors", Arrays.asList(GraphqlErrorHelper.toSpecification(new GQLError(e))));
        sendResponse(resp, result);
    }

    /**
     * Writes a graphql response.
     *
     * @param resp The http response
     * @param result The graphql result to write
     * @throws IOException If there are issues converting the result to json or writing out
     */
    protected void sendResponse(HttpServletResponse resp, Map<String, Object> result)
        throws IOException {
        // print result and flush
        ZimbraLog.extensions.debug("Writing http response.");
        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        resp.getWriter().print(mapper.writeValueAsString(result));
        resp.getWriter().flush();
    }

    /**
     * Translates bytes to json.
     *
     * @param bytes The bytes to read
     * @return Json representation
     * @throws IOException If there are parsing issues
     */
    protected JsonNode readBytes(byte[] bytes) throws IOException {
        return mapper.readTree(bytes);
    }

    /**
     * Utility to deserialize variables from string.
     *
     * @param variables The variables to deserialize
     * @return A map of the variables
     * @throws ServiceException If there are issues deserializing
     */
    protected Map<String, Object> deserializeVariables(String variables) throws ServiceException {
        try {
            return deserializeVariablesObject(mapper.readValue(variables, Object.class));
        } catch (final IOException e) {
            throw ServiceException.PARSE_ERROR("Unable to deserialize variables.", e);
        }
    }

    /**
     * Utility to deserialize variables from object.
     *
     * @param variables The variables to deserialize
     * @return A map of the variables
     * @throws ServiceException If there are issues deserializing
     */
    private Map<String, Object> deserializeVariablesObject(Object variables) throws ServiceException {
        if (variables instanceof Map) {
            @SuppressWarnings("unchecked")
            final Map<String, Object> genericVariables = (Map<String, Object>) variables;
            return genericVariables;
        } else if (variables instanceof String) {
            try {
                return mapper.readValue((String) variables,
                    new TypeReference<Map<String, Object>>() { });
            } catch (final IOException e) {
                throw ServiceException.PARSE_ERROR("Unable to deserialize variables.", e);
            }
        }
        throw ServiceException.PARSE_ERROR("Unexpected type when deserializing variables.", null);
    }

}
