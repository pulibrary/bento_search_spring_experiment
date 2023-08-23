package edu.princeton.pulibrary.bento.catalog;

import java.util.Hashtable;
import org.springframework.web.util.UriUtils;
import com.fasterxml.jackson.annotation.JsonValue;
import edu.princeton.pulibrary.bento.shared.QueryNormalizer;


public class Catalog {
    private int total_results;
    private String query;

    public Catalog(String query) {
        this.query = query;
    }

    public String moreResultsLink() {
        String normalizedQuery = new QueryNormalizer(this.query).normalize();
        StringBuilder builder = new StringBuilder();
        builder.append("https://catalog.princeton.edu/catalog?utf8=%E2%9C%93&search_field=all_fields&q=")
                .append(normalizedQuery);
        return builder.toString();

    }

    @JsonValue public Hashtable<String, String> toJSON() {
        Hashtable<String, String> json = new Hashtable<String, String>();
        json.put("more_results_link", moreResultsLink());
        json.put("total_results", Integer.toString(total_results));
        return json;
    }

}
