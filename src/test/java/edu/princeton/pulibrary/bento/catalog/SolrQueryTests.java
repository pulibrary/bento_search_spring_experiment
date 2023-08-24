package edu.princeton.pulibrary.bento.catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.junit.jupiter.api.Test;

public class SolrQueryTests {
    @Test
    void returnsNumFoundFromSolr() throws SolrServerException, IOException {
        SolrClient mockSolrClient = mock(SolrClient.class);
        QueryResponse mockQueryResponse = mock(QueryResponse.class);
        SolrDocumentList mockSolrDocumentList = mock(SolrDocumentList.class);
        when(mockSolrClient.query(any())).thenReturn(mockQueryResponse);
        when(mockQueryResponse.getResults()).thenReturn(mockSolrDocumentList);
        when(mockSolrDocumentList.getNumFound()).thenReturn(Long.valueOf(25));

        SolrQuery query = new SolrQuery("bat-eared fox",
                                        mockSolrClient);
        SolrDocumentList results = query.results();
        assertEquals(25, results.getNumFound());
    }

}
