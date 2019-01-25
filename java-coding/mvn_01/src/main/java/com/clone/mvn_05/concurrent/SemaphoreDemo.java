package com.clone.mvn_05.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(5);
		for (int index = 0; index < 20; index++) {
			final int NO = index;
			executorService.execute(new Runnable() {

				@Override
				public void run() {
					try {
						semaphore.acquire();
						System.out.println("Accessing : " + NO);
						Thread.sleep((long) (Math.random() * 10000));
						// 访问完毕之后，释放；否则只能有5条记录，然后一直阻塞
						semaphore.release();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
		}
		executorService.shutdown();
	}
}
