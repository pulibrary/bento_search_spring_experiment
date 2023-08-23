package edu.princeton.pulibrary.bento.catalog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CatalogController.class)
public class CatalogControllerTests {
    @Autowired
    MockMvc mvc;

    @Test
    void catalogPathReturnsOK() throws Exception {
        this.mvc.perform(get("/search/catalog?query=art+history"))
                .andExpect(status().isOk());
    }

    @Test
    void catalogPathWithoutParamReturnsBadRequest() throws Exception {
        this.mvc.perform(get("/search/catalog"))
                .andExpect(status().isBadRequest());
    }

}
