package com.runtimerevolution.urlshortener.service;

import com.runtimerevolution.urlshortener.dao.ShortenUrl;
import com.runtimerevolution.urlshortener.dao.ShortenUrlRepository;
import com.runtimerevolution.urlshortener.dto.OriginalUrlRequest;
import com.runtimerevolution.urlshortener.dto.ShortenUrlResponse;
import com.runtimerevolution.urlshortener.util.ShortenUrlGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UrlServiceImpl implements UrlService {

    private final static String SHORTENURLSEQUENCE = "shortenurl";
    private Logger logger = LoggerFactory.getLogger(UrlServiceImpl.class);
    @Value("${short.url.domain}")
    private String SHORTENURLDOMAIN;
    @Autowired
    private ShortenUrlRepository shortenUrlRepository;

    @Autowired
    private ShortenUrlGenerator shortenUrlGenerator;

    @Autowired
    private DatabaseService databaseService;

    /**
     * Short Url creator method,
     * receives the request with the long url version
     * returns a response with the short url in case of success
     **/
    @Override
    public synchronized ShortenUrlResponse createShortenUrl(OriginalUrlRequest request) {
        ShortenUrlResponse response = new ShortenUrlResponse();
        String longUrl = request.getLongUrl();
        StringBuilder shortenUrlstring = new StringBuilder();

        if (!existsLongUrl(longUrl)) {
            String shortid = shortenUrlGenerator.idToBase62Url((int) getCountShortenUrl() + 1);
            shortenUrlstring.append(SHORTENURLDOMAIN);
            shortenUrlstring.append(shortid);
            long nextId = databaseService.getNext(SHORTENURLSEQUENCE);
            //case the database sequence isn't found it returns -1
            if (nextId >= 0) {
                ShortenUrl shortenUrl = new ShortenUrl(nextId, longUrl, shortid);
                shortenUrlRepository.insert(shortenUrl);
                response.setShortUrl(shortenUrlstring.toString());
            } else {
                return null;
            }
        } else {
            shortenUrlstring.append(SHORTENURLDOMAIN);
            shortenUrlstring.append(shortenUrlRepository.findByLongUrl(longUrl).getShortUrl());
            response.setInfo("This Url is already registered");
            response.setShortUrl(shortenUrlstring.toString());
            logger.debug("Process:" + " 'createShortenUrl': " + "LongUrl= " + request.getLongUrl() + ": ShortenUrl= " + response.getShortUrl());
        }
        return response;
    }

    @Override
    public ShortenUrl getShortenUrl(String shortenUrl) {
        return shortenUrlRepository.findByShortUrl(shortenUrl);
    }

    public long getCountShortenUrl() {
        return shortenUrlRepository.count();
    }

    @Override
    public Boolean existsShortenUrl(String shortenUrl) {
        return shortenUrlRepository.findByShortUrl(shortenUrl) != null;
    }

    @Override
    public Boolean existsLongUrl(String longUrl) {
        return shortenUrlRepository.findByLongUrl(longUrl) != null;
    }
}
