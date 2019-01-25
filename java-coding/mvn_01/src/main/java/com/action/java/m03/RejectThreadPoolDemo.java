package com.action.java.m03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectThreadPoolDemo {

	public static class MyTask implements Runnable {
		@Override
		public void run() {
			System.out.println(System.currentTimeMillis() + " :Thread ID: " + Thread.currentThread().getId());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		MyTask myTask = new MyTask();
		ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(10),
				(Runnable r, ThreadPoolExecutor executor) -> System.out.println(r.toString() + " : is disabled!"));
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			executorService.submit(myTask);
			Thread.sleep(10);
		}
		executorService.shutdown();
	}
}
