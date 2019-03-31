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
 */
package com.zimbra.graphql.resources;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


/**
 * Test class for {@link GQLSchemaBuilder}.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({GQLSchemaBuilder.class})
public class GQLSchemaBuilderTest {

    /**
     * Test method for {@link GQLSchemaBuilder#newInstance}<br>
     * Validates that at least the standard schema builder is loaded.
     *
     * @throws Exception If there are issues testing
     */
    @Test
    public void testNewInstanceStandard() throws Exception {
        PowerMock.mockStaticPartial(
            GQLSchemaBuilder.class, "loadBuilder");

        GQLSchemaBuilder.loadBuilder();
        PowerMock.expectLastCall().andReturn(null);

        PowerMock.replay(GQLSchemaBuilder.class);

        final GQLSchemaBuilder builder = GQLSchemaBuilder.newInstance();
        assertNotNull(builder);

        PowerMock.verify(GQLSchemaBuilder.class);
    }

}
