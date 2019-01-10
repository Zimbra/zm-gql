/*
 * ***** BEGIN LICENSE BLOCK *****
 * Zimbra Collaboration Suite Server
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
package com.zimbra.graphql.utilities;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.QName;

import com.zimbra.common.util.ZimbraLog;
import com.zimbra.soap.DocumentHandler;

public class HandlerManager {
    private static Map<QName, DocumentHandler> handlers = new HashMap<QName, DocumentHandler>();

    public static void registerHandler(QName qName, DocumentHandler handler) {
        StringBuilder sb = new StringBuilder();
        sb.append("Registering handle for ").append(qName.getName())
            .append(" with ").append(handler.getClass().getName());
        ZimbraLog.gql.debug(sb.toString());
        handlers.put(qName, handler);
    }

    public static DocumentHandler getHandler(QName qName) {
        StringBuilder sb = new StringBuilder();
        sb.append("Getting handler for ").append(qName.getName());
        ZimbraLog.gql.debug(sb.toString());
        DocumentHandler handler = handlers.get(qName);
        sb = new StringBuilder();
        if (handler == null) {
            sb.append("Handler not found for ").append(qName.getName());
        } else {
            sb.append("Found handler for ").append(qName.getName()).append(" : ")
                .append(handler.getClass().getName());
        }
        ZimbraLog.gql.debug(sb.toString());
        return handler;
    }
}
