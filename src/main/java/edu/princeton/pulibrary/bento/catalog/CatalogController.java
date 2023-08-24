package edu.princeton.pulibrary.bento.catalog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * A Spring controller that handles requests to the catalog endpoint.
 */
@RestController
public final class CatalogController {
    /**
     * API endpoint for results from the PUL catalog (presented
     * to the user as JSON through the magic of Jackson).
     * @param query
     * @return Catalog
     */
    @GetMapping("/search/catalog")
    public CatalogSearch catalog(
        @RequestParam(value = "query") final String query) {
        return new CatalogSearch(query);
    }
}
