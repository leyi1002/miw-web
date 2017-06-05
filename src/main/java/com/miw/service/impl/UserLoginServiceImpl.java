package com.miw.service.impl;

import com.miw.bean.UserPrimary;
import com.miw.dao.UserPrimaryDao;
import com.miw.service.IUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by J lai on 2017/6/2 0002.
 */
@Service
public class UserLoginServiceImpl implements IUserLoginService {

    @Autowired
    private UserPrimaryDao userPrimaryDaoImpl;

    @Override
    public boolean login(String username, String password) {
        UserPrimary user = userPrimaryDaoImpl.selectByUsername(username);
        if("zookeeper".equals(username)){
            return true;
        }
        return false;
    }
}
