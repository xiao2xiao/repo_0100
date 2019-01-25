package com.clone.mvn_05.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(3);
		new Thread(new Worker("Worker1", countDownLatch)).start();
		new Thread(new Worker("Worker2", countDownLatch)).start();
		new Thread(new Worker("Worker3", countDownLatch)).start();
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Main thread end!");
		Thread.sleep(10000);
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
		}

	}

}

class Worker implements Runnable {

	private String workerName;
	private CountDownLatch countDownLatch;

	public Worker() {
		// TODO Auto-generated constructor stub
	}

	public Worker(String workerName, CountDownLatch countDownLatch) {
		super();
		this.workerName = workerName;
		this.countDownLatch = countDownLatch;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public CountDownLatch getCountDownLatch() {
		return countDownLatch;
	}

	public void setCountDownLatch(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {

		try {
			System.out.println("Worker :" + workerName + " is begin.");
			Thread.sleep(1000);
			System.out.println("Worker :" + workerName + " is end.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			countDownLatch.countDown();
		}

	}

}