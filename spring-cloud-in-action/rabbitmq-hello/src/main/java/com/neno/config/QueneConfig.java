package com.neno.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: root
 * @Date: 2019/2/12 16:49
 */
@Configuration
public class QueneConfig {

    @Bean
    public Queue queue() {
        return new Queue("hello");
    }

}
