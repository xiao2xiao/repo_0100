package com.o2o.rmq.store.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.o2o.rmq.store.bean.Order;
import com.o2o.rmq.store.common.Constant;
import com.o2o.rmq.store.service.StoreService;
import com.rabbitmq.client.Channel;

@Component
public class OrderConsumer {
	private static Logger log = LoggerFactory.getLogger(OrderConsumer.class);

	@Autowired
	private StoreService storeService;

	@RabbitListener(queues = { Constant.QUEUE_NAME1 })
	public void handleMessage(Message message, Channel channel) throws IOException {
		try {
			/**
			 * 处理消息
			 */
			int i = storeService.update(new Gson().fromJson(new String(message.getBody()), Order.class));
			if (i > 0) {
				log.info("OrderConsumer : i = {}", i);
				log.info("OrderConsumer {} handleMessage", message);
				log.info(new String(message.getBody()));
			} else {
				throw new RuntimeException();
			}
			/**
			 * 第一个参数
			 * deliveryTag：就是接受的消息的deliveryTag,可以通过msg.getMessageProperties().getDeliveryTag()获得
			 * 第二个参数 multiple：如果为true，确认之前接受到的消息；如果为false，只确认当前消息。
			 * 如果为true就表示连续取得多条消息才发会确认，和计算机网络的中tcp协议接受分组的累积确认十分相似， 能够提高效率。
			 * 同样的，如果要nack或者拒绝消息（reject）的时候， 也是调用channel里面的basicXXX方法就可以了（要指定tagId）。
			 * 注意：如果抛异常或nack（并且requeue为true），消息会重新入队列， 并且会造成消费者不断从队列中读取同一条消息的假象。
			 */
			/**
			 * 确认消息 如果 channel.basicAck channel.basicNack channel.basicReject
			 * 这三个方法都不执行，消息也会被确认 所以，正常情况下一般不需要执行 channel.basicAck
			 * channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
			 */
			/**
			 * 消息的标识，false只确认当前一个消息收到，true确认consumer获得的所有消息
			 * channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			 *
			 * ack返回false，并重新回到队列
			 * channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,
			 * true);
			 *
			 * 拒绝消息 channel.basicReject(message.getMessageProperties().getDeliveryTag(),
			 * true);
			 */
		} catch (Exception e) {
			log.error("OrderConsumer  handleMessage {} , error:", message, e);
			/**
			 * 处理消息失败，将消息重新放回队列
			 */
			channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
		}
	}
}