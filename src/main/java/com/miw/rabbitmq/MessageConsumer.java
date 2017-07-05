package com.miw.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * Created by J lai on 2017/7/3 0003.
 */
@Component
public class MessageConsumer implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @Override
    public void onMessage(Message message) {
        try {
            logger.info("receive message : " + new String(message.getBody(),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
