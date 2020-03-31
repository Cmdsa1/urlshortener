package com.runtimerevolution.urlshortener;

import com.runtimerevolution.urlshortener.dao.DatabaseSequenceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UrlApiRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DatabaseSequenceRepository databaseSequenceRepository;


    @Test
    void urlApiControllerTestOK() throws Exception {
//        databaseSequenceRepository.insert(new DatabaseSequence(DATASEQUENCE, 0));
//        MvcResult mvcResult = this.mockMvc.perform(post("/api/url/shorten").with(httpBasic("test", "test"))
//                .andExpect(status().is3xxRedirection())
//                .andReturn();

    }

    @Test
    void urlApiControllerTestNOK1() {

    }

    @Test
    void urlApiControllerTestNOK2() {

    }

}
