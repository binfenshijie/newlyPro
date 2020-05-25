package com.newly.common.boot.resttemplate;

import com.newly.common.boot.resttemplate.QiniuRestTemplate;
import com.newly.common.boot.resttemplate.TokenRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by bingo on 2020/4/4 上午11:19
 */
@Configuration
public class RestTemplateAutoConfiguration {


    @Bean("newlyTokenRestTemplate")
    public RestTemplate newlyTokenRestTemplate(){
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(2000);
        requestFactory.setReadTimeout(2000);
        return new RestTemplate(requestFactory);
    }


    @Bean
    @ConditionalOnMissingBean
    public TokenRepository tokenRepository(@Value("${newly.service.rest.exTokenRefreshInterval:1200}") Long exTokenRefreshInterval) {
        return new TokenRepository(exTokenRefreshInterval);
    }

    @Bean
    public com.newly.common.boot.resttemplate.QiniuRestTemplate newlyRestTemplate(@Qualifier("newlyTokenRestTemplate") RestTemplate defaultRestTemplate, TokenRepository tokenRepository) {
        return new QiniuRestTemplate(defaultRestTemplate, tokenRepository);
    }
}
