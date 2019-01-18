package com.neno.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: root
 * @Date: 2018/12/16 11:26
 */
@RestController
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/trace2-1", method = RequestMethod.GET)
    public String trace1() {
        String url = "http://trace-3/trace3-1";
        logger.info("=== call trace2-1 ===");
        return restTemplate.getForEntity(url, String.class).getBody();
    }
}
