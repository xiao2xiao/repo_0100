package com.neno.web;

import com.neno.model.ResultMsg;
import com.neno.model.User;
import com.neno.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: root
 * @Date: 2019/1/18 17:47
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/world")
    public User world(int id) {
        return helloService.getUserById(id);
    }

    @RequestMapping("/add")
    public ResultMsg hello(User user) {
        boolean flag = helloService.addUser(user);
        if (flag) {
            return new ResultMsg(0, "success");
        } else {
            return new ResultMsg(1, "error");
        }
    }
}
