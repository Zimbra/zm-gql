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
package com.zimbra.graphql.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.Element;
import com.zimbra.common.soap.MailConstants;
import com.zimbra.cs.service.mail.Search;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.models.inputs.GQLSearchRequestInput;
import com.zimbra.graphql.repositories.IRepository;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.XMLDocumentUtilities;
import com.zimbra.soap.ZimbraSoapContext;
import com.zimbra.soap.mail.message.SearchRequest;
import com.zimbra.soap.mail.type.ConversationHitInfo;
import com.zimbra.soap.mail.type.MessageHitInfo;

/**
 * The ZXMLSearchRepository class.<br>
 * Contains XML document based data access methods for search.
 *
 * @author Zimbra API Team
 * @package com.zimbra.graphql.repositories.impl
 * @copyright Copyright © 2018
 */
public class ZXMLSearchRepository extends ZXMLRepository implements IRepository {

    /**
     * The search document handler.
     */
    protected final Search searchHandler;

    /**
     * Creates an instance with default document handlers.
     */
    public ZXMLSearchRepository() {
        this(new Search());
    }

    /**
     * Creates an instance with specified handlers.
     *
     * @param searchHandler The search handler
     */
    public ZXMLSearchRepository(Search searchHandler) {
        super();
        this.searchHandler = searchHandler;
    }

    /**
     * Search messages.
     *
     * @param rctxt The request context
     * @param searchInput The GQLSearchRequestInput object
     * @return A list of message hits from the search
     * @throws ServiceException If there are issues executing the document
     */
    public List<MessageHitInfo> messageSearch(RequestContext rctxt,
        GQLSearchRequestInput searchInput) throws ServiceException {
        searchInput.setSearchTypes("message");
        return search(rctxt, searchInput, MessageHitInfo.class, MailConstants.E_MSG);
    }

    /**
     * Search conversations.
     *
     * @param rctxt The request context
     * @param searchInput The GQLSearchRequestInput object
     * @return A list of conversation hits from the search
     * @throws ServiceException If there are issues executing the document
     */
    public List<ConversationHitInfo> conversationSearch(RequestContext rctxt,
        GQLSearchRequestInput searchInput) throws ServiceException {
        searchInput.setSearchTypes("conversation");
        return search(rctxt, searchInput, ConversationHitInfo.class, MailConstants.E_CONV);
    }

    /**
     * Perform a search and return the requested list of objects.
     *
     * @param rctxt The request context
     * @param searchInput The GQLSearchRequestInput object
     * @param responseClass The Class to package the response into
     * @param requiredElement The element name to retrieve from the search result
     * @return A list of objects that are found in the search request
     * @throws ServiceException If there are issues executing the document
     */
    private <T> List<T> search(RequestContext rctxt, GQLSearchRequestInput searchInput,
        Class<?> responseClass, String requiredElement) throws ServiceException {
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        final SearchRequest req = new SearchRequest();
        req.setIncludeTagDeleted(searchInput.getIncludeTagDeleted());
        req.setIncludeTagMuted(searchInput.getIncludeTagMuted());
        req.setAllowableTaskStatus(searchInput.getAllowableTaskStatus());
        req.setCalItemExpandStart(searchInput.getCalItemExpandStart());
        req.setCalItemExpandEnd(searchInput.getCalItemExpandEnd());
        req.setQuery(searchInput.getQuery());
        req.setInDumpster(searchInput.getInDumpster());
        req.setSearchTypes(searchInput.getSearchTypes());
        req.setGroupBy(searchInput.getGroupBy());
        req.setQuick(searchInput.getQuick());
        req.setSortBy(searchInput.getSortBy());
        req.setFetch(searchInput.getFetch());
        req.setMarkRead(searchInput.getMarkRead());
        req.setMaxInlinedLength(searchInput.getMaxInlinedLength());
        req.setWantHtml(searchInput.getWantHtml());
        req.setNeedCanExpand(searchInput.getNeedCanExpand());
        req.setNeuterImages(searchInput.getNeuterImages());
        req.setWantRecipients(searchInput.getWantRecipients());
        req.setPrefetch(searchInput.getPrefetch());
        req.setResultMode(searchInput.getResultMode());
        req.setFullConversation(searchInput.getFullConversation());
        req.setField(searchInput.getField());
        req.setLimit(searchInput.getLimit());
        req.setOffset(searchInput.getOffset());
        req.setCalTz(searchInput.getCalTz());
        req.setLocale(searchInput.getLocale());
        req.setCursor(searchInput.getCursor());
        req.setWantContent(searchInput.getMsgContent());
        req.setIncludeMemberOf(searchInput.getIncludeMemberOf());
        req.setWantContent(searchInput.getMsgContent());
        req.setIncludeTagDeleted(searchInput.getIncludeTagDeleted());
        req.setIncludeTagMuted(searchInput.getIncludeTagMuted());

        // execute
        final Element response = XMLDocumentUtilities.executeDocument(
            searchHandler,
            zsc,
            XMLDocumentUtilities.toElement(req));
        final List<T> searchHits = new ArrayList<T>();
        if (response != null && response.hasChildren()) {
            final List<Element> searches = response.listElements(requiredElement);
            for (final Element search : searches) {
                final T searchHit = XMLDocumentUtilities.fromElement(search, responseClass);
                searchHits.add(searchHit);
            }
        }
        return searchHits;
    }

}
