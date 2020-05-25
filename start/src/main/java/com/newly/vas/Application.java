package com.newly.vas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by bingo on 2019/1/27 下午5:34
 */
@SpringBootApplication(scanBasePackages = {"com.newly.common.boot", "com.newly.vas"})
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableFeignClients
@EnableScheduling
//@EnableSwagger2
@EnableAsync(proxyTargetClass = true)//利用@EnableAsync注解开启异步任务支持
@MapperScan(value = {"com.newly.vas.**.mapper"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}