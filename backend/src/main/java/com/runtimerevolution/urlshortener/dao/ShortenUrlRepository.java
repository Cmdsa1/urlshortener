package com.runtimerevolution.urlshortener.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenUrlRepository extends MongoRepository<ShortenUrl, String> {

    ShortenUrl findByLongUrl(String longUrl);

    ShortenUrl findByShortUrl(String shortUrl);

}
