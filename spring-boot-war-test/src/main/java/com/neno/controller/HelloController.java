package com.neno.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: root
 * @Date: 2019/1/14 15:04
 */
@RestController
public class HelloController {

    @RequestMapping("hello")
    private String hello(String name) {
        return "hello " + name;
    }

}
