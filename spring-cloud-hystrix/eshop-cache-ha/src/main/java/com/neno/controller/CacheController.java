package com.neno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.neno.hystrix.command.GetProductInfoCommand;
import com.neno.model.ProductInfo;
import com.neno.service.ProductService;
import com.netflix.hystrix.HystrixCommand;

import rx.Observable;
import rx.Observer;

@Controller
public class CacheController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ProductService productService;

	/**
	 * 同步 nginx开始，各种缓存都失效了，需要获取原始数据
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping("/getProduct")
	@ResponseBody
	public String getProductInfo(@RequestParam("productIds") String productIds) {
		/**
		 * 通过继承HystrixCommand<T>方法实现
		 * 
		 * HystrixCommand<ProductInfo> getInfoCommand = new
		 * GetProductInfoCommand(productId, restTemplate); ProductInfo productInfo =
		 * getInfoCommand.execute();
		 */

		/**
		 * 通过继承HystrixObservableCommand<T>方法实现
		 * 
		 * HystrixObservableCommand<ProductInfo> hystrixObservableCommand = new
		 * ProductInfoObservableCommand( productId, restTemplate);
		 * Observable<ProductInfo> observable = hystrixObservableCommand.observe();
		 * 
		 * 
		 */

		/**
		 * 使用注解@HystrixCommand实现
		 */
		String[] strings = productIds.split(",");
		Long[] pid = new Long[strings.length];
		for (int i = 0; i < strings.length; i++) {
			pid[i] = Long.valueOf(strings[i]);
		}
		for (Long long1 : pid) {
			System.out.println("-->" + long1);
		}
		Observable<ProductInfo> observable = productService.getProductByidByObserve(pid);
		observable.subscribe(new Observer<ProductInfo>() {

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("接受完了数据。。。。。。。。。。。。。。。。");
			}

			@Override
			public void onError(Throwable e) {
				// TODO Auto-generated method stub
				e.printStackTrace();
			}

			@Override
			public void onNext(ProductInfo productInfo) {
				// TODO Auto-generated method stub
				System.out.println(productInfo);
			}
		});

		return "success";
	}

	/**
	 * 开启缓存
	 */
	@RequestMapping("/getproduct2")
	@ResponseBody
	public String getProductInfo2(@RequestParam("productIds") String productIds) {
		/**
		 * 通过继承HystrixCommand<T>方法实现
		 */
		for (String pid : productIds.split(",")) {
			HystrixCommand<ProductInfo> getInfoCommand = new GetProductInfoCommand(Long.valueOf(pid), restTemplate);
			ProductInfo productInfo = getInfoCommand.execute();
			System.out.println(productInfo);
			System.out.println(getInfoCommand.isResponseFromCache());
		}
		return "success";
	}

	/**
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping("/getproductsync")
	@ResponseBody
	public String getProductInfoSync(@RequestParam("productIds") String productIds) {
		for (String pid : productIds.split(",")) {
			ProductInfo productInfo = productService.getProductByidSync(Long.valueOf(pid));
			System.out.println(productInfo);
			// System.out.println(ProductService.isResponseFromCache());
		}
		return "success";
	}

	@RequestMapping("/postproductsync")
	@ResponseBody
	public String postProductInfoSync(Long productId) {
		ProductInfo productInfo = productService.postProductByidSync(productId);
		System.out.println(productInfo);
		return "success";
	}

	/**
	 * 异步 nginx开始，各种缓存都失效了，需要获取原始数据
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping("/getproductasync")
	@ResponseBody
	public String getProductInfoAsync(Long productId) {
		ProductInfo productInfo = null;
		try {
			productInfo = productService.getProductByidAsync(productId).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(productInfo);
		return "success";
	}

	@RequestMapping("/change/product")
	@ResponseBody
	public String changeProduct(Long productId) {
		// 拿到一个商品id
		// 调用商品服务的接口，获取商品id对应的商品的最新数据
		// 用restTemplate去调用商品服务的http接口
		String url = "http://ESHOP-PRODUCT-HA/getProductInfo?productId=" + productId;
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		System.out.println(responseEntity.getBody());
		return "success";
	}
}
