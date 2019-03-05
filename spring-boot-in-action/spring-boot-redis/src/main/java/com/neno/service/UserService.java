package com.neno.service;

import com.alibaba.fastjson.JSONObject;
import com.neno.common.CommonConstant;
import com.neno.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import sun.rmi.runtime.Log;

import java.util.*;

/**
 * @Author: root
 * @Date: 2019/2/19 18:33
 */
@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void getUserById() {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        String aa = JSONObject.toJSON(user).toString();

        //redisTemplate.opsForHash().put("user_id:" + user.getId(), aa + ":" + user.getId(), aa);
        Map<String, Object> map = new HashMap<>();
        map.put("id", String.valueOf(1));
        map.put("name", "hao");
        redisTemplate.opsForHash().putAll("user_id:" + user.getId(), map);
    }

    /**
     * 生产者,生成5个订单放进去
     */
    public void productionDelayMessage() {
        for (int i = 0; i < 5; i++) {
            //延迟3秒
            Calendar cal1 = Calendar.getInstance();
            cal1.add(Calendar.SECOND, 20);
            int second3later = (int) (cal1.getTimeInMillis() / 1000) + 20 + (i + 1) * 10;
            redisTemplate.opsForZSet().add(CommonConstant.ORDER_ID_HOT_KEY, "OID0000001" + i, second3later);
            System.out.println(System.currentTimeMillis() + "ms:redis生成了一个订单任务：订单ID为" + "OID0000001" + i);
        }
    }
}
