package edu.princeton.pulibrary.bento.catalog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

@WebMvcTest(CatalogController.class)
public class CatalogControllerTests {
    @Autowired
    MockMvc mvc;

    @MockBean
    private SolrClientFactory solrClientFactory;

    @Test
    void catalogPathReturnsOK() throws Exception {
        HttpSolrClient mockSolrClient = mock(HttpSolrClient.class);
        QueryResponse mockQueryResponse = mock(QueryResponse.class);
        SolrDocumentList mockSolrDocumentList = new SolrDocumentList();
        when(solrClientFactory.build()).thenReturn(mockSolrClient);
        when(mockSolrClient.query(any())).thenReturn(mockQueryResponse);
        when(mockQueryResponse.getResults()).thenReturn(mockSolrDocumentList);

        this.mvc.perform(get("/search/catalog?query=art+history"))
                .andExpect(status().isOk());
    }

    @Test
    void catalogPathWithoutParamReturnsBadRequest() throws Exception {
        this.mvc.perform(get("/search/catalog"))
                .andExpect(status().isBadRequest());
    }

}
