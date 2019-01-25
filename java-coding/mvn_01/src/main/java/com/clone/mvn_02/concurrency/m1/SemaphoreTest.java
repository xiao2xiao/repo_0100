package com.clone.mvn_02.concurrency.m1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(5);
		for (int index = 0; index < 20; index++) {
			final int NO = index;
			Runnable runnable = new Runnable() {

				@Override
				public void run() {
					try {
						semaphore.acquire();
						System.out.println("Accessing：" + NO);
						Thread.sleep((long) Math.random() * 10000);
						//semaphore.release();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			};
			exec.execute(runnable);
		}
		exec.shutdown();
	}
}
