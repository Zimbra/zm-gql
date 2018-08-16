/*
 * ***** BEGIN LICENSE BLOCK *****
 * Zimbra Collaboration Suite, Network Edition.
 * Copyright (C) 2013, 2014 Zimbra, Inc.  All Rights Reserved.
 * ***** END LICENSE BLOCK *****
 */
package com.zimbra.graphql.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.Element;
import com.zimbra.common.soap.MailConstants;
import com.zimbra.cs.account.Account;
import com.zimbra.cs.mailbox.OperationContext;
import com.zimbra.cs.service.mail.Search;
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
 * @param <T>
 * @package com.zimbra.graphql.repositories.impl
 * @copyright Copyright © 2018
 */
public class ZXMLSearchRepository extends ZXMLRepository implements IRepository {

    Search searchHandler;

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
     * @param searchInput The GQLSearchRequestInput object
     * @param operationContext The OperationContext boject 
     * @param account The Account object
     * @return A list of message hits from the search
     * @throws ServiceException If there are issues executing the document
     */
    public List<MessageHitInfo> searchMessages(GQLSearchRequestInput searchInput, OperationContext octxt,
            Account account) throws ServiceException {
        searchInput.setSearchTypes("message");
        return search(searchInput, octxt, account, MessageHitInfo.class, MailConstants.E_MSG);
    }

    /**
     * Search conversations.
     * 
     * @param searchInput The GQLSearchRequestInput object
     * @param octxt The OperationContext object 
     * @param account The Account object
     * @return A list of conversation hits from the search
     * @throws ServiceException If there are issues executing the document
     */
    public List<ConversationHitInfo> searchConversations(GQLSearchRequestInput searchInput,
            OperationContext octxt, Account account) throws ServiceException {
        searchInput.setSearchTypes("conversation");
        return search(searchInput, octxt, account, ConversationHitInfo.class, MailConstants.E_CONV);
    }

    /**
     * Perform a search and return the requested list of objects.
     * 
     * @param searchInput The GQLSearchRequestInput object
     * @param octxt The OperationContext object 
     * @param account The Account object
     * @param responseClass The Class to package the response into
     * @param requiredElement The element name to retrieve from the search result
     * @return A list of objects that are found in the search request 
     * @throws ServiceException If there are issues executing the document
     */
    private <T> List<T> search(GQLSearchRequestInput searchInput, OperationContext octxt, 
            Account account, Class<?> responseClass, String requiredElement
            ) throws ServiceException {
            ZimbraSoapContext zsc = GQLAuthUtilities.getZimbraSoapContext(octxt, account);
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
            XMLDocumentUtilities.toElement(req),
            zsc
        );
        List<T> searchHits = new ArrayList<T>();
        if (response != null && response.hasChildren()) {
            List<Element> searches = response.listElements(requiredElement);
            for (Element search : searches) {
                T searchHit = XMLDocumentUtilities.fromElement(search, responseClass);
                searchHits.add(searchHit);
            }
        }
        return searchHits;
    }

}
