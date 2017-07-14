package com.miw.redis;

import com.miw.bean.UserPrimary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.hash.BeanUtilsHashMapper;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationUtils;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by J lai on 2017/6/23 0023.
 */
@Service
public class RedisUtils {

    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    public RedisUtils(){
        System.out.println(redisTemplate);
    }

    public UserPrimary addVerificationCode(String code){
        ValueOperations<Serializable, Serializable> valueOperations = redisTemplate.opsForValue();
        UserPrimary userPrimary = new UserPrimary();
        userPrimary.setUsername("zookpeer");
        userPrimary.setPassword("123123");
        valueOperations.set("user",userPrimary);
        UserPrimary name = (UserPrimary) valueOperations.get("user");
        return name;
    };


    public void saveHashObj(){
        UserPrimary userPrimary = new UserPrimary();
        userPrimary.setUsername("张四口");
        userPrimary.setPassword("33");
        userPrimary.setId(3+"");
        Map<String, Object> map = new Jackson2HashMapper(true).toHash(userPrimary);
        redisTemplate.boundHashOps("uid:"+userPrimary.getId()).putAll(map);
    }

    public UserPrimary getHashObj(String key) {
        BoundHashOperations<Serializable, String, Object> ops = redisTemplate.boundHashOps(("uid:" + key));
        Map<String, Object> map = ops.entries();
        return (UserPrimary) new Jackson2HashMapper(true).fromHash(map);
    }

    public void deleteHashObj(String key){
        redisTemplate.delete(key);
    }

    public Boolean expireHashObj(String key){
        UserPrimary userPrimary = new UserPrimary();
        userPrimary.setUsername("张四口");
        userPrimary.setPassword("33");
        userPrimary.setId(4+"");
        Map<String, Object> map = new Jackson2HashMapper(true).toHash(userPrimary);
        redisTemplate.boundHashOps(key).putAll(map);
        Boolean expire = redisTemplate.boundHashOps(key).expire(10, TimeUnit.SECONDS);
        return expire;
    }

    public void updateHashObj(String key,String update){
        BoundHashOperations<Serializable, Object, Object> ops = redisTemplate.boundHashOps(key);
        String username = (String) ops.get("password");
        ops.put("password",update);
    }


    public List<UserPrimary> getList(List<String> keys){
        Jackson2HashMapper jackson2HashMapper = new Jackson2HashMapper(true);
        List<UserPrimary> listRes = new ArrayList<>();
        List<Object> results = redisTemplate.executePipelined(new RedisCallback<List<Object>>() {
            @Override
            public List<Object> doInRedis(RedisConnection connection) throws DataAccessException {
                for (int i = 0; i < keys.size(); i++) {
                    byte[] serialize = redisTemplate.getStringSerializer().serialize(keys.get(i));
                    connection.hGetAll(serialize);
                }

                return null;
            }
        });
        for(int i=0;i<results.size();i++){
            Map<String,Object> a1 = (Map)results.get(i);
            UserPrimary userPrimary = (UserPrimary)jackson2HashMapper.fromHash(a1);
            listRes.add(userPrimary);
        }
        return listRes;
    }


}
