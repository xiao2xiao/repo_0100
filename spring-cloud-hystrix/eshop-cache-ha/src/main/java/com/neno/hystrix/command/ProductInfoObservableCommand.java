package com.neno.hystrix.command;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.neno.model.ProductInfo;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class ProductInfoObservableCommand extends HystrixObservableCommand<ProductInfo> {
	private Long productId;
	private RestTemplate restTemplate;

	public ProductInfoObservableCommand(HystrixCommandGroupKey hystrixCommandGroupKey, Long productId,
			RestTemplate restTemplate) {
		super(hystrixCommandGroupKey);
		this.productId = productId;
		this.restTemplate = restTemplate;
	}

	@Override
	protected Observable<ProductInfo> construct() {
		// TODO Auto-generated method stub
		return Observable.unsafeCreate(new Observable.OnSubscribe<ProductInfo>() {

			@Override
			public void call(Subscriber<? super ProductInfo> observer) {
				// TODO Auto-generated method stub
				try {
					if (!observer.isUnsubscribed()) {
						String url = "http://ESHOP-PRODUCT-HA/getProductInfo?productId=" + productId;
						ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
						ProductInfo productInfo = JSONObject.parseObject(responseEntity.getBody(), ProductInfo.class);
						observer.onNext(productInfo);
						observer.onCompleted();
					}
				} catch (Exception e) {
					observer.onError(e);
				}
			}
		}).subscribeOn(Schedulers.io());
	}
}
