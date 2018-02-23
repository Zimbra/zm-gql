package com.zimbra.graphql.managers;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.zimbra.common.util.ZimbraLog;
import com.zimbra.graphql.repositories.IRepository;
import com.zimbra.graphql.utilities.Configuration;
import com.zimbra.graphql.utilities.GQLConstants;

public class ClassManager {

	/**
	 * Cache of repository instances by type.
	 */
	protected static final Map<String, IRepository> repositoryCache = Collections.synchronizedMap(new HashMap<String, IRepository>());

	/**
	 * Loads an IRepository for a given type.<br>
	 * Checks for cached instance before instantiating.
	 *
	 * @param type The type of repository (account, folder, etc)
	 * @return An IRepository instance
	 * @throws ConfigurationException
	 */
	protected static IRepository getRepository(String type) {
		// check the cache for a matching repository
		IRepository repository = repositoryCache.get(type);

		// if no cached repository, try to build then cache one
		if (repository == null) {
			// synchronize and re-fetch from cache to prevent duplicates
			synchronized(repositoryCache) {
				repository = repositoryCache.get(type);
				if (repository == null) {
					try {
						// load a config file
						final Configuration config = new Configuration();

						// load the handler class
						final Class<?> daoClass = Class.forName(config.getString(GQLConstants.LC_REPOSITORY_CLASS_PREFIX + type));
						repository = (IRepository) daoClass.getConstructor(Configuration.class).newInstance(config);

						// cache the new handler
						repositoryCache.put(type, repository);
					} catch (final ClassNotFoundException e) {
						ZimbraLog.extensions.error("The specified class is not supported: " + type, e);
						// TODO : exceptions ticket
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
						ZimbraLog.extensions.error("There was an issue instantiating the repository class for type: " + type, e);
						// TODO : exceptions ticket
					}
				}
			}
		}
		return repository;
	}
}
