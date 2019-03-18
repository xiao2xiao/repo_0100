package com.neno.hystrix.circuit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neno.EshopCacheHaApplicationTests;
import com.neno.model.ProductInfo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: root
 * @Date: 2019/3/18 11:56
 */
public class CircuitBreakerTest extends EshopCacheHaApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(CircuitBreakerTest.class);

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testCircuitBreaker() {
        String url = "http://ESHOP-CACHE-HA/getProductInfo?productId=" + 1L;
        String productInfo = "";
        for (int i = 0; i < 15; i++) {
            productInfo = restTemplate.getForEntity(url, String.class).getBody();
            LOGGER.info("第" + (i + 1) + "次请求：" + JSONObject.parseObject(productInfo, ProductInfo.class).toString());
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String url2 = "http://ESHOP-CACHE-HA/getProductInfo?productId=" + (-1L);
        for (int i = 15; i < 40; i++) {
            productInfo = restTemplate.getForEntity(url2, String.class).getBody();
            LOGGER.info("第" + (i + 1) + "次请求：" + JSONObject.parseObject(productInfo, ProductInfo.class).toString());
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 40; i < 45; i++) {
            productInfo = restTemplate.getForEntity(url, String.class).getBody();
            LOGGER.info("第" + (i + 1) + "次请求：" + JSONObject.parseObject(productInfo, ProductInfo.class).toString());
        }

        LOGGER.info("尝试等待3秒钟");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 45; i < 55; i++) {
            productInfo = restTemplate.getForEntity(url, String.class).getBody();
            LOGGER.info("第" + (i + 1) + "次请求：" + JSONObject.parseObject(productInfo, ProductInfo.class).toString());
        }
    }

}
