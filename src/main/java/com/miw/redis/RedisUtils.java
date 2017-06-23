package com.miw.redis;

import com.miw.bean.UserPrimary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by J lai on 2017/6/23 0023.
 */
@Service
public class RedisUtils {

    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    public UserPrimary addVerificationCode(String code){
        ValueOperations<Serializable, Serializable> valueOperations = redisTemplate.opsForValue();
        UserPrimary userPrimary = new UserPrimary();
        userPrimary.setUsername("zookpeer");
        userPrimary.setPassword("123123");
        valueOperations.set("user",userPrimary);
        UserPrimary name = (UserPrimary) valueOperations.get("user");
        return name;
    };



}
