package com.newly.common.boot.operationLog.config;


import com.newly.common.boot.operationLog.interceptor.OperationLogInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by bingo on 2020/4/23 下午3:07
 */
@Configuration
public class OperationLogConfig extends WebMvcConfigurerAdapter {

  @Value("${spring.application.name}")
  private String applicationName;

  @Value("${spring.application.code}")
  private String applicationCode;


  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(operationLogInterceptor())
            .addPathPatterns("/**/*");
    super.addInterceptors(registry);
  }

  @Bean
  public OperationLogInterceptor operationLogInterceptor() {
    return new OperationLogInterceptor(applicationName, applicationCode);
  }


}
