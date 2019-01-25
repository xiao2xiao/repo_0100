package com.action.java.m03;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest_01 {

	public static class DivTask implements Runnable {

		private int a;
		private int b;

		public DivTask(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}

		@Override
		public void run() {
			double rev = a / b;
			System.out.println("rev = " + rev);
		}
	}

	public static class TraceThreadPoolExecutor extends ThreadPoolExecutor {
		public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
				BlockingQueue<Runnable> workQueue) {
			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		}
		@Override
		public void execute(Runnable task) {
			// TODO Auto-generated method stub
			super.execute(wrap(task, clientTrace(), Thread.currentThread().getName()));
		}
		@Override
		public Future<?> submit(Runnable task) {
			// TODO Auto-generated method stub
			return super.submit(wrap(task, clientTrace(), Thread.currentThread().getName()));
		}
		private Exception clientTrace() {
			return new Exception("Client stack trace");
		}
		private Runnable wrap(final Runnable task, final Exception clientStack, String clientThreadName) {
			return new Runnable() {
				@Override
				public void run() {
					try {
						task.run();
					} catch (Exception e) {
						clientStack.printStackTrace();
						throw e;
					}
				}
			};
		}
	}

	public static void main(String[] args) throws Exception {
		ExecutorService pools = new TraceThreadPoolExecutor(5, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>());
		for (int i = 0; i < 5; i++) {
			pools.execute(new DivTask(100, i));
		}
	}
}
