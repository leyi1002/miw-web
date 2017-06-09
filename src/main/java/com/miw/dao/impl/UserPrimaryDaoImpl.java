package com.miw.dao.impl;

import com.miw.bean.UserPrimary;
import com.miw.dao.UserPrimaryDao;
import org.springframework.stereotype.Repository;


/**
 * Created by ZhangJian on 2015-7-16.
 */
@Repository
public class UserPrimaryDaoImpl extends BaseDao<UserPrimary> implements UserPrimaryDao {


    @Override
    public UserPrimary selectByUsername(String username) {
        return getSqlSession().selectOne(getMapperNameSpace()+".selectByUsername",username);
    }


}
