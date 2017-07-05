package com.miw.rabbitmq.confirm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * Created by J lai on 2017/7/5 0005.
 */
@Component
public class ReturnCallBack implements RabbitTemplate.ReturnCallback {
    private static final Logger logger = LoggerFactory.getLogger(ReturnCallBack.class);
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        try {
            logger.info("消息发送queue失败: "+new String(message.getBody(),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
