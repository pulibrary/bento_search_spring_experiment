package edu.princeton.pulibrary.bento.shared;

import org.springframework.web.util.UriUtils;

/**
 * This class is responsible for cleaning the
 * query strings from the user to remove potentially
 * harmful characters and ensure compatibility with
 * integrated systems.
 */
public final class QueryNormalizer {
    /**
     * The original query provided by the user.
     */
    private String originalQuery;

    /**
     * Initialize.
     * @param query
     */
    public QueryNormalizer(final String query) {
        this.originalQuery = query;
    }

    /**
     * Get the cleaned-up version of the string.
     * @return String
     */
    public String normalize() {
        return uriEncode(
            consolidateSpaces(
                removeJSON(originalQuery)
            )
        );
    }

    private String consolidateSpaces(final String query) {
        return query.trim().replaceAll(" +", " ");
    }

    private String removeJSON(final String query) {
        return this.originalQuery.replace("{", "")
                                 .replace("}", "")
                                 .replace(":", "")
                                 .replace("\"", "");
    }

    private String uriEncode(final String query) {
        return UriUtils.encodePath(query, "utf-8");
    }
}
