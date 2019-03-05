package com.neno;

import com.neno.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: root
 * @Date: 2019/3/5 9:54
 */
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @RequestMapping("hello")
    public String hello(){
        userService.productionDelayMessage();
        return "success";
    }

}
