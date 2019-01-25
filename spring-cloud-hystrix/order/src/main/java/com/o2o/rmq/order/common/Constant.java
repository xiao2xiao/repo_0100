package com.o2o.rmq.order.common;

public interface Constant {
	/**
	 * 定义交换机的名称
	 */
	public static final String EXCHANGE_01 = "first_exchange";
	/**
	 * 对列名称
	 */
	public static final String QUEUE_NAME1 = "first-queue";
	public static final String QUEUE_NAME2 = "second-queue";
	public static final String QUEUE_NAME3 = "third-queue";

	/**
	 * 队列key
	 */
	public static final String ROUTING_KEY_1 = "queue_one_key1";
	public static final String ROUTING_KEY_2 = "queue_one_key2";
}
