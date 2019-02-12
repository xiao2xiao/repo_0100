package com.neno.sender;

import com.neno.RabbitmqHelloApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @Author: root
 * @Date: 2019/2/12 16:57
 */
public class ProducerTest extends RabbitmqHelloApplicationTests {

    @Autowired
    private Producer producer;

    @Test
    public void send() {
        producer.send();
    }
}