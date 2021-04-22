package com.runtimerevolution.urlshortener.service;

import com.runtimerevolution.urlshortener.dao.ShortenUrl;
import com.runtimerevolution.urlshortener.dto.OriginalUrlRequest;
import com.runtimerevolution.urlshortener.dto.ShortenUrlResponse;

public interface UrlService {

  ShortenUrlResponse createShortenUrl(OriginalUrlRequest longUrl);

  ShortenUrl getShortenUrl(String shortenUrl);

  Boolean existsShortenUrl(String shortenUrl);

  Boolean existsLongUrl(String longUrl);

}
