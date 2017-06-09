package com.miw.service;

import com.miw.bean.UserPrimary;

/**
 * Created by J lai on 2017/6/2 0002.
 */
public interface IUserLoginService {

    boolean login(String username,String password);

    int insertUser(UserPrimary user);

}
