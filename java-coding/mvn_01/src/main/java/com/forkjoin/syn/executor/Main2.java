package com.forkjoin.syn.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/////ExecutorService
public class Main2 {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		CountTask2 task = new CountTask2(1, 1000000);
		long start = System.currentTimeMillis();
		Future<Long> result = executor.submit(task);
		try {
			System.out.println("result is:" + result.get());
			long time = System.currentTimeMillis() - start;
			System.out.println("ExecutorService cost time is:" + time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executor.shutdown();
	}

}

class CountTask2 implements Callable<Long> {
	@SuppressWarnings("unused")
	private static final int THRESHOLD = 2;
	private int start;
	private int end;

	public CountTask2(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	public Long call() throws Exception {
		long sum = 0;
		boolean canCompute = (end - start) <= 100;
		// System.out.print(canCompute);
		if (canCompute) {
			for (int i = start; i < end; i++)
				sum += Long.valueOf(i);
		} else {
			int middle = (start + end) / 2;
			CountTask2 leftTask = new CountTask2(start, middle);
			CountTask2 rightTask = new CountTask2(middle + 1, end);
			long leftResult = (long) leftTask.call();
			long rightResult = (long) rightTask.call();
			sum = leftResult + rightResult;
		}
		return sum;
	}

}