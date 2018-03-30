package com.zimbra.graphql.resources;

import java.io.IOException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.zimbra.common.util.ZimbraLog;
import com.zimbra.cs.extension.ExtensionHttpHandler;
import com.zimbra.graphql.utilities.GQLConstants;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.execution.instrumentation.Instrumentation;
import graphql.execution.instrumentation.NoOpInstrumentation;
import graphql.introspection.IntrospectionQuery;
import graphql.schema.GraphQLSchema;
import graphql.servlet.DefaultExecutionStrategyProvider;
import graphql.servlet.DefaultGraphQLContextBuilder;
import graphql.servlet.DefaultGraphQLErrorHandler;
import graphql.servlet.DefaultGraphQLRootObjectBuilder;
import graphql.servlet.DefaultGraphQLSchemaProvider;
import graphql.servlet.ExecutionStrategyProvider;
import graphql.servlet.GraphQLContext;
import graphql.servlet.GraphQLContextBuilder;
import graphql.servlet.GraphQLErrorHandler;
import graphql.servlet.GraphQLRootObjectBuilder;
import graphql.servlet.GraphQLSchemaProvider;
import graphql.servlet.GraphQLServletListener;

/**
 * ZGraphQLServletBase class.<br>
 * Implements the GraphQLServlet functionality while extending ExtensionHttpHandler.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.resources
 * @copyright Copyright © 2018
 */
public abstract class ZGraphQLServletBase extends ExtensionHttpHandler {

    private final GraphQLSchemaProvider schemaProvider;

    private final ExecutionStrategyProvider executionStrategyProvider;

    private final Instrumentation instrumentation;

    private final GraphQLErrorHandler errorHandler;

    private final GraphQLContextBuilder contextBuilder;

    private final GraphQLRootObjectBuilder rootObjectBuilder;

    private final List<GraphQLServletListener> listeners;

    private final ServletFileUpload fileUpload;

    private final RequestHandler getHandler;

    private final RequestHandler postHandler;

    private final ObjectMapper mapper;

    protected abstract GraphQLSchema buildSchema();

    /**
     * Constructor.<br>
     * Sets up default properties and the get/post handlers.
     */
    public ZGraphQLServletBase() {
        ZimbraLog.extensions.info("Starting zm-gql base servlet.");
        mapper = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        schemaProvider = new DefaultGraphQLSchemaProvider(buildSchema());
        executionStrategyProvider = new DefaultExecutionStrategyProvider();
        instrumentation = NoOpInstrumentation.INSTANCE;
        errorHandler = new DefaultGraphQLErrorHandler();
        contextBuilder = new DefaultGraphQLContextBuilder();
        rootObjectBuilder = new DefaultGraphQLRootObjectBuilder();
        listeners = new ArrayList<GraphQLServletListener>();
        fileUpload = new ServletFileUpload(new DiskFileItemFactory());

        this.getHandler = (request, response) -> {
            final GraphQLContext context = createContext(Optional.of(request), Optional.of(response));
            final Object rootObject = createRootObject(Optional.of(request), Optional.of(response));
            String path = request.getPathInfo();
            if (path == null) {
                path = request.getServletPath();
            }
            if (path.contentEquals("/schema.json")) {
                query(IntrospectionQuery.INTROSPECTION_QUERY, null, new HashMap<>(), getSchemaProvider().getSchema(request), request, response, context, rootObject);
            } else {
                if (request.getParameter("query") != null) {
                    final Map<String, Object> variables = new HashMap<>();
                    if (request.getParameter("variables") != null) {
                        variables.putAll(deserializeVariables(request.getParameter("variables")));
                    }
                    String operationName = null;
                    if (request.getParameter("operationName") != null) {
                        operationName = request.getParameter("operationName");
                    }
                    query(request.getParameter("query"), operationName, variables, getSchemaProvider().getReadOnlySchema(request), request, response, context, rootObject);
                } else {
                    response.setStatus(GQLConstants.STATUS_BAD_REQUEST);
                    ZimbraLog.extensions.info("Bad GET request: path was not \"/schema.json\" or no query variable named \"query\" given");
                }
            }
        };

        this.postHandler = (request, response) -> {
            final GraphQLContext context = createContext(Optional.of(request), Optional.of(response));
            final Object rootObject = createRootObject(Optional.of(request), Optional.of(response));
            GraphQLRequest graphQLRequest = null;

            try {
                InputStream inputStream = null;

                if (ServletFileUpload.isMultipartContent(request)) {
                    final Map<String, List<FileItem>> fileItems = fileUpload.parseParameterMap(request);

                    if (fileItems.containsKey("graphql")) {
                        final Optional<FileItem> graphqlItem = getFileItem(fileItems, "graphql");
                        if (graphqlItem.isPresent()) {
                            inputStream = graphqlItem.get().getInputStream();
                        }

                    } else if (fileItems.containsKey("query")) {
                        final Optional<FileItem> queryItem = getFileItem(fileItems, "query");
                        if (queryItem.isPresent()) {
                            graphQLRequest = new GraphQLRequest();
                            graphQLRequest.setQuery(new String(queryItem.get().get()));

                            final Optional<FileItem> operationNameItem = getFileItem(fileItems, "operationName");
                            if (operationNameItem.isPresent()) {
                                graphQLRequest.setOperationName(new String(operationNameItem.get().get()).trim());
                            }

                            final Optional<FileItem> variablesItem = getFileItem(fileItems, "variables");
                            if (variablesItem.isPresent()) {
                                final String variables = new String(variablesItem.get().get());
                                if (!variables.isEmpty()) {
                                    graphQLRequest.setVariables(deserializeVariables(variables));
                                }
                            }
                        }
                    }

                    if (inputStream == null && graphQLRequest == null) {
                        response.setStatus(GQLConstants.STATUS_BAD_REQUEST);
                        ZimbraLog.extensions.info("Bad POST multipart request: no part named \"graphql\" or \"query\"");
                        return;
                    }

                    context.setFiles(Optional.of(fileItems));

                } else {
                    // this is not a multipart request
                    inputStream = request.getInputStream();
                }

                if (graphQLRequest == null) {
                    graphQLRequest = mapper.readValue(inputStream, GraphQLRequest.class);
                }

            } catch (final Exception e) {
                ZimbraLog.extensions.info("Bad POST request: parsing failed", e);
                response.setStatus(GQLConstants.STATUS_BAD_REQUEST);
                return;
            }

            Map<String,Object> variables = graphQLRequest.getVariables();
            if (variables == null) {
                variables = new HashMap<>();
            }

            query(graphQLRequest.getQuery(), graphQLRequest.getOperationName(), variables, getSchemaProvider().getSchema(request), request, response, context, rootObject);
        };
    }

