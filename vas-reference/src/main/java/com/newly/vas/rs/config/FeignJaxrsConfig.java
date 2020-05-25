package com.newly.vas.rs.config;

import feign.Contract;
import feign.jaxrs.JAXRSContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignJaxrsConfig {

    @Bean
    public Contract feignContract(){
        return new JAXRSContract();
    }

}
