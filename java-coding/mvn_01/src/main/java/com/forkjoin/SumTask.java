package com.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 当我们需要执行大量的小任务时，有经验的Java开发人员都会采用线程池来高效执行这些小任务。
 * 然而，有一种任务，例如，对超过1000万个元素的数组进行排序，这种任务本身可以并发执行，但
 * 如何拆解成小任务需要在任务执行的过程中动态拆分。这样，大任务可以拆成小任务，小任务还可以
 * 继续拆成更小的任务，最后把任务的结果汇总合并，得到最终结果，这种模型就是Fork/Join模型。
 * 
 * Fork/Join使用两个类来完成以上两件事情：
 * 
 * ForkJoinTask：我们要使用ForkJoin框架，必须首先创建一个ForkJoin任务。它提供在
 * 任务中执行fork()和join()操作的机制，通常情况下我们不需要直接继承
 * ForkJoinTask类，而只需要继承它的子类，Fork/Join框架提供了以下两个子类： RecursiveAction：用于没有返回结果的任务。
 * RecursiveTask ：用于有返回结果的任务。
 * ForkJoinPool：ForkJoinTask需要通过ForkJoinPool来执行，任务分割出的子任务会添加到当
 * 前工作线程所维护的双端队列中，进入队列的头部。当一个工作线程的队列里暂时没有任务 时，它会随机从其他工作线程的队列的尾部获取一个任务。
 * 
 * @author root
 *
 */
public class SumTask extends RecursiveTask<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int THRESHOLD = 20;
	long[] array;
	int start;
	int end;

	SumTask(long[] array, int start, int end) {
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		if (end - start <= THRESHOLD) {
			// 如果任务足够小,直接计算:
			long sum = 0;
			for (int i = start; i < end; i++) {
				sum += array[i];
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			System.out.println(String.format("compute %d~%d = %d", start, end, sum));
			return sum;
		}
		// 任务太大,一分为二:
		int middle = (end + start) / 2;
		System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
		SumTask subtask1 = new SumTask(this.array, start, middle);
		SumTask subtask2 = new SumTask(this.array, middle, end);
		invokeAll(subtask1, subtask2);
		Long subresult1 = subtask1.join();
		Long subresult2 = subtask2.join();
		Long result = subresult1 + subresult2;
		System.out.println("result = " + subresult1 + " + " + subresult2 + " ==> " + result);
		return result;
	}

	public static void main(String[] args) throws Exception {
		int workerCount = Runtime.getRuntime().availableProcessors();
		int sum = 0;
		System.out.println("工作线程数：" + workerCount);
		// 创建随机数组成的数组:
		long[] array = new long[400];
		fillRandom(array);
		// fork/join task:

		ForkJoinPool fjp = new ForkJoinPool(workerCount); // 最大并发数4
		ForkJoinTask<Long> task = new SumTask(array, 0, array.length);
		long startTime = System.currentTimeMillis();
		Long result = fjp.invoke(task);
		long endTime = System.currentTimeMillis();
		System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
		System.out.println("+++++++++++++++++++++++++++++++");
		long start = System.currentTimeMillis();
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		long end = System.currentTimeMillis();
		System.out.println("sum: " + sum + " in " + (end - start) + " ms.");
	}

	private static void fillRandom(long[] array2) {
		for (int i = 0; i < array2.length; i++)
			array2[i] = new Random().nextInt(10000);
	}
}
