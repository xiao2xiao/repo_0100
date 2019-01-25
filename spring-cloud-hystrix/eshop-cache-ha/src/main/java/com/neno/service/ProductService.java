package com.neno.service;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.neno.hystrix.command.GetProductInfoCommand;
import com.neno.model.ProductInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * （一）创建请求命令 传统的同步执行与异步执行之外， 我们还可以将 HystrixComand 通 过 Observable 来实现响应式执行方式。通过调用
 * observe()和toObservable ()方法可 以返回 Observable 对象. 虽然 HystrixCorand 具备了 observe
 * ()和toObservable() 的功能，但是它的 实现有一定的局限性，它返回的 Observable 只能发射一次数据，所以 Hystrix 还提供了
 * 另外一个特殊命令封装 HystrixObservableCorand, 通过它实现的命令可以获取能发 射多次的 Observable 。 如果使用
 * HystrixObservableCorand 来实现命令封装，需要将命令的执行逻辑在 construct 方法中重载，这样 Hystrix
 * 才能将具体逻辑包装到 Observable 内
 * 
 * 在使用 @HystrixComand 注解实现响应式命 令时， 可 以 通 过 observableExecutionMode 参数来控制是使用
 * observe ()还是toObservable()的执行方式。该 参数有下面两种设置方式。
 * • @HystrixComand(observableExecutionMode = ObservableExecutionMode. EAGER) :
 * EAGER 是该参数的模式值， 表示使用 observe ()执行方式。
 * • @HystrixCommand(observableExecutionMode = ObservableExecutionMode. LAZY):
 * 表示使用 toObservable()执行方式
 * 
 * （二）服务降级 1）在HystrixComand中可以通过重载 getFallback ()方法来实现服务降级逻辑， 2）在
 * HystrixObservableCommand 实 现的 Hystrix 命令中， 我们可以通过重载 resumeWithFallback
 * 方法来实现服务降级逻辑 3）在使用注解来定义服务降级逻辑时， 我们需要将具体的 Hystrix 命令与 fallback 实现函 数定义在同一个类中，
 * 并且 fallbackMethod 的值必须与实现 fallback 方法的名字相同。
 * 
 * @author root
 *
 */
@Service
public class ProductService {
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 添加缓存
	 * 
	 * @param productId
	 * @return
	 */

	@HystrixCommand(fallbackMethod = "getAaa")
	public ProductInfo getProductByidSync(long productId) {
		String url = "http://ESHOP-PRODUCT-HA/getProductInfo?productId=" + productId;
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		System.out.println("调用接口，商品productId = " + productId);
		return JSONObject.parseObject(responseEntity.getBody(), ProductInfo.class);
	}

	public ProductInfo getAaa(long productId) {
		System.out.println("---->" + productId);
		return new ProductInfo();
	}

	/**
	 * 清除缓存
	 * 
	 * @param productId
	 * @return
	 */
	@CacheRemove(commandKey = "getProductByidSync")
	@HystrixCommand
	@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD")
	public ProductInfo postProductByidSync(@CacheKey("productId") long productId) {
		String url = "http://ESHOP-PRODUCT-HA/getProductInfo2?productId=" + productId;
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		return JSONObject.parseObject(responseEntity.getBody(), ProductInfo.class);
	}

	@HystrixCommand
	public Future<ProductInfo> getProductByidAsync(long productId) {
		return new AsyncResult<ProductInfo>() {
			@Override
			public ProductInfo invoke() {
				String url = "http://ESHOP-PRODUCT-HA/getProductInfo?productId=" + productId;
				ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
				return JSONObject.parseObject(responseEntity.getBody(), ProductInfo.class);
			}

			@Override
			public ProductInfo get() throws UnsupportedOperationException {
				// TODO Auto-generated method stub
				return invoke();
			}
		};
	}

	@HystrixCommand(observableExecutionMode = ObservableExecutionMode.EAGER)
	public Observable<ProductInfo> getProductByidByObserve(Long[] productIds) {
		return Observable.unsafeCreate(new OnSubscribe<ProductInfo>() {

			@Override
			public void call(Subscriber<? super ProductInfo> observer) {
				// TODO Auto-generated method stub
				try {
					if (!observer.isUnsubscribed()) {
						for (int i = 0; i < productIds.length; i++) {
							String url = "http://ESHOP-PRODUCT-HA/getProductInfo?productId=" + productIds[i];
							ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
							ProductInfo productInfo = JSONObject.parseObject(responseEntity.getBody(),
									ProductInfo.class);
							observer.onNext(productInfo);
						}
						observer.onCompleted();
					}
				} catch (Exception e) {
					observer.onError(e);
				}
			}
		}).observeOn(Schedulers.io());
	}

}
