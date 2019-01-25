package com.o2o.rmq.store.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.o2o.rmq.store.common.Constant;

@Configuration
public class RabbitMqConfig {
	/**
	 * key: queue在该direct-exchange中的key值，当消息发送给direct-exchange中指定key为设置值时，
	 * 消息将会转发给queue参数指定的消息队列
	 */
	@Autowired
	private QueueConfig queueConfig;

	@Autowired
	private ExchangeConfig exchangeConfig;

	/**
	 * 将消息队列1和交换机1进行绑定,指定队列key1
	 */
	@Bean
	public Binding binding_one() {
		return BindingBuilder.bind(queueConfig.firstQueue()).to(exchangeConfig.directExchange())
				.with(Constant.ROUTING_KEY_1);
	}
}
