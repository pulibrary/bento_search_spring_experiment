package edu.princeton.pulibrary.bento.catalog;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.stereotype.Service;

@Service
public class SolrClientFactory {
    /**
     * Connect to solr on port 9000.
     * @return HttpSolrClient
     */
    HttpSolrClient build() {
        String configuredUrl = System.getenv("SOLR_URL");
        String defaultUrl = "http://localhost:9090/solr/catalog-staging";
        String urlString = configuredUrl == null ? defaultUrl : configuredUrl;
        return new HttpSolrClient.Builder(urlString).build();
    }
}
