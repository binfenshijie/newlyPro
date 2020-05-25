package com.newly.common.boot.operationLog.service.impl;

import com.newly.common.boot.operationLog.config.KafkaConfig;
import com.newly.common.boot.operationLog.dto.OperationLogDTO;
import com.newly.common.boot.operationLog.service.LogService;
import com.newly.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by bingo on 2020/4/24 下午5:14
 */
@Service
public class LogServiceImpl implements LogService {


//    @Autowired
//    private AmqpTemplate rabbitTemplate;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private KafkaConfig kafkaConfig;

    /**
     * @return void
     * @author bingo 2018/12/11 3:31 PM
     * @modificationHistory ========逻辑或功能重大变更记录
     * @modify by user   :{bingo} :{20181211}
     * @modify by reason :{底层实现由rabbitmq变为kafka}
     **/
    @Override
    public void saveOperationLog(OperationLogDTO operationLogDTO) {
        if (kafkaTemplate != null) {
            this.kafkaTemplate.send(kafkaConfig.getKafkaOperationLogTopic(), JsonUtils.getJsonUtlis()
                    .object2String(operationLogDTO));
        } else {
            throw new RuntimeException("not select operation log mq!");
        }

    }

//    @Override
//    public void saveOperationLog(OperationLogDTO operationLogDTO) {
//        if (rabbitTemplate != null) {
//            this.rabbitTemplate.convertAndSend(RABBIT_OPERATION_EXCHANGE, RABBIT_OPERATION_ROUTE_KEY, operationLogDTO);
//        } else {
//            throw new RuntimeException("not select operation log mq!");
//        }
//
//    }
}
