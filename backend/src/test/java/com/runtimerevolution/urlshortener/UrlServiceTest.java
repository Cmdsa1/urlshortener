package com.runtimerevolution.urlshortener;

import com.runtimerevolution.urlshortener.dao.DatabaseSequence;
import com.runtimerevolution.urlshortener.dao.DatabaseSequenceRepository;
import com.runtimerevolution.urlshortener.dto.OriginalUrlRequest;
import com.runtimerevolution.urlshortener.dto.ShortenUrlResponse;
import com.runtimerevolution.urlshortener.service.UrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UrlServiceTest {

    private final static String DATASEQUENCE = "shortenurl";
    private final static String URLTEST = "https://www.facebook.com";

    @Autowired
    private UrlService urlService;

    @Autowired
    private DatabaseSequenceRepository databaseSequenceRepository;

    @Test
    void urlServiceTestOK1() {
        databaseSequenceRepository.insert(new DatabaseSequence(DATASEQUENCE, 0));
        OriginalUrlRequest request = new OriginalUrlRequest();
        request.setLongUrl(URLTEST);
        ShortenUrlResponse response = urlService.createShortenUrl(request);
        assertEquals("http:\\\\localhost:8080\\b", response.getShortUrl());
    }

    @Test
    void urlServiceTestNOK1() {
        OriginalUrlRequest request = new OriginalUrlRequest();
        request.setLongUrl(URLTEST);
        urlService.createShortenUrl(request);
        ShortenUrlResponse response = urlService.createShortenUrl(request);
        assertEquals(null, response);
    }

    @Test
    void urlServiceTestNOK2() {
        databaseSequenceRepository.insert(new DatabaseSequence(DATASEQUENCE, 0));
        OriginalUrlRequest request = new OriginalUrlRequest();
        request.setLongUrl(URLTEST);
        urlService.createShortenUrl(request);
        ShortenUrlResponse response = urlService.createShortenUrl(request);
        assertEquals("http:\\\\localhost:8080\\b", response.getShortUrl());
        assertEquals("This Url is already registered", response.getInfo());
    }
}
