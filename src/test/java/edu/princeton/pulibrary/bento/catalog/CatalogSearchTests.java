package edu.princeton.pulibrary.bento.catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CatalogSearchTests {
    @Test
    void moreResultsLinkIncludesQuery() {
        String query = "pizza";
        CatalogSearch catalog = new CatalogSearch(query);
        assertEquals("https://catalog.princeton.edu/catalog?utf8=%E2%9C%93&search_field=all_fields&q=pizza",
                     catalog.toJSON().get("more_results_link").asText());
    }

    @Test
    void moreResultsLinkAddsUrlEncodedSpaces() {
        String query = "pizza and cheese";
        CatalogSearch catalog = new CatalogSearch(query);
        assertEquals("https://catalog.princeton.edu/catalog?utf8=%E2%9C%93&search_field=all_fields&q=pizza%20and%20cheese",
                     catalog.toJSON().get("more_results_link").asText());
    }

    @Test
    void moreResultsLinkRemovesJSONCharacters() {
        String query = """
            {"bad": "content"}
        """;
        CatalogSearch catalog = new CatalogSearch(query);
        assertEquals("https://catalog.princeton.edu/catalog?utf8=%E2%9C%93&search_field=all_fields&q=bad%20content",
                     catalog.toJSON().get("more_results_link").asText());
    }

    @Test
    void canAddSearchResult() {
        String query = "fungus";
        CatalogSearch catalog = new CatalogSearch(query);

        CatalogSearchResult result = new CatalogSearchResult("Mushrooms",
                                                             "https://catalog.princeton.edu/mushroom",
                                                             "Mushroom expert",
                                                             "A good book");
        catalog.addResult(result);
        assertEquals("Mushroom expert",
                     catalog.toJSON()
                            .get("results")
                            .iterator().next()
                            .get("creator")
                            .asText());
    }

}
