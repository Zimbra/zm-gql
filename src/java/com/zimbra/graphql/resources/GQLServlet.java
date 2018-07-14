package com.zimbra.graphql.resources;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zimbra.common.util.ZimbraLog;
import com.zimbra.cs.extension.ExtensionHttpHandler;
import com.zimbra.graphql.repositories.impl.AccountInfoRepository;
import com.zimbra.graphql.repositories.impl.ZXMLFolderRepository;
import com.zimbra.graphql.resolvers.impl.AccountInfoResolver;
import com.zimbra.graphql.resolvers.impl.FolderResolver;
import com.zimbra.graphql.schema.GQLValueMapperFactory;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.GQLUtilities;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;

public class GQLServlet extends ExtensionHttpHandler {

    /**
     * Object mapper.
     */
    protected final ObjectMapper mapper = GQLUtilities.createDefaultMapper();

    /**
     * GraphQL instance.
     */
    protected final GraphQL graphql;

    /**
     * Constructs an instance and sets up gql object with schema.
     */
    public GQLServlet() {
        graphql = GraphQL.newGraphQL(buildSchema())
            .build();
    }

    @Override
    public String getPath() {
        return "/graphql";
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        ZimbraLog.extensions.debug("Handling GET request.");
        // seek query param (string)
        final String query = req.getParameter("query");
        // seek operationName param (string)
        final String operationName = req.getParameter("operationName");
        // seek variables param (map {string -> object})
        final Map<String, Object> variables = new HashMap<String, Object>();
        final String rawVariables = req.getParameter("variables");
        if (rawVariables != null) {
            variables.putAll(deserializeVariables(rawVariables));
        }
        final Map<String, Object> result = doGraphQLRequest(req, query, operationName, variables);
        sendResponse(resp, result);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        ZimbraLog.extensions.debug("Handling POST request.");
        String query = null;
        String operationName = null;
        Map<String, Object> variables = new HashMap<String, Object>();
        // read the body into json
        final JsonNode jsonBody = mapper.readTree(GQLUtilities.decodeStream(req.getInputStream(), 4080));
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
        final Map<String, Object> result = doGraphQLRequest(req, query, operationName, variables);
        sendResponse(resp, result);
    }

    /**
     * Executes a gql request given parameters.
     *
     * @param req The http request
     * @param query The query from http request
     * @param operationName The operationName from http request
     * @param variables The variables from http request
     * @return A spec-based map of the query result
     */
    protected Map<String, Object> doGraphQLRequest(HttpServletRequest req, String query,
        String operationName, Map<String, Object> variables) {
        // build gql request
        final ExecutionInput input = ExecutionInput.newExecutionInput()
            .query(query)
            .operationName(operationName)
            .context(GQLAuthUtilities.buildContext(req))
            .variables(variables)
            .build();
        // execute
        final ExecutionResult executionResult = graphql.execute(input);
        // result to spec
        final Map<String, Object> result = executionResult.toSpecification();
        return result;
    }

    /**
     * Writes a graphql response.
     *
     * @param resp The http response
     * @param result The graphql result to write
     * @throws IOException If there are issues converting the result to json or writing out
     */
    protected void sendResponse(HttpServletResponse resp, Map<String, Object> result) throws IOException {
        // print result and flush
        resp.getWriter().print(mapper.writeValueAsString(result));
        resp.getWriter().flush();
    }

    /**
     * Wires the application schema given resolvers.<br>
     *
     * @return A wired schema
     */
    protected GraphQLSchema buildSchema() {
        final AccountInfoResolver accountInfoResolver = new AccountInfoResolver(new AccountInfoRepository());
        final FolderResolver folderResolver = new FolderResolver(new ZXMLFolderRepository());
        return new GraphQLSchemaGenerator()
            .withValueMapperFactory(new GQLValueMapperFactory(mapper))
            .withOperationsFromSingletons(accountInfoResolver, folderResolver)
            .generate();
    }

    /**
     * Utility to deserialize variables from string.
     *
     * @param variables The variables to deserialize
     * @return A map of the variables
     */
    private Map<String, Object> deserializeVariables(String variables) {
        try {
            return deserializeVariablesObject(mapper.readValue(variables, Object.class));
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Utility to deserialize variables from object.
     *
     * @param variables The variables to deserialize
     * @return A map of the variables
     */
    private Map<String, Object> deserializeVariablesObject(Object variables) {
        if (variables instanceof Map) {
            @SuppressWarnings("unchecked")
            final
            Map<String, Object> genericVariables = (Map<String, Object>) variables;
            return genericVariables;
        } else if (variables instanceof String) {
            try {
                return mapper.readValue((String) variables, new TypeReference<Map<String, Object>>() {});
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Variables should be either an object or a string");
        }
    }

}
