package com.neno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author root
 * @date
 */
@SpringBootApplication
@EnableDiscoveryClient
public class EshopProductHaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopProductHaApplication.class, args);
    }

}
