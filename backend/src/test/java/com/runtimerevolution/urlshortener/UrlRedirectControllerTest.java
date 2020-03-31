package com.runtimerevolution.urlshortener;

import com.runtimerevolution.urlshortener.dao.ShortenUrl;
import com.runtimerevolution.urlshortener.dao.ShortenUrlRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UrlRedirectControllerTest {

    private final static String URLTEST = "https://www.facebook.com";

    @Value("${shorturl.not.found.url}")
    private String SHORTENURLNOTFOUND;

    @Autowired
    private ShortenUrlRepository shortenUrlRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void urlRedirectControllerTestOK() throws Exception {
        shortenUrlRepository.insert(new ShortenUrl(0, URLTEST, "a"));
        this.mockMvc.perform(get("/a"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
    }

    @Test
    void urlRedirectControllerTestNOK() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/a"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        assertEquals("redirect:" + SHORTENURLNOTFOUND, mvcResult.getModelAndView().getViewName());
    }

}
