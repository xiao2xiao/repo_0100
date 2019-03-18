package com.neno.hystrix.command;

import com.neno.common.Commons;
import com.netflix.hystrix.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: root
 * @Date: 2019/3/17 21:38
 * 清空缓存
 */
public class UpdateProductInfoCommand extends HystrixCommand<Boolean> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateProductInfoCommand.class);

    private Long productId;

    private RestTemplate restTemplate;

    public UpdateProductInfoCommand(Long productId, RestTemplate restTemplate) {
        super(Setter.withGroupKey(Commons.GetProductInfoGroupKey).andCommandKey(Commons.GetProductInfoCommandKey));
        this.productId = productId;
        this.restTemplate = restTemplate;
    }

    @Override
    protected Boolean run() throws Exception {
//        GetProductInfoCommand.flushCache(productId);
        return true;
    }

}
