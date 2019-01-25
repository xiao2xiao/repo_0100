package com.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class TestForkJoin {

	public static void main(String[] args) {
		/**
		 * 任务定义好了，现在就可以来测试一下了。
		 * 要执行一个ForkJoin任务，得先有一个线程池，能够执行ForkJoin任务的线程池就是ForkJoinPool，
		 * 这个跟我们的普通的线程池使用上很像，因为它们的祖先都是ExecutorService~
		 */
		long startTime = System.currentTimeMillis();

		ForkJoinPool forkJoinPool = new ForkJoinPool();
		long sum = forkJoinPool.invoke(new ForkJoinCalculator(1L, 1000000000L));
		long endTime = System.currentTimeMillis();
		System.out.println(sum);
		System.out.println(endTime - startTime);
	}

}
