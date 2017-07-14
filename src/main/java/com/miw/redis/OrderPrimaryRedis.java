package com.miw.redis;

import com.miw.bean.OrderPrimary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by J lai on 2017/7/13 0013.
 */
@Component
public class OrderPrimaryRedis extends RedisObjBase<OrderPrimary> {

    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    public OrderPrimaryRedis(){
        setRedisTemplate(redisTemplate);
    }
}
