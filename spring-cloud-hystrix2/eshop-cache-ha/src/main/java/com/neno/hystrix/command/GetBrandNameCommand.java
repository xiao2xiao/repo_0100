package com.neno.hystrix.command;

import com.neno.cache.local.BrandCache;
import com.neno.cache.local.LocationsCache;
import com.neno.common.Commons;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * @Author: root
 * @Date: 2019/3/18 0:05
 */
public class GetBrandNameCommand extends HystrixCommand<String> {

    private Long brandId;

    public GetBrandNameCommand(Long brandId) {
        super(Setter.withGroupKey(Commons.GetShopNameCommandGroupKey)
                .andCommandKey(Commons.GetShopNameCommandKey)
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties
                                .ExecutionIsolationStrategy.SEMAPHORE)));
        this.brandId = brandId;
    }

    @Override
    protected String run() throws Exception {
        throw new Exception();
    }

    @Override
    protected String getFallback() {
        return BrandCache.getBrandName(brandId);
    }
}
