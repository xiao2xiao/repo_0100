package com.neno.hystrix.command;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.neno.constant.Commons;
import com.neno.model.ProductInfo;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;

/**
 * @author root 获取商品信息
 */
public class GetProductInfoCommand extends HystrixCommand<ProductInfo> {

	private Long productId;
	private RestTemplate restTemplate;

	public GetProductInfoCommand(Long productId, RestTemplate restTemplate) {
		super(Setter.withGroupKey(Commons.hystrixCommandGroupKey).andCommandKey(Commons.hystrixCommandKey));
		this.productId = productId;
		this.restTemplate = restTemplate;
	}

	@Override
	protected ProductInfo run() throws Exception {
		String url = "http://ESHOP-PRODUCT-HA/getProductInfo?productId=" + productId;
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		System.out.println("调用接口，商品productId = " + productId);
		return JSONObject.parseObject(responseEntity.getBody(), ProductInfo.class);
	}

	@Override
	protected ProductInfo getFallback() {
		// TODO Auto-generated method stub
		return new ProductInfo();
	}

	/**
	 * 开启缓存
	 */
	@Override
	protected String getCacheKey() {
		// TODO Auto-generated method stub
		return "product_id_info--->" + productId;
	}

	/**
	 * 清除缓存
	 * 
	 * @param id
	 */
	public static void flushCache(Long id) {
		HystrixRequestCache.getInstance(Commons.hystrixCommandKey, HystrixConcurrencyStrategyDefault.getInstance())
				.clear("product_id_info--->" + id);
	}
}
