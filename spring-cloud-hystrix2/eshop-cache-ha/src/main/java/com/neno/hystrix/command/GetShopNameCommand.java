package com.neno.hystrix.command;

import com.neno.cache.local.LocationsCache;
import com.neno.common.Commons;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * @Author: root
 * @Date: 2019/3/18 0:05
 */
public class GetShopNameCommand extends HystrixCommand<String> {

    private Long shopId;

    public GetShopNameCommand(Long shopId) {
        super(Setter.withGroupKey(Commons.hystrixGroupKey)
                .andCommandKey(Commons.hystrixCommandKey)
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties
                                .ExecutionIsolationStrategy.SEMAPHORE)));
        this.shopId = shopId;
    }

    @Override
    protected String run() throws Exception {
        return LocationsCache.getShopName(shopId);
    }
}
