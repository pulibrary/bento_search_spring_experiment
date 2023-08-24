package edu.princeton.pulibrary.bento.catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.solr.common.SolrDocumentList;
import org.junit.jupiter.api.Test;

public class CatalogSearchFactoryTests {
    @Test
    void createsNewCatalogSearchWithCorrectNumberOfHits() {
        SolrDocumentList mockSolrDocumentList = mock(SolrDocumentList.class);
        when(mockSolrDocumentList.getNumFound()).thenReturn(Long.valueOf(25));
        CatalogSearchFactory factory = new CatalogSearchFactory(mockSolrDocumentList, "ducks");

        CatalogSearch search = factory.build();

        assertEquals("25",
                     search.toJSON().get("total_results").asText());

    }
    
}
