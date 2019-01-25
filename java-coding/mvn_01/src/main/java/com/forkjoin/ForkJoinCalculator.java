package com.forkjoin;

import java.util.concurrent.RecursiveTask;

public class ForkJoinCalculator extends RecursiveTask<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long start;
	private long end;
	private static final long THRESHOLD = 1000L;

	public ForkJoinCalculator(long start, long end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		/**
		 * 不大于临界值直接计算和
		 */
		if (end - start <= THRESHOLD) {
			long sum = 0;
			for (long i = start; i <= end; i++) {
				sum += i;
			}
			return sum;
		} else {
			/**
			 * 大于临界值继续进行拆分子任务
			 */
			long mid = (start + end) / 2;
			ForkJoinCalculator c1 = new ForkJoinCalculator(start, mid);
			/**
			 * 继续拆分
			 */
			c1.fork();
			ForkJoinCalculator c2 = new ForkJoinCalculator(mid + 1L, end);
			c2.fork();
			/**
			 * 合并子任务结果
			 */
			return c1.join() + c2.join();
		}
	}
}
