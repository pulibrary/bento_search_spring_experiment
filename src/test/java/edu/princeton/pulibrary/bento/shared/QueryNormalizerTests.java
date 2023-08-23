package edu.princeton.pulibrary.bento.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class QueryNormalizerTests {
    @Test
    void normalizerRemovesRedundantSpaces() {
        String query = "war  and    peace";
        QueryNormalizer normalizer = new QueryNormalizer(query);
        assertEquals("war%20and%20peace", normalizer.normalize());
    }

    @Test
    void normalizerRemovesDoubleQuotes() {
        String query = "Let's add \"quotes\"";
        QueryNormalizer normalizer = new QueryNormalizer(query);
        assertEquals("Let's%20add%20quotes", normalizer.normalize());
    }
}
