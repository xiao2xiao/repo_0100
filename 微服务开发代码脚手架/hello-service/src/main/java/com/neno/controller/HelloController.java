package com.neno.controller;

import com.neno.common.RestResponse;
import com.neno.exception.IllegalParamsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: root
 * @Date: 2018/12/15 22:41
 */
@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Value("${server.port}")
    private int port;

    @RequestMapping(value = "/getusername", method = RequestMethod.GET)
    public RestResponse<String> getUsername(long id) {
        logger.info("hello-service id = " + id + "，port = " + port);
        if (id == 2) {
            logger.info("+++++++++++if if++++++++++");
            throw new IllegalParamsException(IllegalParamsException.Type.WRONG_PAGE_NUM, "参数不合法。。");
        }
        logger.info("+++++++++++ how ++++++++++");
        return RestResponse.success("test-username id = " + id + "，port = " + port);
    }
}
