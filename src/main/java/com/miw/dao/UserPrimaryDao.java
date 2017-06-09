package com.miw.dao;


import com.miw.bean.UserPrimary;

/**
 * Created by ZhangJian on 2015-7-16.
 */
public interface UserPrimaryDao extends IDao<UserPrimary>{

    UserPrimary selectByUsername(String username);


}
