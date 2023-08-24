package edu.princeton.pulibrary.bento.catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CatalogSearchTests {
    @Test
    void moreResultsLinkIncludesQuery() {
        String query = "pizza";
        CatalogSearch catalog = new CatalogSearch(query);
        assertEquals("https://catalog.princeton.edu/catalog?utf8=%E2%9C%93&search_field=all_fields&q=pizza", catalog.moreResultsLink());
    }

    @Test
    void moreResultsLinkAddsUrlEncodedSpaces() {
        String query = "pizza and cheese";
        CatalogSearch catalog = new CatalogSearch(query);
        assertEquals("https://catalog.princeton.edu/catalog?utf8=%E2%9C%93&search_field=all_fields&q=pizza%20and%20cheese",
                     catalog.moreResultsLink());
    }

    @Test
    void moreResultsLinkRemovesJSONCharacters() {
        String query = """
            {"bad": "content"}
        """;
        CatalogSearch catalog = new CatalogSearch(query);
        assertEquals("https://catalog.princeton.edu/catalog?utf8=%E2%9C%93&search_field=all_fields&q=bad%20content",
                     catalog.moreResultsLink());
    }

}