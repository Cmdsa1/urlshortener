package com.runtimerevolution.urlshortener.service;

import com.runtimerevolution.urlshortener.dao.DatabaseSequence;
import com.runtimerevolution.urlshortener.dao.DatabaseSequenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    private final Logger logger = LoggerFactory.getLogger(DatabaseServiceImpl.class);

    @Autowired
    private DatabaseSequenceRepository sequenceRepository;

    /**
     * receives id of the datasequence desired
     * returns the next available id and updates the counter
     **/
    @Override
    public long getNext(String sequenceId) {
        long id = -1;
        Optional<DatabaseSequence> counter = sequenceRepository.findById(sequenceId);
        if (!counter.isEmpty()) {
            id = counter.get().getSeq();
            counter.get().setSeq(id + 1);
            sequenceRepository.save(counter.get());
        } else {
            logger.info("Process:" + " 'getNext': " + "sequenceId=" + sequenceId + "not found");
        }
        return id;
    }

}
