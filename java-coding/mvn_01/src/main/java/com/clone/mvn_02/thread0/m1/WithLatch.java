package com.clone.mvn_02.thread0.m1;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

class Child implements Runnable {
	private CountDownLatch latch = null;
	private int id = -1;

	public Child() {
		// TODO Auto-generated constructor stub
	}

	public Child(CountDownLatch latch, int id) {
		super();
		this.latch = latch;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(new Random(100).nextInt(5000));
			System.out.println(String.format("Child Thread %d is finished!", id));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			latch.countDown();
		}
	}

}

public class WithLatch {
	public static void main(String[] args) {
		int taskSize = 3;
		CountDownLatch cLatch = new CountDownLatch(taskSize);
		for (int i = 0; i < taskSize; i++) {
			new Thread(new Child(cLatch, i)).start();
		}
		try {
			cLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Main finished!");
	}
}
