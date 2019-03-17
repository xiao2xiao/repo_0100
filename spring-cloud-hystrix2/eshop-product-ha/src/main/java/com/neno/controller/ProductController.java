package com.neno.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: root
 * @Date: 2019/3/17 20:31
 */
@RestController
public class ProductController {
    @RequestMapping("/getProductInfo")
    public String getProductInfo(Long productId) {
//        try {
//            Thread.sleep(50000);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        return "{\"id\": " + productId
                + ", \"name\": \"iphone7手机\", \"price\": 5599, \"specification\": \"iphone7的规格\", \"shopId\": 1, \"modifiedTime\": \"2017-01-01 12:00:00\"}";
    }

    @RequestMapping("/getProductInfo2")
    public String getProductInfo2(Long productId) {
        return "{\"id\": " + productId
                + ", \"name\": \"魅族手机\", \"price\": 2222, \"specification\": \"魅族的规格\", \"shopId\": 2, \"modifiedTime\": \"2018-02-09 12:00:00\"}";
    }
}
