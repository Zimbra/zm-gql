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
package com.zimbra.graphql.models.inputs;

import java.util.List;

import com.zimbra.common.gql.GqlConstants;
import com.zimbra.soap.mail.type.FilterAction;
import com.zimbra.soap.mail.type.FilterTests;
import com.zimbra.soap.mail.type.FilterVariables;
import com.zimbra.soap.mail.type.NestedRule;

import io.leangen.graphql.annotations.types.GraphQLType;

/**
 * The GQLFilterRuleInput class.<br>
 * Contains filter rule input properties.
 * @see com.zimbra.soap.mail.type.FilterRule
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.models.inputs
 * @copyright Copyright © 2018
 */
@GraphQLType(name=GqlConstants.CLASS_FILTERS_REQUEST, description="Input for filter rules request.")
public class GQLFilterRuleInput {

    protected String name;
    protected Boolean active;
    protected FilterVariables filterVariables;
    protected FilterTests tests;
    protected List<FilterAction> actions;
    protected NestedRule child;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * @return the filterVariables
     */
    public FilterVariables getFilterVariables() {
        return filterVariables;
    }

    /**
     * @param filterVariables the filterVariables to set
     */
    public void setFilterVariables(FilterVariables filterVariables) {
        this.filterVariables = filterVariables;
    }

    /**
     * @return the tests
     */
    public FilterTests getTests() {
        return tests;
    }

    /**
     * @param tests the tests to set
     */
    public void setTests(FilterTests tests) {
        this.tests = tests;
    }

    /**
     * @return the actions
     */
    public List<FilterAction> getActions() {
        return actions;
    }

    /**
     * @param actions the actions to set
     */
    public void setActions(List<FilterAction> actions) {
        this.actions = actions;
    }

    /**
     * @return the child
     */
    public NestedRule getChild() {
        return child;
    }

    /**
     * @param child the child to set
     */
    public void setChild(NestedRule child) {
        this.child = child;
    }


}
