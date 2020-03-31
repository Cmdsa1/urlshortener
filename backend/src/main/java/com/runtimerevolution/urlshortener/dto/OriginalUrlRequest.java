package com.runtimerevolution.urlshortener.dto;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;

public class OriginalUrlRequest {

    @NotEmpty(message = "Please provide an Url")
    @URL(message = "The URL entered is invalid")
    private String longUrl;

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
