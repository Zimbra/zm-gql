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

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.Element;
import com.zimbra.cs.service.account.SearchGal;
import com.zimbra.cs.service.mail.AutoComplete;
import com.zimbra.cs.service.mail.Search;
import com.zimbra.graphql.models.RequestContext;
import com.zimbra.graphql.models.inputs.GQLGALSearchRequestInput;
import com.zimbra.graphql.models.inputs.GQLSearchBy;
import com.zimbra.graphql.models.inputs.GQLSearchRequestInput;
import com.zimbra.graphql.models.outputs.GQLAutoCompleteResponse;
import com.zimbra.graphql.models.outputs.GQLConversationSearchResponse;
import com.zimbra.graphql.models.outputs.GQLMessageSearchResponse;
import com.zimbra.graphql.repositories.IRepository;
import com.zimbra.graphql.repositories.ZXMLRepository;
import com.zimbra.graphql.utilities.GQLAuthUtilities;
import com.zimbra.graphql.utilities.XMLDocumentUtilities;
import com.zimbra.soap.ZimbraSoapContext;
import com.zimbra.soap.account.message.SearchGalRequest;
import com.zimbra.soap.account.message.SearchGalResponse;
import com.zimbra.soap.mail.message.AutoCompleteRequest;
import com.zimbra.soap.mail.message.AutoCompleteResponse;
import com.zimbra.soap.mail.message.SearchRequest;
import com.zimbra.soap.mail.message.SearchResponse;
import com.zimbra.soap.type.GalSearchType;

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
     * The auto-complete handler.
     */
    protected final AutoComplete autoCompleteHandler;

    /**
     * The search gal handler.
     */
    protected final SearchGal searchGALHandler;

    /**
     * Creates an instance with default document handlers.
     */
    public ZXMLSearchRepository() {
        this(new Search(), new AutoComplete(), new SearchGal());
    }

    /**
     * Creates an instance with specified handlers.
     *
     * @param searchHandler The search handler
     * @param autoCompleteHandler The auto-complete handler
     */
    public ZXMLSearchRepository(Search searchHandler, AutoComplete autoCompleteHandler, SearchGal searchGALHandler) {
        super();
        this.searchHandler = searchHandler;
        this.autoCompleteHandler = autoCompleteHandler;
        this.searchGALHandler = searchGALHandler;
    }

    /**
     * Search messages.
     *
     * @param rctxt The request context
     * @param searchInput The GQLSearchRequestInput object
     * @return A list of message hits from the search
     * @throws ServiceException If there are issues executing the document
     */
    public GQLMessageSearchResponse messageSearch(RequestContext rctxt,
        GQLSearchRequestInput searchInput) throws ServiceException {
        searchInput.setSearchTypes("message");
        final SearchResponse searchResponse = search(rctxt, searchInput);
        final GQLMessageSearchResponse messageSearchResponse = new GQLMessageSearchResponse();
        messageSearchResponse.setQueryInfos(searchResponse.getQueryInfos());
        messageSearchResponse.setQueryMore(searchResponse.getQueryMore());
        messageSearchResponse.setQueryOffset(searchResponse.getQueryOffset());
        messageSearchResponse.setSortBy(searchResponse.getSortBy());
        messageSearchResponse.setTotalSize(searchResponse.getTotalSize());
        messageSearchResponse.setSearchHits(searchResponse.getSearchHits());
        return messageSearchResponse;
    }

    /**
     * Search conversations.
     *
     * @param rctxt The request context
     * @param searchInput The GQLSearchRequestInput object
     * @return A list of conversation hits from the search
     * @throws ServiceException If there are issues executing the document
     */
    public GQLConversationSearchResponse conversationSearch(RequestContext rctxt,
        GQLSearchRequestInput searchInput) throws ServiceException {
        searchInput.setSearchTypes("conversation");
        final SearchResponse searchResponse = search(rctxt, searchInput);
        final GQLConversationSearchResponse conversationSearchResponse = new GQLConversationSearchResponse();
        conversationSearchResponse.setQueryInfos(searchResponse.getQueryInfos());
        conversationSearchResponse.setQueryMore(searchResponse.getQueryMore());
        conversationSearchResponse.setQueryOffset(searchResponse.getQueryOffset());
        conversationSearchResponse.setSortBy(searchResponse.getSortBy());
        conversationSearchResponse.setTotalSize(searchResponse.getTotalSize());
        conversationSearchResponse.setSearchHits(searchResponse.getSearchHits());
        return conversationSearchResponse;
    }

    /**
     * Perform a search and return the requested list of objects.
     *
     * @param rctxt The request context
     * @param searchInput The GQLSearchRequestInput object
     * @return A SearchResponse object
     * @throws ServiceException If there are issues executing the document
     */
    protected SearchResponse search(RequestContext rctxt, GQLSearchRequestInput searchInput)
        throws ServiceException {
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
        req.setWantHtml(searchInput.getIncludeHtml());
        req.setNeedCanExpand(searchInput.getIncludeIsExpandable());
        req.setNeuterImages(searchInput.getNeuterImages());
        req.setWantRecipients(searchInput.getIncludeRecipients());
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
            XMLDocumentUtilities.toElement(req),
            rctxt);
        return XMLDocumentUtilities.fromElement(response, SearchResponse.class);
    }

    /**
     * Retrieves auto-complete search hits.
     *
     * @param rctxt The request context
     * @param name The name
     * @param type Type of addresses to auto-complete on
     * @param includeIsExpandable Denotes whether to include `isExpandable` flag for group entries
     * @param folders Comma-separated list of folder ids
     * @param includeGal Denotes whether to search the global address list
     * @return List of auto-complete matches
     * @throws ServiceException If there are issues executing the document
     */
    public GQLAutoCompleteResponse autoComplete(RequestContext rctxt, String name,
        GalSearchType type, Boolean includeIsExpandable, String folders, Boolean includeGal)
        throws ServiceException {
        // get auth context
        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        // map the request params
        final AutoCompleteRequest req = new AutoCompleteRequest(name);
        req.setType(type);
        req.setNeedCanExpand(includeIsExpandable);
        req.setFolderList(folders);
        req.setIncludeGal(includeGal);

        // execute
        final Element response = XMLDocumentUtilities.executeDocument(
            autoCompleteHandler,
            zsc,
            XMLDocumentUtilities.toElement(req),
            rctxt);
        final GQLAutoCompleteResponse gqlResponse = new GQLAutoCompleteResponse();
        if (response != null) {
            final AutoCompleteResponse autoComplete = XMLDocumentUtilities.fromElement(response,
                AutoCompleteResponse.class);
            gqlResponse.setIsCacheable(autoComplete.getCanBeCached());
            gqlResponse.setMatches(autoComplete.getMatches());
        }
        return gqlResponse;
    }

    /**
     * Search GAL.
     *
     * @param rctxt The request context
     * @param searchInput The GQLGALSearchRequestInput object
     * @return 
     * @throws ServiceException If there are issues executing the document
     */
    public SearchGalResponse galSearch(RequestContext rctxt, GQLSearchBy searchBy, String value,
            GQLGALSearchRequestInput searchInput) throws ServiceException {

        final ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(rctxt);
        SearchGalRequest req = new SearchGalRequest();
        if (searchBy == GQLSearchBy.REF) {
            req.setRef(value);
        } else {
            req.setName(value);
        }
        if (searchInput != null) {
            req.setCursor(searchInput.getCursor());
            req.setGalAccountId(searchInput.getGalAccountId());
            req.setLimit(searchInput.getLimit());
            req.setLocale(searchInput.getLocale());
            req.setNeedCanExpand(searchInput.getIncludeIsExpandable());
            req.setNeedIsMember(searchInput.getIncludeIsMember());
            req.setNeedIsOwner(searchInput.getIncludeIsOwner());
            req.setNeedSMIMECerts(searchInput.getIncludeSMIMECerts());
            req.setOffset(searchInput.getOffset());
            req.setQuick(searchInput.getQuick());
            req.setSearchFilter(searchInput.getSearchFilter());
            req.setSortBy(searchInput.getSortBy());
            req.setType(searchInput.getSearchType());
        }

        final Element response = XMLDocumentUtilities.executeDocument(
            searchGALHandler,
            zsc,
            XMLDocumentUtilities.toElement(req),
            rctxt);
        SearchGalResponse searchGalResponse = null;
        if (response != null) {
            searchGalResponse = XMLDocumentUtilities.fromElement(response,
                    SearchGalResponse.class);
        }
        return searchGalResponse;
    }
}
