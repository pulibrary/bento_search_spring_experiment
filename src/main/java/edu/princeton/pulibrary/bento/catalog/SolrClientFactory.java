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
        String urlString = "http://localhost:9090/solr/catalog-staging";
        return new HttpSolrClient.Builder(urlString).build();
    }
}
