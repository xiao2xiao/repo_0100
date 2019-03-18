package com.neno.hystrix.command;

import com.alibaba.fastjson.JSONObject;
import com.neno.common.Commons;
import com.neno.model.ProductInfo;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * @Author: root
 * @Date: 2019/3/17 22:11
 */
public class GetProductInfosCommand extends HystrixObservableCommand<ProductInfo> {

    private long[] productIds;
    private RestTemplate restTemplate;

    public GetProductInfosCommand(long[] productIds, RestTemplate restTemplate) {
        super(Setter.withGroupKey(Commons.GetProductInfoGroupKey).andCommandKey(Commons.GetProductInfoCommandKey));
        this.productIds = productIds;
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
                    for (Long productId : productIds) {
                        String url = "http://ESHOP-PRODUCT-HA/getProductInfo?productId=" + productId;
                        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
                        ProductInfo productInfo = JSONObject.parseObject(responseEntity.getBody(), ProductInfo.class);
                        observer.onNext(productInfo);
                    }
                    observer.onCompleted();
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}
