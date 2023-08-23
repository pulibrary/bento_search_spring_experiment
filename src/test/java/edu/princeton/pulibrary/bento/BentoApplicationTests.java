package edu.princeton.pulibrary.bento;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BentoApplication.class)
class BentoApplicationTests {

	@Autowired
    MockMvc mvc;

    @Test
    void pathReturnsForbiddenUnlessOtherwiseConfigured() throws Exception {
        this.mvc.perform(get("/search/library_of_alexandria"))
                .andExpect(status().isForbidden());
    }

}
