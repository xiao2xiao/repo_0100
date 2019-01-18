package com.neno.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: root
 * @Date: 2018/12/16 11:26
 */
@RestController
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value = "/trace3-1", method = RequestMethod.GET)
    public String trace1() {
        logger.info("=== call trace3-1 ===");
        return "how are you............";
    }

    @RequestMapping(value = "/trace3-2", method = RequestMethod.GET)
    public String trace2(HttpServletRequest request) {

        logger.info("=== <call trace3-2,TraceId={},SpanId={}> ===", request.getHeader("X-B3-TraceId"), request.getHeader("X-B3-SpanId"));
        return "thank you joy...............";

    }
    @RequestMapping(value = "/trace3-3", method = RequestMethod.GET)
    public String trace3() {

        logger.info("=== call trace3-1 ===");
        return "how are you............";
    }
}