    /**
     * @return The schema provider
     */
    protected GraphQLSchemaProvider getSchemaProvider() {
        return schemaProvider;
    }

    /**
     * Creates a grqphql context.
     *
     * @param request The request
     * @param response The response
     * @return A graphql context
     */
    protected GraphQLContext createContext(Optional<HttpServletRequest> request, Optional<HttpServletResponse> response) {
        return this.contextBuilder.build(request, response);
    }

    /**
     * Creates a root object.
     *
     * @param request The request
     * @param response The response
     * @return An object
     */
    protected Object createRootObject(Optional<HttpServletRequest> request, Optional<HttpServletResponse> response) {
        return this.rootObjectBuilder.build(request, response);
    }

    /**
     * @return The execution strategy provider
     */
    protected ExecutionStrategyProvider getExecutionStrategyProvider() {
        return executionStrategyProvider;
    }

    /**
     * @return The instrumentation
     */
    protected Instrumentation getInstrumentation() {
        return instrumentation;
    }

    /**
     * Transforms the input if needed.<br>
     * Currently just returns the variables as is.
     *
     * @param schema The schema
     * @param query The query in question
     * @param variables The variables to transform
     * @return Transformed variables
     */
    protected Map<String, Object> transformVariables(GraphQLSchema schema, String query, Map<String, Object> variables) {
        return variables;
    }

    /**
     * @return The error handler
     */
    protected GraphQLErrorHandler getGraphQLErrorHandler() {
        return errorHandler;
    }

