package com.runtimerevolution.urlshortener;

import com.runtimerevolution.urlshortener.dao.DatabaseSequence;
import com.runtimerevolution.urlshortener.dao.DatabaseSequenceRepository;
import com.runtimerevolution.urlshortener.service.DatabaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class DatabaseServiceTest {

    private final static String DATASEQUENCE = "shortenurl";

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private DatabaseSequenceRepository databaseSequenceRepository;

    @Test
    void databaseServiceTestOK() {
        databaseSequenceRepository.insert(new DatabaseSequence(DATASEQUENCE, 0));
        assertEquals(0, databaseService.getNext(DATASEQUENCE));
    }

    @Test
    void databaseServiceTestNOK() {
        databaseSequenceRepository.insert(new DatabaseSequence("test", 0));
        assertEquals(-1, databaseService.getNext(DATASEQUENCE));
    }
}
