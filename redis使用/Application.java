package com.neno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author root
 * @date
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Application {

    @Bean
    public JedisPool jedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // config.setMaxActive(MAX_ACTIVE);--->maxActive 改成了MaxTotal()
        jedisPoolConfig.setMaxTotal(1024);
        jedisPoolConfig.setMaxIdle(200);
        // config.setMaxWait(MAX_WAIT);-----> setMaxWait() 改成了 setMaxWaitMillis()
        jedisPoolConfig.setMaxWaitMillis(10000);
        jedisPoolConfig.setTestOnBorrow(true);
        return new JedisPool(jedisPoolConfig, "192.168.254.134", 6379, 10000);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
