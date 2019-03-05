package com.neno.service;

import com.neno.RedisApplicationTests;
import com.neno.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

/**
 * @Author: root
 * @Date: 2019/2/19 18:50
 */
public class UserServiceTest extends RedisApplicationTests {

    @Autowired
    private UserService userService;
    private static final int threadNum = 10;
    private static CountDownLatch cdl = new CountDownLatch(threadNum);


    @Test
    public void getUserById() {
        userService.getUserById();

    }

    @Test
    public void productionDelayMessage() {
        userService.productionDelayMessage();
    }

//    @Test
//    public void consumerDelayMessage() {
//        userService.consumerDelayMessage();
//    }

}