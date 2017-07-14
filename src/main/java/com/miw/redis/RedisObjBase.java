package com.miw.redis;

import com.miw.bean.UserPrimary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by J lai on 2017/6/26 0026.
 */

public abstract class RedisObjBase<T> {

    private RedisTemplate<Serializable, Serializable> redisTemplate;

    protected void setRedisTemplate(RedisTemplate<Serializable, Serializable> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * hash存储
     *
     * @param key 存储的key
     * @param t   插入的对象
     */
    public void saveHashObj(String key, T t) {
        Map<String, Object> map = new Jackson2HashMapper(true).toHash(t);
        redisTemplate.boundHashOps(key).putAll(map);
    }

    /**
     * hash存储+过期时间second
     *
     * @param key
     * @param t
     */
    public Boolean saveHashObjExprieSecond(String key,  T t,long timeout) {
        Map<String, Object> map = new Jackson2HashMapper(true).toHash(t);
        BoundHashOperations<Serializable, Object, Object> ops = redisTemplate.boundHashOps(key);
        ops.putAll(map);
        Boolean expire = ops.expire(timeout, TimeUnit.SECONDS);
        return expire;
    }

    /**
     * 更新hash对象(已存在)的属性值
     *
     * @param key
     * @param field
     * @param value
     */
    public void updateHashObjExistField(String key, String field, String value) {
        HashOperations<Serializable, Object, Object> hashops = redisTemplate.opsForHash();
        Boolean hasKey = hashops.hasKey(key, field);hashops.put(key,field,value);
        if(hasKey){
            hashops.put(key,field,value);
        }
    }

    /**
     * @param key 存储的key
     * @return T对象
     */
    public T getHashObj(String key) {
        BoundHashOperations<Serializable, String, Object> ops = redisTemplate.boundHashOps(key);
        Map<String, Object> map = ops.entries();
        Object o = new Jackson2HashMapper(true).fromHash(map);
        T t = (T) o;
        return t;
    }

    /**
     *  根据keys 返回list对象
     * @param keys
     * @return
     */
    public List<T> getHashObjList(List<String> keys){
        List<T> listRes = new ArrayList<>();
        Jackson2HashMapper jackson2HashMapper = new Jackson2HashMapper(true);
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
            T t = (T)jackson2HashMapper.fromHash(a1);
            listRes.add(t);
        }
        return listRes;
    }


    /**
     * 删除key
     *
     * @param key
     */
    public void deleteHashObj(String key) {
        redisTemplate.delete(key);
    }


}
