package com.neno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: root
 * @Date: 2019/3/17 20:47
 */
@EnableHystrix
@SpringBootApplication
@EnableDiscoveryClient
public class EshopCacheHaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopCacheHaApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
