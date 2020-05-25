package com.newly.common.boot.resteasy.config;


import javax.ws.rs.core.Application;

import com.newly.common.boot.resteasy.config.JacksonConfig;
import com.newly.common.boot.resteasy.config.JaxrsApplication;
import com.newly.common.boot.resteasy.config.ResteasyProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by bingo on 2020/4/21.
 * json配置
 */
@Configuration
@EnableConfigurationProperties(ResteasyProperties.class)
public class CustomResteasyJacksonConfig {

  @Bean
  public JacksonConfig jackson2Provider() {
    return new JacksonConfig();
  }


  @Bean
  @ConditionalOnMissingBean(Application.class)
  public Application jaxrsApplication() {
    return new JaxrsApplication();
  }

}
