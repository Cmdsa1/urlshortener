package com.runtimerevolution.urlshortener.controller;

import com.runtimerevolution.urlshortener.service.UrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(("/"))
public class UrlRedirectController {

  private final Logger logger = LoggerFactory.getLogger(UrlRedirectController.class);

  @Autowired
  UrlService urlService;

  @Value("${shorturl.not.found.url}")
  private String shortenUrlNotFound;

  /**
   * Redirecting endpoint,
   * receives shortenUrl string
   * returns a ModelAndView with the redirection of the long url corresponding to the shorten version
   **/
  @GetMapping(value = "/{shortenUrl}")
  public ModelAndView redirectToLongUrl(@PathVariable("shortenUrl") String shortenUrl) {

    try {
      final boolean existsUrl = urlService.existsShortenUrl(shortenUrl);
      if (existsUrl) {
        logger.debug("Process:" + " 'redirectToLongUrl': " + "shortenUrl= " + shortenUrl
            + ": user redirected");
        return new ModelAndView("redirect:" + urlService.getShortenUrl(shortenUrl).getLongUrl());
      } else {
        logger
            .info("Process:" + " 'redirectToLongUrl': " + "shortenUrl=" + shortenUrl + "not found");
        return new ModelAndView("redirect:" + shortenUrlNotFound);
      }
    } catch (Exception e) {
      logger.error("Process:" + " 'redirectToLongUrl': " + e.getMessage());
    }
    return new ModelAndView("Service not available, try again later");
  }

}
