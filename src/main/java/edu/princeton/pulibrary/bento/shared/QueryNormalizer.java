package edu.princeton.pulibrary.bento.shared;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.web.util.UriUtils;

public class QueryNormalizer {
    String originalQuery; 
    public QueryNormalizer(String query) {
        this.originalQuery = query;
    }

    public String normalize() {
        return uriEncode(
            consolidateSpaces(
                removeJSON(originalQuery)
            )
        );
    }

    private String consolidateSpaces(String query) {
        return query.trim().replaceAll(" +", " ");
    }

    private String removeJSON(String query) {
        return this.originalQuery.replace("{", "")
                                 .replace("}", "")
                                 .replace(":", "")
                                 .replace("\"", "");
    }

    private String uriEncode(String query) {
        return UriUtils.encodePath(query, "utf-8");
    }
}
