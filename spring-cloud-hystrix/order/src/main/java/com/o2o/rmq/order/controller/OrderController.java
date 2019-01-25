package com.o2o.rmq.order.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.o2o.rmq.order.bean.Order;
import com.o2o.rmq.order.common.Constant;

@RequestMapping(value = "/order")
@RestController
public class OrderController {
	private static Logger log = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 * 保存order , 同时需要向store服务发送通知减库存
	 * 
	 * @param order
	 * @return
	 */
	@PostMapping("/save")
	public Order saveOrder(Order order) {
		log.info(order.toString());
		Gson gson = new Gson();
		/**
		 * 将order转换为json字符串，便于传输
		 */
		String orderJson = gson.toJson(order);
		String msgId = UUID.randomUUID().toString();
		/**
		 * 构建Message ,主要是使用 msgId 将 message 和 CorrelationData 关联起来。 这样当消息发送到交换机失败的时候，在
		 * MsgSendConfirmCallBack 中就可以根据 correlationData.getId()即
		 * msgId,知道具体是哪个message发送失败,进而进行处理。
		 */
		/**
		 * 将 msgId和 message绑定
		 */

//		Message message = MessageBuilder.withBody(json.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_JSON)
//				.setCorrelationId(msgId).build();
		/**
		 * 将 msgId和 CorrelationData绑定
		 */
		CorrelationData correlationData = new CorrelationData(msgId);
		//log.info("CorrelationData [id = {}]", correlationData.getId());
		/**
		 * 将 msgId 与 Message 的关系保存起来,例如放到缓存中. 当
		 * MsgSendReturnCallback回调时（消息从交换机到队列失败）,进行处理 {@code MsgSendReturnCallback}. 当
		 * MsgSendConfirmCallBack回调时,进行处理 {@code MsgSendConfirmCallBack}.
		 * 定时检查这个绑定关系列表,如果发现一些已经超时(自己设定的超时时间)未被处理,则手动处理这些消息.
		 */
		/**
		 * 发送消息 指定消息交换机 "first_exchange" 指定队列key "queue_one_key1"
		 */
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setExpiration("6000");
		messageProperties.setCorrelationId(msgId);
		Message message = new Message(orderJson.getBytes(), messageProperties);
		rabbitTemplate.convertAndSend(Constant.EXCHANGE_01, Constant.ROUTING_KEY_1, message, correlationData);
		return order;
	}
}
