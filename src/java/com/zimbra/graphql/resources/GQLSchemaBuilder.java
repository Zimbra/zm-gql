/*
 * ***** BEGIN LICENSE BLOCK *****
 * Zimbra GraphQL Extension
 * Copyright (C) 2019 Synacor, Inc.
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
 */package com.zimbra.graphql.resources;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.zimbra.common.util.ZimbraLog;
import com.zimbra.graphql.repositories.impl.ZNativeAuthRepository;
import com.zimbra.graphql.repositories.impl.ZXMLAccountRepository;
import com.zimbra.graphql.repositories.impl.ZXMLAuthRepository;
import com.zimbra.graphql.repositories.impl.ZXMLContactRepository;
import com.zimbra.graphql.repositories.impl.ZXMLFolderRepository;
import com.zimbra.graphql.repositories.impl.ZXMLMessageRepository;
import com.zimbra.graphql.repositories.impl.ZXMLSearchRepository;
import com.zimbra.graphql.repositories.impl.ZXMLTaskRepository;
import com.zimbra.graphql.resolvers.IResolver;
import com.zimbra.graphql.resolvers.impl.AccountResolver;
import com.zimbra.graphql.resolvers.impl.AuthResolver;
import com.zimbra.graphql.resolvers.impl.ContactResolver;
import com.zimbra.graphql.resolvers.impl.FolderResolver;
import com.zimbra.graphql.resolvers.impl.MessageResolver;
import com.zimbra.graphql.resolvers.impl.SearchResolver;
import com.zimbra.graphql.resolvers.impl.TaskResolver;

import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;

/**
  * GQLSchemaBuilder class.<br>
  * Contains methods for loading and building the graphql schema.
  *
  * @author Zimbra API Team
  * @package com.zimbra.graphql.resources
  * @copyright Copyright © 2019
  */
public class GQLSchemaBuilder {

    /**
     * List of query and mutation resolvers to build schema with.
     */
    protected List<IResolver> resolvers = new ArrayList<IResolver>();

    /**
     * Add all resolvers that represent schema queries and mutations.
     *
     * @return This instance
     */
    protected GQLSchemaBuilder loadResolvers() {
        resolvers.add(new AccountResolver(new ZXMLAccountRepository()));
        resolvers.add(new AuthResolver(new ZXMLAuthRepository(), new ZNativeAuthRepository()));
        resolvers.add(new ContactResolver(new ZXMLContactRepository()));
        resolvers.add(new FolderResolver(new ZXMLFolderRepository()));
        resolvers.add(new MessageResolver(new ZXMLMessageRepository()));
        resolvers.add(new SearchResolver(new ZXMLSearchRepository()));
        resolvers.add(new TaskResolver(new ZXMLTaskRepository()));
        return this;
    }

    /**
     * Creates the schema with loaded resolvers.
     *
     * @return A graphql schema
     */
    public GraphQLSchema build() {
        loadResolvers();
        ZimbraLog.extensions.info("Generating schema with loaded resolvers . . .");
        return new GraphQLSchemaGenerator()
            .withBasePackages(
                "com.zimbra.graphql.models",
                "com.zimbra.soap")
            .withOperationsFromSingletons(
                resolvers.toArray()
            ).generate();
    }

    /**
     * Creates an instance of the schema builder.<br>
     * Use network schema builder, default to standard if not available.
     *
     * @return An instance of the schema builder
     */
    public static GQLSchemaBuilder newInstance() {
        // check for network edition builder
        GQLSchemaBuilder builder = loadBuilder();
        if (builder == null) {
            // use standard as default
            ZimbraLog.extensions.info("Loading standard graphql schema builder.");
            builder = new GQLSchemaBuilder();
        }
        return builder;
    }

    /**
     * Attempts to load network schema builder.<br>
     * Returns null if unavailable.
     *
     * @return GQLSchemaBuilder or null if network edition is unavailable
     */
    protected static GQLSchemaBuilder loadBuilder() {
        try {
            ZimbraLog.extensions.debug("Looking for %s.", "GQLNetworkSchemaBuilder");
            final Class<?> schemaClass = Class.forName(
                GQLSchemaBuilder.class.getPackage().getName() + ".GQLNetworkSchemaBuilder");
            ZimbraLog.extensions.info("Loading network edition graphql schema builder.");
            return (GQLSchemaBuilder) schemaClass.getConstructor().newInstance();
        } catch (final ClassNotFoundException e) {
            // do nothing if network edition isn't in path
        } catch (InstantiationException | IllegalAccessException
            | IllegalArgumentException | InvocationTargetException
            | NoSuchMethodException | SecurityException e) {
            ZimbraLog.extensions.errorQuietly(
                "There was an issue instantiating the network edition graphql schema builder.", e);
        }

        return null;
    }
}
