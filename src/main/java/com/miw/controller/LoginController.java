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

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Created by J lai on 2017/5/31 0031.
 */
@Controller
@RequestMapping("/user")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private IUserLoginService userLoginServiceImpl;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserPrimaryRedis userPrimaryRedis;
    @Autowired
    private MessageProduce messageProduce;
    @Autowired
    private MessageConsumer messageConsumer;

    @ResponseBody
    @RequestMapping("/login")
    public boolean login(String username, String password) {
        boolean login;
        login = userLoginServiceImpl.login(username, password);
        return login;
    }

    @ResponseBody
    @RequestMapping("/addUser")
    public int addUser() {
        UserPrimary userPrimary = new UserPrimary();
        userPrimary.setUsername("zs");
        userPrimary.setPassword("123456");
        int i = userLoginServiceImpl.insertUser(userPrimary);
        return i;
    }

    @ResponseBody
    @RequestMapping("/redis")
    public UserPrimary redis() {
        UserPrimary s = redisUtils.addVerificationCode("123");
        return s;
    }

    @RequestMapping("/redisObj")
    public void redisObj() {
        UserPrimary userPrimary = new UserPrimary();
        userPrimary.setUsername("张四口");
        userPrimary.setPassword("33");
        userPrimary.setId(3 + "");
        userPrimaryRedis.saveHashObj("uid:" + 4, userPrimary);
    }

    @ResponseBody
    @RequestMapping("/redisGetObj")
    public UserPrimary redisGetObj() {
        UserPrimary hashObj = userPrimaryRedis.getHashObj("uid:" + 4);
        return hashObj;
    }

    @ResponseBody
    @RequestMapping("/redisGet")
    public UserPrimary redisGet() {
        UserPrimary hashObj = redisUtils.getHashObj("" + 5);
        return hashObj;
    }

    @ResponseBody
    @RequestMapping("/redisExp")
    public Boolean redisDel() {
        UserInfo u = new UserInfo();
        u.setEmail("leyi1002@163.com");
        u.setAddress("中关村S区3幢");
        UserPrimary userPrimary = new UserPrimary();
        userPrimary.setUsername("张四口");
        userPrimary.setPassword("33");
        userPrimary.setId(3 + "");
        userPrimary.setUserInfo(u);
        for(int i=0;i<5;i++){
            Boolean aBoolean = userPrimaryRedis.saveHashObjExprieSecond("uid:" + i,100000,userPrimary);

        }
        return true;
    }

    @ResponseBody
    @RequestMapping("/update")
    public Boolean update() {
        userPrimaryRedis.updateHashObjExistField("uid:"+5,"age","27");
        return true;
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<UserPrimary> list() {
        List<String> strings = Arrays.asList("uid:0", "uid:1", "uid:2");
        List<UserPrimary> list = redisUtils.getList(strings);
        return list;
    }






}
