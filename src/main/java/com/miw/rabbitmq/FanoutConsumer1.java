package com.miw.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * Created by J lai on 2017/7/4 0004.
 */
@Component
public class FanoutConsumer1 implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(FanoutConsumer1.class);

    @Override
    public void onMessage(Message message) {
        try {
            logger.info("FanoutConsumer1 receive message : " + new String(message.getBody(),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
