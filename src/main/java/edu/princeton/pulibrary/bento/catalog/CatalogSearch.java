package edu.princeton.pulibrary.bento.catalog;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import edu.princeton.pulibrary.bento.shared.QueryNormalizer;

/**
 * This class models a search in PUL's catalog.
 */
public final class CatalogSearch {
    /**
     * The number of results matching the user's query.
     */
    private int totalResults;

    /**
     * The user's query.
     */
    private String query;

    /**
     * A List of documents that match the user's query.
     */
    private List<CatalogSearchResult> results;

    /**
     * Initializer.
     * @param userQuery
     */
    public CatalogSearch(final String userQuery) {
        this.query = userQuery;
        this.results = new ArrayList<CatalogSearchResult>();
    }

    /**
     * A JSON object node
     * that we can provide to the client.
     * @return HashMap<String, String|List<CatalogSearchResult>>
     */
    @JsonValue public ObjectNode toJSON() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        json.put("more_results_link", moreResultsLink());
        json.put("more_results_link", moreResultsLink());
        json.put("total_results", Integer.toString(totalResults));
        ArrayNode array = mapper.valueToTree(results);
        json.putArray("results").addAll(array);
        return json;
    }

    /**
     * Add a search result.
     * @param result
     */
    public void addResult(final CatalogSearchResult result) {
        results.add(result);
    }

    private String moreResultsLink() {
        String normalizedQuery = new QueryNormalizer(this.query).normalize();
        StringBuilder builder = new StringBuilder();
        builder.append("https://catalog.princeton.edu")
               .append("/catalog?utf8=%E2%9C%93&search_field=all_fields&q=")
               .append(normalizedQuery);
        return builder.toString();

    }
}
