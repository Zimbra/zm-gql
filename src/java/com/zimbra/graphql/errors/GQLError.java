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
package com.zimbra.graphql.errors;

import java.io.IOException;
import java.util.List;

import com.zimbra.common.service.ServiceException;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

/**
 * The GQLError class.<br>
 * GraphQLError Wrapper for exceptions.<br>
 * Used to generate errors to spec from exceptions not handled
 * by query execution.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.errors
 * @copyright Copyright © 2018
 */
public class GQLError implements GraphQLError {

    /**
     * The error message for this error response.
     */
    protected String message = "";

    /**
     * The exception associated with this error.
     */
    protected Exception exception;

    /**
     * Generates the message.
     *
     * @param e The exception associated with this error
     */
    public GQLError(Exception e) {
        this.exception = e;
        if (e instanceof ServiceException) {
            this.message += ((ServiceException) e).getCode() + " : ";
        }
        this.message += e.getMessage();
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        if (exception instanceof IOException) {
            return ErrorType.InvalidSyntax;
        }
        return ErrorType.ValidationError;
    }

}