    /**
     * Handles requests.
     *
     * @param request The request
     * @param response The response
     * @param handler The request handler (post, get)
     */
    private void doRequest(HttpServletRequest request, HttpServletResponse response, RequestHandler handler) {
        final List<GraphQLServletListener.RequestCallback> requestCallbacks = runListeners(l -> l.onRequest(request, response));

        try {
            handler.handle(request, response);
            runCallbacks(requestCallbacks, c -> c.onSuccess(request, response));
        } catch (final Throwable t) {
            response.setStatus(500);
            ZimbraLog.extensions.error("Error executing GraphQL request!", t);
            runCallbacks(requestCallbacks, c -> c.onError(request, response, t));
        } finally {
            runCallbacks(requestCallbacks, c -> c.onFinally(request, response));
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doRequest(req, resp, getHandler);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doRequest(req, resp, postHandler);
    }

    private Optional<FileItem> getFileItem(Map<String, List<FileItem>> fileItems, String name) {
        final List<FileItem> items = fileItems.get(name);
        if(items == null || items.isEmpty()) {
            return Optional.empty();
        }

        return items.stream().findFirst();
    }

    /**
     * Instantiates an instance of GraphQL using the app's schema.
     *
     * @param schema The schema to use
     * @return GraphQL instance
     */
    private GraphQL newGraphQL(GraphQLSchema schema) {
        final ExecutionStrategyProvider executionStrategyProvider = getExecutionStrategyProvider();
        return GraphQL.newGraphQL(schema)
            .queryExecutionStrategy(executionStrategyProvider.getQueryExecutionStrategy())
            .mutationExecutionStrategy(executionStrategyProvider.getMutationExecutionStrategy())
            .subscriptionExecutionStrategy(executionStrategyProvider.getSubscriptionExecutionStrategy())
            .instrumentation(getInstrumentation())
            .build();
    }

    /**
     * Performs a query.
     *
     * @param query The query
     * @param operationName The op
     * @param variables Variables to use
     * @param schema The app schema
     * @param req The request
     * @param resp The response
     * @param context The context
     * @param rootObject The root object
     * @throws IOException If there are issues running the query
     */
    private void query(String query, String operationName, Map<String, Object> variables, GraphQLSchema schema, HttpServletRequest req, HttpServletResponse resp, GraphQLContext context, Object rootObject) throws IOException {
        if (operationName != null && operationName.isEmpty()) {
            query(query, null, variables, schema, req, resp, context, rootObject);
        } else if (Subject.getSubject(AccessController.getContext()) == null && context.getSubject().isPresent()) {
            Subject.doAs(context.getSubject().get(), (PrivilegedAction<Void>) () -> {
                try {
                    query(query, operationName, variables, schema, req, resp, context, rootObject);
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
                return null;
            });
        } else {
            final List<GraphQLServletListener.OperationCallback> operationCallbacks = runListeners(l -> l.onOperation(context, operationName, query, variables));

            final ExecutionResult executionResult = newGraphQL(schema).execute(new ExecutionInput(query, operationName, context, rootObject, transformVariables(schema, query, variables)));
            final List<GraphQLError> errors = executionResult.getErrors();
            final Object data = executionResult.getData();

            final String response = mapper.writeValueAsString(createResultFromDataAndErrors(data, errors));

            resp.setContentType(GQLConstants.ENCODING);
            resp.setStatus(GQLConstants.STATUS_OK);
            resp.getWriter().write(response);

            if(getGraphQLErrorHandler().errorsPresent(errors)) {
                runCallbacks(operationCallbacks, c -> c.onError(context, operationName, query, variables, data, errors));
            } else {
                runCallbacks(operationCallbacks, c -> c.onSuccess(context, operationName, query, variables, data));
            }

            runCallbacks(operationCallbacks, c -> c.onFinally(context, operationName, query, variables, data));
        }
    }

    /**
     * Creates an object structured with data|errors.
     *
     * @param data The data to respond with
     * @param errors The errors to respond with
     * @return The data and error object
     */
    private Map<String, Object> createResultFromDataAndErrors(Object data, List<GraphQLError> errors) {

        final Map<String, Object> result = new HashMap<>();
        result.put("data", data);

        if (getGraphQLErrorHandler().errorsPresent(errors)) {
            result.put("errors", getGraphQLErrorHandler().processErrors(errors));
        }

        return result;
    }

    /**
     * Executes the listeners for an action.
     *
     * @param action The action
     * @return
     */
    private <R> List<R> runListeners(Function<? super GraphQLServletListener, R> action) {
        if (listeners == null) {
            return Collections.emptyList();
        }

        return listeners.stream()
            .map(listener -> {
                try {
                    return action.apply(listener);
                } catch (final Throwable t) {
                    ZimbraLog.extensions.error("Error running listener: {}", listener, t);
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    /**
     * Executes the callbacks for an action.
     *
     * @param callbacks The callbacks to execute
     * @param action The action
     */
    private <T> void runCallbacks(List<T> callbacks, Consumer<T> action) {
        callbacks.forEach(callback -> {
            try {
                action.accept(callback);
            } catch (final Throwable t) {
                ZimbraLog.extensions.error("Error running callback: {}", callback, t);
            }
        });
    }

    protected class VariablesDeserializer extends JsonDeserializer<Map<String, Object>> {
        @Override
        public Map<String, Object> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return deserializeVariablesObject(p.readValueAs(Object.class));
        }
    }

    private Map<String, Object> deserializeVariables(String variables) {
        try {
            return deserializeVariablesObject(mapper.readValue(variables, Object.class));
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

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
            throw new RuntimeException("variables should be either an object or a string");
        }
    }

    protected static class GraphQLRequest {
        private String query;
        @JsonDeserialize(using = ZGraphQLServletBase.VariablesDeserializer.class)
        private Map<String, Object> variables = new HashMap<>();
        private String operationName;

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public Map<String, Object> getVariables() {
            return variables;
        }

        public void setVariables(Map<String, Object> variables) {
            this.variables = variables;
        }

        public String getOperationName() {
            return operationName;
        }

        public void setOperationName(String operationName) {
            this.operationName = operationName;
        }
    }

    protected interface RequestHandler extends BiConsumer<HttpServletRequest, HttpServletResponse> {
        @Override
        default void accept(HttpServletRequest request, HttpServletResponse response) {
            try {
                handle(request, response);
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        }

        void handle(HttpServletRequest request, HttpServletResponse response) throws Exception;
    }

}
