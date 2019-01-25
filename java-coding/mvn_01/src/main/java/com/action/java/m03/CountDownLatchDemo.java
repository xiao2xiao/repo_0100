package com.action.java.m03;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo implements Runnable {

	static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(10);
	static final CountDownLatchDemo demo = new CountDownLatchDemo();

	@Override
	public void run() {
		try {
			// 模拟检查任务
			Thread.sleep(new Random().nextInt(10) * 1000);
			System.out.println(Thread.currentThread().getName() + " check complete");
			COUNT_DOWN_LATCH.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			executorService.submit(demo);
		}
		// 等待检查
		COUNT_DOWN_LATCH.await();
		System.out.println("Fire!");
		executorService.shutdown();
	}
}
