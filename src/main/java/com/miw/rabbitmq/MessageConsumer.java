package com.miw.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * Created by J lai on 2017/7/3 0003.
 */
@Component
public class MessageConsumer implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
    @Autowired
    private RabbitTemplate amqpTemplate;

    @Override
    public void onMessage(Message message) {
        try {
            Thread.sleep(10000);
            logger.info("receive message : " + new String(message.getBody(),"utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
