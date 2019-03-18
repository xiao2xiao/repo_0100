package com.neno.common;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

/**
 * @Author: root
 * @Date: 2019/3/17 21:42
 */
public interface Commons {
    HystrixCommandGroupKey GetProductInfoGroupKey = HystrixCommandGroupKey.Factory
            .asKey("GetProductInfoCommandGroup");
    HystrixCommandGroupKey GetShopNameCommandGroupKey = HystrixCommandGroupKey.Factory
            .asKey("GetShopNameCommandGroup");
    HystrixCommandGroupKey GetBrandNameCommandGroupKey = HystrixCommandGroupKey.Factory
            .asKey("GetBrandNameCommandGroup");
    HystrixCommandKey GetProductInfoCommandKey = HystrixCommandKey.Factory.asKey("GetProductInfoCommand");
    HystrixCommandKey GetShopNameCommandKey = HystrixCommandKey.Factory.asKey("GetShopNameCommand");
    HystrixCommandKey GetBrandNameCommandKey = HystrixCommandKey.Factory.asKey("GetBrandNameCommand");

}
