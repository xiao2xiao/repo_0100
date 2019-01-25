package com.forkjoin.syn.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

///ForkJoinPool 
public class Main {

	public static void main(String[] args) throws ExecutionException {
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinCalculator task = new ForkJoinCalculator(1, 1000000);
		long start = System.currentTimeMillis();
		long result = pool.invoke(task);
		System.out.println("result is:" + result);
		long time = System.currentTimeMillis() - start;
		System.out.println("ForkJoinPool cost time is:" + time);

	}
}

class ForkJoinCalculator extends RecursiveTask<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long start;
	private long end;
	@SuppressWarnings("unused")
	private static final long THRESHOLD = 10000;// 临界值

	public ForkJoinCalculator(long start, long end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {

		if (end - start <= 1000) {
			// 不大于临界值直接计算和
			long sum = 0;
			for (long i = start; i <= end; i++) {
				sum += i;
			}
			return sum;
		} else {
			// 大于临界值继续进行拆分子任务
			long mid = (start + end) / 2;

			// 拆分子任务
			ForkJoinCalculator calculator1 = new ForkJoinCalculator(start, mid);
			calculator1.fork();

			// 拆分子任务
			ForkJoinCalculator calculator2 = new ForkJoinCalculator(mid + 1, end);
			calculator2.fork();

			// 合并子任务结果
			return calculator1.join() + calculator2.join();
		}
	}
}