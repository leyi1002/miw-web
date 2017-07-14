package com.miw.redis;

import com.miw.bean.UserPrimary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by J lai on 2017/6/26 0026.
 */
@Service
public class UserPrimaryRedis extends RedisObjBase<UserPrimary> {

    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    public UserPrimaryRedis(){
        setRedisTemplate(redisTemplate);
    }

}
