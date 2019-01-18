package com.neno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @Author: root
 * @Date: 2019/1/14 11:00
 */
@SpringBootApplication
@ServletComponentScan(basePackages = "com.neno.filter")
public class WarTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(WarTestApplication.class, args);
    }

}
//public class SpringBootWarTestApplication extends SpringBootServletInitializer {
//    public static void main(String[] args) {
//        SpringApplication.run(SpringBootWarTestApplication.class, args);
//    }
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//
//        return builder.sources(SpringBootWarTestApplication.class);
//    }
//}
