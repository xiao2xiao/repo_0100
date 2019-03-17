package com.neno.common;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

/**
 * @Author: root
 * @Date: 2019/3/17 21:42
 */
public interface Commons {
    public static final HystrixCommandGroupKey hystrixGroupKey = HystrixCommandGroupKey.Factory
            .asKey("GetProductInfoGroup");
    public static final HystrixCommandKey hystrixCommandKey = HystrixCommandKey.Factory.asKey("GetProductInfoCommand");
}
