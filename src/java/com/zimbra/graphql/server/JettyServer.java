package com.zimbra.graphql.server;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.zimbra.common.util.ZimbraLog;
import com.zimbra.graphql.resources.GraphQLResource;
import com.zimbra.graphql.utilities.Configuration;
import com.zimbra.graphql.utilities.GQLConstants;

public class JettyServer {

	/**
	 * Jetty server instance.
	 */
	protected Server server;

	/**
	 * Application config (non-client specific).
	 */
	protected Configuration config;

	/**
	 * Application main.
	 *
	 * @param args Program input
	 */
	public static void main(String[] args) {
		final JettyServer jettyServer = new JettyServer();
		jettyServer.setup();
		ZimbraLog.extensions.info("Starting jetty server.");
		jettyServer.run();
	}

	/**
	 * JettyServer constructor.<br>
	 * Initializes the jetty server with config.
	 */
	public JettyServer() {
		// load default config
		config = new Configuration();

		// setup ZimbraLog
		ZimbraLog.toolSetupLog4jConsole(config.getString(GQLConstants.LC_GQL_LOG_LEVEL, GQLConstants.DEFAULT_LOG_LEVEL), true, false);
	}

	/**
	 * Configure Jetty server.
	 */
	protected void setup() {
		final ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath(StringUtils.defaultIfEmpty(config.getString(GQLConstants.LC_GQL_SERVER_CONTEXT_PATH), GQLConstants.DEFAULT_SERVER_CONTEXT_PATH));
		final ServletHolder jerseyServlet = context.addServlet(GraphQLResource.class, GQLConstants.DEFAULT_SERVER_CONTEXT_PATH);
		jerseyServlet.setInitOrder(0);
		jerseyServlet.setInitParameter("javax.ws.rs.Application", "com.zimbra.graphql.server.JettyServer");

		server = new Server(config.getInt(GQLConstants.LC_GQL_SERVER_PORT, GQLConstants.DEFAULT_SERVER_PORT));
		server.setHandler(context);
	}

	/**
	 * Runs the configured jetty server.
	 */
	protected void run() {
		try {
			server.start();
			server.join();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
