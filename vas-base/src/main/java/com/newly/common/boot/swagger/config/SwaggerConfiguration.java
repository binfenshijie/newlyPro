package com.newly.common.boot.swagger.config;

import com.newly.common.boot.swagger.resource.ApiListingResource;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Created by bingo on 2020/4/22.
 * swagger
 */
@Configuration
public class SwaggerConfiguration {

  @Bean("apiListingResourceJSON")
  public ApiListingResource apiListingResource() {
    return new ApiListingResource();
  }

  @Bean("apiDeclarationProvider")
  public SwaggerSerializers swaggerSerializers() {
    return new SwaggerSerializers();
  }

  @Value("${swagger.title:unknown}")
  private String title;
  @Value("${swagger.version:unknown}")
  private String version;

  //项目暴露接口的contextPath Root
  @Value("${swagger.basePath:unknown}")
  private String basePath;
  @Value("${swagger.resourcePackage:unknown}")
  private String resourcePackage;

  @Bean
  @ConditionalOnMissingBean(BeanConfig.class)
  public BeanConfig beanConfig() {
    BeanConfig beanConfig = new BeanConfig();
    beanConfig.setTitle(title);
    beanConfig.setVersion(version);
    beanConfig.setSchemes(new String[]{"http"});
    beanConfig.setBasePath(basePath);
    beanConfig.setResourcePackage(resourcePackage);
    beanConfig.setScan(true);
    return beanConfig;
  }
}
