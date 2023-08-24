package edu.princeton.pulibrary.bento.catalog;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;

public final class SolrQuery {
    /**
     * The string to search for.
     */
    private String searchString;

    /**
     * A SolrClient to be used for searching.
     */
    private SolrClient client;

    /**
     * Initialize, injecting your own SolrClient.
     * @param searchStringFromUser
     * @param solrClient
     */
    public SolrQuery(final String searchStringFromUser,
                     final SolrClient solrClient) {
        this.searchString = searchStringFromUser;
        this.client = solrClient;
    }

    /**
     * Get the results from solr.
     * @return SolrDocumentList
     * @throws IOException
     * @throws SolrServerException
     */
    public SolrDocumentList results() throws IOException, SolrServerException {
        final Map<String, String> queryParamMap = new HashMap<String, String>();
        queryParamMap.put("q", searchString);
        MapSolrParams queryParams = new MapSolrParams(queryParamMap);

        final QueryResponse response = client.query(queryParams);
        return response.getResults();
    }
}
