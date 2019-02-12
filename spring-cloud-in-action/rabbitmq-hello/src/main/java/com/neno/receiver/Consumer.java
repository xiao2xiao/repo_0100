package com.neno.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: root
 * @Date: 2019/2/12 16:42
 */
@Component
@RabbitListener(queues = "hello")
public class Consumer {
    @RabbitHandler
    public void process(String context) {
        System.out.println("消费者process : " + context);
    }
}
