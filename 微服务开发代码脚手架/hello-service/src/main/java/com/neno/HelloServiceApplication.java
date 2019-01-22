package com.neno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: root
 * @Date: 2018/12/15 22:41
 */
@SpringBootApplication
@EnableDiscoveryClient
//@RibbonClient(name = "hello-service",configuration = NewRuleConfig.class)
public class HelloServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloServiceApplication.class, args);
    }

}

