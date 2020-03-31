package com.runtimerevolution.urlshortener;

import com.runtimerevolution.urlshortener.util.ShortenUrlGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ShortenUrlGeneratorTest {

    @Autowired
    private ShortenUrlGenerator shortenUrlGenerator;

    @Test
    void shortUrlGeneratorOK() {
        assertEquals("b", shortenUrlGenerator.idToBase62Url(1));
    }

    @Test
    void shortUrlGeneratorNOK() {
        assertEquals(null, shortenUrlGenerator.idToBase62Url(-4));
    }


}
