package com.runtimerevolution.urlshortener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.runtimerevolution.urlshortener.dao.DatabaseSequence;
import com.runtimerevolution.urlshortener.dao.DatabaseSequenceRepository;
import com.runtimerevolution.urlshortener.dto.OriginalUrlRequest;
import com.runtimerevolution.urlshortener.dto.ShortenUrlResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UrlApiRestControllerTest {

    private final static String DATASEQUENCE = "shortenurl";
    private final static String URLTEST = "https://www.facebook.com";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DatabaseSequenceRepository databaseSequenceRepository;


    @Test
    @WithMockUser(username = "test", password = "test", roles = "USER")
    void urlApiControllerTestOK() throws Exception {
        databaseSequenceRepository.insert(new DatabaseSequence(DATASEQUENCE, 0));
        OriginalUrlRequest request = new OriginalUrlRequest();
        request.setLongUrl(URLTEST);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(request);
        MvcResult mvcResult = this.mockMvc.perform(
                post("/api/url/shorten").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        ShortenUrlResponse response = mapper.readValue(contentAsString, ShortenUrlResponse.class);
        assertEquals("http:\\\\localhost:8080\\b", response.getShortUrl());
    }

    @Test
    void urlApiControllerTestNOK1() throws Exception {
        databaseSequenceRepository.insert(new DatabaseSequence(DATASEQUENCE, 0));
        OriginalUrlRequest request = new OriginalUrlRequest();
        request.setLongUrl(URLTEST);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(request);

        MvcResult mvcResult = this.mockMvc.perform(
                post("/api/url/shorten").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isUnauthorized())
                .andReturn();

    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = "USER")
    void urlApiControllerTestNOK2() throws Exception {
        databaseSequenceRepository.insert(new DatabaseSequence(DATASEQUENCE, 0));
        OriginalUrlRequest request = new OriginalUrlRequest();
        request.setLongUrl("oioi");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(request);
        MvcResult mvcResult = this.mockMvc.perform(
                post("/api/url/shorten").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = "USER")
    void urlApiControllerTestNOK3() throws Exception {
        databaseSequenceRepository.insert(new DatabaseSequence(DATASEQUENCE, 0));
        OriginalUrlRequest request = new OriginalUrlRequest();
        request.setLongUrl("");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(request);
        MvcResult mvcResult = this.mockMvc.perform(
                post("/api/url/shorten").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isBadRequest())
                .andReturn();

    }
}
