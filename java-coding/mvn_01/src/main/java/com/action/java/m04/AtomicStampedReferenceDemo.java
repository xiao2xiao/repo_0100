package com.action.java.m04;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {
	static AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19, 0);

	public static void main(String[] args) {
		/**
		 * 模拟多个线程同时更新后台数据库，为用户充值
		 */
		for (int i = 0; i < 3; i++) {
			final int timestamp = money.getStamp();
			new Thread(() -> {
				while (true) {
					while (true) {
						Integer m = money.getReference();
						if (m < 20) {
							if (money.compareAndSet(m, m + 20, timestamp, timestamp + 1)) {
								System.out.println("余额小于20元，充值成功，余额：" + money.getReference() + " 元");
								break;
							}
						} else {
							break;
						}
					}
				}
			}).start();
		}
		/**
		 * 用户消费线程，模拟消费行为
		 */
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				while (true) {
					int timestamp = money.getStamp();
					Integer m = money.getReference();
					if (m > 10) {
						System.out.println("大于10元");
						if (money.compareAndSet(m, m - 10, timestamp, timestamp + 1)) {
							System.out.println("成功消费10元，余额：" + money.getReference() + " 元");
							break;
						}
					} else {
						System.out.println("没有足够的余额！");
						break;
					}
				}
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
