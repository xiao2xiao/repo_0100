package com.neno.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: root
 * @Date: 2019/2/12 16:42
 */
@Component
public class Producer {
    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    public void send() {
        String msg = "hello : " + new Date();
        System.out.println("生产者send : " + msg);
        this.rabbitmqTemplate.convertAndSend("hello", msg.toString());
    }
}
