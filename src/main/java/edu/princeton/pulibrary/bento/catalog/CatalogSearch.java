package edu.princeton.pulibrary.bento.catalog;

import java.util.Hashtable;
import com.fasterxml.jackson.annotation.JsonValue;
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
     * Initializer.
     * @param userQuery
     */
    public CatalogSearch(final String userQuery) {
        this.query = userQuery;
    }

    /**
     * A Hashtable that Jackson can then convert into JSON
     * that we can provide to the client.
     * @return Hashtable<String, String>
     */
    @JsonValue public Hashtable<String, String> toJSON() {
        Hashtable<String, String> json = new Hashtable<String, String>();
        json.put("more_results_link", moreResultsLink());
        json.put("total_results", Integer.toString(totalResults));
        return json;
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
