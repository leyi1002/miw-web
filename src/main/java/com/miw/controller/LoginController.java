package com.miw.controller;

import com.miw.service.IUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by J lai on 2017/5/31 0031.
 */
@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private IUserLoginService userLoginServiceImpl;

    @ResponseBody
    @RequestMapping("/login")
    public boolean login(String username,String password){
        boolean login = userLoginServiceImpl.login(username, password);
        return login;
    }



}
