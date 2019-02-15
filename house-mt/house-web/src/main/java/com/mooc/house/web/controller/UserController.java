package com.mooc.house.web.controller;

import com.mooc.house.biz.service.UserService;
import com.mooc.house.common.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: root
 * @Date: 2019/2/13 23:56
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("hello")
    public String hello(ModelMap modelMap){
        User user = userService.getUserList().get(0);
        modelMap.put("user",user);
        return "hello";
    }
}
