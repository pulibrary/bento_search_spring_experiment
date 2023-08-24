package edu.princeton.pulibrary.bento.catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.jupiter.api.Test;

public class CatalogSearchFactoryTests {
    @Test
    void createsNewCatalogSearchWithCorrectNumberOfHits() {
        SolrDocumentList mockSolrDocumentList = new SolrDocumentList();
        mockSolrDocumentList.setNumFound(25);
        CatalogSearchFactory factory = new CatalogSearchFactory(mockSolrDocumentList, "ducks");

        CatalogSearch search = factory.build();

        assertEquals("25",
                     search.toJSON().get("total_results").asText());

    }

    @Test
    void createsNewCatalogSearchWithCorrectMetadata() {
        SolrDocumentList mockSolrDocumentList = new SolrDocumentList();
        SolrDocument mockSolrDocument = new SolrDocument();
        mockSolrDocument.setField("title_display", "The very best book");
        mockSolrDocumentList.add(mockSolrDocument);

        CatalogSearchFactory factory = new CatalogSearchFactory(mockSolrDocumentList, "ducks");
        CatalogSearch search = factory.build();

        assertEquals("The very best book",
                     search.toJSON()
                           .get("results")
                           .iterator()
                           .next()
                           .get("title")
                           .asText());
    }

}
