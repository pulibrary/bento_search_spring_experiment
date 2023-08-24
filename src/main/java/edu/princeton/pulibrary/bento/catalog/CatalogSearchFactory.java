package edu.princeton.pulibrary.bento.catalog;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public final class CatalogSearchFactory {

    /**
     * A list of results from solr (also includes the total number of results).
     */
    private SolrDocumentList solrResults;

    /**
     * A query string from the user.
     */
    private String query;

    /**
     * Initialize.
     * @param solrDocumentList
     * @param queryFromUser
     */
    public CatalogSearchFactory(final SolrDocumentList solrDocumentList,
                                final String queryFromUser) {
        this.solrResults = solrDocumentList;
        this.query = queryFromUser;
    }

    /**
     * Create a new CatalogSearch object using data from SolrDocumentList.
     * @return CatalogSearch
     */
    public CatalogSearch build() {
        CatalogSearch search = new CatalogSearch(query);
        search.setTotalResults(solrResults.getNumFound());
        for (SolrDocument document : solrResults) {
            final String title =
                (String) document.getFirstValue("title_display");
            final String id =
                (String) document.getFirstValue("id");
            final String url =
                "https://catalog.princeton.edu/catalog/" + id;
            String author =
                (String) document.getFirstValue("author_display");
            String description =
                (String) document.getFirstValue("pub_created_display");

            CatalogSearchResult result =
                new CatalogSearchResult(title,
                                        url,
                                        author,
                                        description);

            search.addResult(result);
        }
        return search;
    }
}
