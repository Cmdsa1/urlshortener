package com.runtimerevolution.urlshortener.controller;

import com.runtimerevolution.urlshortener.dto.OriginalUrlRequest;
import com.runtimerevolution.urlshortener.dto.ShortenUrlResponse;
import com.runtimerevolution.urlshortener.service.UrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UrlApiRestController {

    private final Logger logger = LoggerFactory.getLogger(UrlApiRestController.class);

    @Autowired
    UrlService urlService;

    /**
     * Shorten url creater endpoint,
     * receives request with the long url version
     * returns a HTTPentity with result of the operation and in case of success the corresponding shorten url
     **/
    @RequestMapping(value = "/url/shorten", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity shortenUrl(@Validated @RequestBody OriginalUrlRequest requestModel) {

        ShortenUrlResponse response;
        try {
            response = urlService.createShortenUrl(requestModel);
            if (response != null) {
                logger.debug("Process:" + " 'shortenUrl': " + "LongUrl= " + requestModel.getLongUrl() + ": ShortenUrl= " + response.getShortUrl());
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                logger.debug("Process:" + " 'shortenUrl': " + "LongUrl= " + requestModel.getLongUrl() + ": ShortenUrl= " + response.getShortUrl() + ": process failed");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (Exception e) {
            logger.error("Process:" + " 'shortenUrl': " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
