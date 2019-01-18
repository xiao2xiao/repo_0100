package com.neno.web;

import com.neno.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: root
 * @Date: 2019/1/18 10:13
 */
@Controller
public class HelloController {
    private static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private HelloService helloService;

    @RequestMapping("/index")
    public String hello() {
        return "index";
    }

    @RequestMapping("/handle")
    @ResponseBody
    public Map<String, Object> world(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        String text_area_old = request.getParameter("text_area_old");
        logger.info("------------" + text_area_old);
        if (!"".equals(text_area_old)) {
            modelMap.put("text_area_new", helloService.handleStr(text_area_old));
        } else {
            modelMap.put("text_area_new", "");
        }
        modelMap.put("success", true);
        return modelMap;
    }
}
