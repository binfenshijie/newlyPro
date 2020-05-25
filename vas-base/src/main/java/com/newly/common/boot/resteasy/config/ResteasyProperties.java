package com.newly.common.boot.resteasy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by bingo on 2020/4/21.
 * resteasy config properties
 */
@ConfigurationProperties(prefix = "newly.service.rest")
public class ResteasyProperties {

  public static final String DEFAULT_APPLICATION_PATH_PATTERN = "/service/rs/";

  public static final String DEFAULT_REST_URL_PATTERN = DEFAULT_APPLICATION_PATH_PATTERN + "*";

  private String urlPatterns = DEFAULT_REST_URL_PATTERN;


  public String getUrlPatterns() {
    return urlPatterns;
  }

  public void setUrlPatterns(String urlPatterns) {
    this.urlPatterns = urlPatterns;
  }

}
