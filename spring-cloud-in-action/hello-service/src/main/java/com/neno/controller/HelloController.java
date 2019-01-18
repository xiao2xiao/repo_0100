package com.neno.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * @Author: root
 * @Date: 2018/12/15 22:41
 */
@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private Registration registration;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        ServiceInstance serviceInstance = serviceInstance();
        int sleepTime = new Random().nextInt(3000);
        logger.info("sleepTime : " + sleepTime);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("/hello,host : " + serviceInstance.getHost() + ",service_id : " + serviceInstance.getServiceId());
        return "hello world";
    }

    public ServiceInstance serviceInstance() {
        List<ServiceInstance> instances = discoveryClient.getInstances(registration.getServiceId());
        if (instances != null && instances.size() > 0) {
            for (ServiceInstance instance : instances) {
                if (instance.getPort() == 8091) {
                    return instance;
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello1() {
        return "I am hello-service...........";
    }
}
