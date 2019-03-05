package com.neno.service;

import com.neno.common.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Set;

/**
 * 一、springboot项目启动成功后执行一段代码的两种方式
 * 1、实现ApplicationRunner接口
 * 2、实现CommandLineRunner接口
 * 二、指定执行顺序
 * 当项目中同时实现了ApplicationRunner和CommondLineRunner接口时，
 * 可使用Order注解或实现Ordered接口来指定执行顺序，值越小越先执行
 *
 * @Author: root
 * @Date: 2019/3/5 9:51
 */
@Component

public class CommandService implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandService.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        consumerDelayMessage();
    }

    /**
     * 消费者，取订单
     */
    private void consumerDelayMessage() {
        while (true) {
            Set<TypedTuple<String>> items = redisTemplate.opsForZSet().rangeWithScores(CommonConstant.ORDER_ID_HOT_KEY, 0, -1);
//            LOGGER.info("++++++++++++++++++++" + items.size());
            if (items == null || items.isEmpty()) {
                System.out.println("当前没有等待的任务");
//                break;
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                continue;
            }
            double score = ((TypedTuple<String>) items.toArray()[0]).getScore();
            Calendar cal = Calendar.getInstance();
            int nowSecond = (int) (cal.getTimeInMillis() / 1000);
            if (nowSecond >= score) {
                LOGGER.info("-------- score --------- : " + score);
                LOGGER.info("-------- nowSecond --------- : " + nowSecond);
                String orderId = ((TypedTuple<String>) items.toArray()[0]).getValue();
                Long num = redisTemplate.opsForZSet().remove(CommonConstant.ORDER_ID_HOT_KEY, orderId);
                if (num != null && num > 0) {
                    System.out.println(System.currentTimeMillis() + "ms:redis消费了一个任务：消费的订单OrderId为" + orderId);
                }
            }
        }
    }
}
