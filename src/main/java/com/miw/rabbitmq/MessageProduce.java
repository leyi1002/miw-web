package com.miw.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by J lai on 2017/7/3 0003.
 */
@Service
public class MessageProduce {
    private static final Logger logger = LoggerFactory.getLogger(MessageProduce.class);
    @Autowired
    private RabbitTemplate amqpTemplate;

    public void sendMessage(String routingkey,Object message){
        amqpTemplate.convertAndSend("directExc",routingkey,message);
        System.out.println("zz");
    }

    public void fanout(Object message){
        amqpTemplate.convertAndSend("fanoutExc",null,message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("method","fanout");
                return message;
            }
        });

    }

    public void topic(String routingtkey,String message) {
        amqpTemplate.convertAndSend("topicExc",routingtkey,message);
    }

    public void reCall(){

    }

}
