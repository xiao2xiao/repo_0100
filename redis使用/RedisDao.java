package com.neno.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author: root
 * @Date: 2019/5/21 18:46
 */
@Repository
public class RedisDao {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 向redis中设置数据缓存
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        jedis.set(key, value);
        jedis.close();
    }

    /**
     * 从redis中获取数据缓存
     *
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String value = jedis.get(key);
        jedis.close();
        return value;
    }

    /**
     * 从redis中删除数据缓存
     *
     * @param key
     */
    public void delete(String key) {
        Jedis jedis = jedisPool.getResource();
        jedis.del(key);
        jedis.close();
    }
}
