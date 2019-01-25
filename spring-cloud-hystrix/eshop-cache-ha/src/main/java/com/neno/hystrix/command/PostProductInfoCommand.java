package com.neno.hystrix.command;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.neno.constant.Commons;
import com.neno.model.ProductInfo;
import com.netflix.hystrix.HystrixCommand;

public class PostProductInfoCommand extends HystrixCommand<ProductInfo> {

	private RestTemplate restTemplate;
	private Long productId;

	protected PostProductInfoCommand(RestTemplate restTemplate, Long productId) {
		super(Setter.withGroupKey(Commons.hystrixCommandGroupKey));
		this.restTemplate = restTemplate;
		this.productId = productId;
	}

	@Override
	protected ProductInfo run() throws Exception {
		/**
		 * 写操作
		 */
		String url = "http://ESHOP-PRODUCT-HA/getProductInfo2?productId=" + productId;
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		/**
		 * 清除缓存
		 */
		GetProductInfoCommand.flushCache(productId);
		return JSONObject.parseObject(responseEntity.getBody(), ProductInfo.class);
	}

}
