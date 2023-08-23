package edu.princeton.pulibrary.bento.catalog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogController {
    @GetMapping("/search/catalog")
	public Catalog catalog(@RequestParam(value = "query", defaultValue = "World") String name) {
		return new Catalog(12, "http://catalog.princeton.edu");
	}
}
