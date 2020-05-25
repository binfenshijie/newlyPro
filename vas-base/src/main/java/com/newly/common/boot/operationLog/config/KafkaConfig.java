package com.newly.common.boot.operationLog.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class KafkaConfig{

    @Value("${kafka.operation.log.topic}")
    private String kafkaOperationLogTopic = "KAFKA.OPERATION.LOG.TOPIC";
}