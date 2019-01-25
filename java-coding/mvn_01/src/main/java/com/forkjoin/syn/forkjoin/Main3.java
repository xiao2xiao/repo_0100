
package com.forkjoin.syn.forkjoin;

import java.util.stream.Stream;

public class Main3 {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		long sum = Stream.iterate(1L, i -> i + 1).limit(10000000).parallel().reduce(0L, Long::sum);
		System.out.println("result is:" + sum);
		long time = System.currentTimeMillis() - start;
		System.out.println("java 8 的并发流  cost time is:" + time);
		System.out.println(((1L) + (1000000L)) * (1000000L) / (2L));
	}

	/**
	 * java 8 的方法
	 * 
	 * @param n
	 * @return
	 */
	public static long test02(int n) {
		return Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L, Long::sum);
	}

	/**
	 * java 8 的并发流
	 * 
	 * @param n
	 * @return
	 */
	public static long test03(int n) {
		return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
	}
}