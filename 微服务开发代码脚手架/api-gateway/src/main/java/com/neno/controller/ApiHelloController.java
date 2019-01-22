package com.neno.controller;

import com.neno.common.RestResponse;
import com.neno.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: root
 * @Date: 2019/1/20 16:45
 */
@RestController
public class ApiHelloController {
    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "test/getusername", method = RequestMethod.GET)
    public RestResponse<String> getUsername(long id) {
        return RestResponse.success(helloService.getUsername(id));
    }

    @RequestMapping(value = "/hello")
    public RestResponse<String> hello(String name) {
        return RestResponse.success("hello," + name);
    }
}
