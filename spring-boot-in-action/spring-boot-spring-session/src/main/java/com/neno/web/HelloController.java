package com.neno.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: root
 * @Date: 2019/1/29 14:13
 */
@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("putsession")
    public String putSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        LOGGER.info(session.getClass().toString());
        LOGGER.info(session.getId());
        String name = "fly";
        session.setAttribute("user", name);
        return "hello，" + name + " ，" + session.getId() + " (,) " + session.getClass().toGenericString();
    }

    @RequestMapping("how")
    public String how(HttpServletRequest request, String name) {
        return "hello，" + name;
    }
}
