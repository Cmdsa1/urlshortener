package com.runtimerevolution.urlshortener.dto;

import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

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
