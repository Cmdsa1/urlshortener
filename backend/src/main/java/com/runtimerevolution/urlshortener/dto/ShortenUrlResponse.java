package com.runtimerevolution.urlshortener.dto;

public class ShortenUrlResponse {

  private String shortUrl;
  private String info;

  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }
}
