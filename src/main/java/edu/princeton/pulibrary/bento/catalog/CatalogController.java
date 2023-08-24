package edu.princeton.pulibrary.bento.catalog;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * A Spring controller that handles requests to the catalog endpoint.
 */
@RestController
public final class CatalogController {

    /**
     * A factory for creating a solr client.
     */
    private final SolrClientFactory solrClientFactory;

    /**
     * Initialize.
     * @param injectedSolrClientFactory
     */
    public CatalogController(
        final SolrClientFactory injectedSolrClientFactory) {
            this.solrClientFactory = injectedSolrClientFactory;
    }

    /**
     * API endpoint for results from the PUL catalog (presented
     * to the user as JSON through the magic of Jackson).
     * @param query
     * @return Catalog
     */
    @GetMapping("/search/catalog")
    public CatalogSearch catalog(
        @RequestParam(value = "query") final String query)
        throws IOException, SolrServerException {
            final SolrDocumentList results =
                new SolrQuery(query, solrClientFactory.build()).results();
            return new CatalogSearchFactory(results, query).build();
    }
}
