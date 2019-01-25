package com.clone.executor;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
	public static void main(String[] args) {
		System.out.println("Main Thread is starting..........");
		try {
			CountDownLatch latch = new CountDownLatch(3);
			Worker workerA = new Worker("workerA", latch);
			Worker workerB = new Worker("workerB", latch);
			Worker workerC = new Worker("workerC", latch);
			new Thread(workerA).start();
			new Thread(workerB).start();
			new Thread(workerC).start();
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Main Thread is ending..........");
	}
}

class Worker implements Runnable {

	private String workerName;
	private CountDownLatch latch;

	public Worker() {
	}

	public Worker(String workerName, CountDownLatch latch) {
		super();
		this.workerName = workerName;
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			System.out.println(workerName + " : 正在做任务........");
			Thread.sleep(new Random().nextInt(2999));
			System.out.println(workerName + " : 已完成任务........");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			latch.countDown();
		}
	}

}