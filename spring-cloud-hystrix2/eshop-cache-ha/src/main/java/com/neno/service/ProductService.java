package com.neno.service;

import com.alibaba.fastjson.JSONObject;
import com.neno.model.ProductInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: root
 * @Date: 2019/3/17 20:56
 */
@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private RestTemplate restTemplate;

    public ProductInfo getProductById(Long productId) {
        String url = "http://ESHOP-PRODUCT-HA/getProductInfo?productId=" + productId;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        LOGGER.info("调用接口ProductService，商品productId = " + productId);
        return JSONObject.parseObject(responseEntity.getBody(), ProductInfo.class);
    }
}
