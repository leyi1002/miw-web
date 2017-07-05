package com.miw.controller;

import com.miw.bean.UserInfo;
import com.miw.bean.UserPrimary;
import com.miw.rabbitmq.MessageConsumer;
import com.miw.rabbitmq.MessageProduce;
import com.miw.redis.RedisUtils;
import com.miw.redis.UserPrimaryRedis;
import com.miw.service.IUserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * Created by J lai on 2017/5/31 0031.
 */
@Controller
@RequestMapping("/rabbit")
public class RabbitController {

    private static final Logger logger = LoggerFactory.getLogger(RabbitController.class);

    @Autowired
    private MessageProduce messageProduce;


    @RequestMapping("/direct")
    public void messageProduce(){
        messageProduce.sendMessage("一个消息");
    }

    @RequestMapping("/fanout")
    public void fanout(){
        messageProduce.fanout(" 广播 消息");
    }


    @RequestMapping("/top")
    public void topic(){
        messageProduce.topic("com.miw.top.top"," top 主题 消息");
    }

    @RequestMapping("/bot")
    public void topicBot(){
        messageProduce.topic("com.miw.bot.bot"," bot 主题 消息");
    }





}
