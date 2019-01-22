package com.neno.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * @Author: root
 * @Date: 2019/1/20 20:56
 */
public class NewRuleConfig {

    @Autowired
    private IClientConfig iClientConfig;

    @Bean
    public IPing ribbonConfig(IClientConfig config){
        return new PingUrl(false,"/health");
    }

    public IRule ruleConfig(IClientConfig config){
        return new AvailabilityFilteringRule();
    }

}
