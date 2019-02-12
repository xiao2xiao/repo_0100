package com.neno.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: root
 * @Date: 2019/2/12 12:20
 */
@RestController
@RefreshScope
public class HelloController {
    @Value("${from}")
    private String from;

    @RequestMapping(value = "/from")
    public String from() {
        return this.from;
    }
}
