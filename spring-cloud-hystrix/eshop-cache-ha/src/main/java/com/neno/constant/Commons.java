package com.neno.constant;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

public interface Commons {
	public static final HystrixCommandGroupKey hystrixCommandGroupKey = HystrixCommandGroupKey.Factory
			.asKey("ExampleGroup");

	public static final HystrixCommandKey hystrixCommandKey = HystrixCommandKey.Factory.asKey("ExampleCommand");
}
