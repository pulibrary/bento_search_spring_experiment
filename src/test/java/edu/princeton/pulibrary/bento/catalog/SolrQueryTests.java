package edu.princeton.pulibrary.bento.catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SolrQueryTests {

    private SolrDocumentList results;

    @BeforeAll
    void runSearch() throws SolrServerException, IOException {
        SolrClient mockSolrClient = mock(SolrClient.class);
        QueryResponse mockQueryResponse = mock(QueryResponse.class);
        SolrDocumentList mockSolrDocumentList = new SolrDocumentList();
        SolrDocument mockSolrDocument = new SolrDocument();

        when(mockSolrClient.query(any())).thenReturn(mockQueryResponse);
        when(mockQueryResponse.getResults()).thenReturn(mockSolrDocumentList);
        mockSolrDocumentList.setNumFound(25);
        mockSolrDocument.setField("title_display", "The very best book");

        SolrQuery query = new SolrQuery("bat-eared fox",
                                        mockSolrClient);
        results = query.results();
        results.add(mockSolrDocument);
    }

    @Test
    void returnsNumFoundFromSolr() throws SolrServerException, IOException {
        assertEquals(25, results.getNumFound());
    }

    @Test
    void returnsResultsFromSolr() throws SolrServerException, IOException {
        assertEquals("The very best book", results.iterator().next().getFirstValue("title_display"));
    }

}
