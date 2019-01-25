package com.action.java.m03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExtThreadPool {
	public static class MyTask implements Runnable {

		public String name;

		public MyTask(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			System.out.println("正在执行：" + "：Thread ID：" + Thread.currentThread().getId() + "，Task name = " + name);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(10)) {
			@Override
			protected void beforeExecute(Thread t, Runnable r) {
				System.out.println("准备执行：" + ((MyTask) r).getName());
			}
			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				System.out.println("执行完成：" + ((MyTask) r).getName());
			}
			@Override
			protected void terminated() {
				System.out.println("线程退出！");
			}
		};
		for (int i = 0; i < 5; i++) {
			MyTask myTask = new MyTask("Thread-name-" + i);
			// 使用submit报错
			// executorService.submit(myTask);
			executorService.execute(myTask);
			Thread.sleep(10);
		}
		executorService.shutdown();
	}
}
