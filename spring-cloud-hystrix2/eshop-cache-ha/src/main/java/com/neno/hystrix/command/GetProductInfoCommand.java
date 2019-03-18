package com.neno.hystrix.command;

import com.alibaba.fastjson.JSONObject;
import com.neno.common.Commons;
import com.neno.model.ProductInfo;
import com.neno.service.ProductService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: root
 * @Date: 2019/3/17 21:38
 */
public class GetProductInfoCommand extends HystrixCommand<ProductInfo> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetProductInfoCommand.class);

    private Long productId;

    private RestTemplate restTemplate;

    public GetProductInfoCommand(Long productId, RestTemplate restTemplate) {
        super(Setter.withGroupKey(Commons.GetProductInfoGroupKey).andCommandKey(Commons.GetProductInfoCommandKey));
        this.productId = productId;
        this.restTemplate = restTemplate;
    }

    @Override
    protected ProductInfo run() throws Exception {
        String url = "http://ESHOP-PRODUCT-HA/getProductInfo?productId=" + productId;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        LOGGER.info("调用接口ProductService，商品productId = " + productId);
        return JSONObject.parseObject(responseEntity.getBody(), ProductInfo.class);
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(productId);
    }

    /**
     * 清空缓存
     *
     * @param productId
     */
    public static void flushCache(long productId) {
        HystrixRequestCache.getInstance(Commons.GetProductInfoCommandKey, HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(productId));
    }
}
