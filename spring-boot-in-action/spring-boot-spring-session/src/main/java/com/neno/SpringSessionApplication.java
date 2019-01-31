package com.neno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author root
 * @date
 */
@SpringBootApplication
public class SpringSessionApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SpringSessionApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringSessionApplication.class);
    }
}
