package com.miw.rabbitmq.confirm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by J lai on 2017/7/5 0005.
 */
@Component
public class ConfirmCallBack implements RabbitTemplate.ConfirmCallback {
    private static final Logger logger = LoggerFactory.getLogger(ConfirmCallBack.class);

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
            logger.info("消息确认成功");
        }else{
            logger.info("消息确认失败,"+cause);
        }
    }
}
