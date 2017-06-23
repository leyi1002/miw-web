package com.miw.controller;

import com.miw.bean.UserPrimary;
import com.miw.redis.RedisUtils;
import com.miw.service.IUserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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

    @ResponseBody
    @RequestMapping("/login")
    public boolean login(String username, String password) {
        boolean login;
        login = userLoginServiceImpl.login(username, password);
        return login;
    }

    @ResponseBody
    @RequestMapping("/addUser")
    public int addUser(){
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

}
