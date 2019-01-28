package com.action.java.m04;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 普通变量也能享受cas操作
 *  1）updater只能修改它可见范围内的变量，因为它使用反射得到这个变量
 *  2）为了确保变量能被正确的读取，它必须是volatile类型的
 *  3）由于cas操作会通过对象实例中的偏移量直接进行赋值，它不支持static字段
 * 
 * @author root
 *
 */
public class AtomicIntegerFieldUpdaterDemo {

	public static class Candidate {
		int id;
		volatile int score;
	}

	public final static AtomicIntegerFieldUpdater<Candidate> scoreUpdater = AtomicIntegerFieldUpdater
			.newUpdater(Candidate.class, "score");
	// 检查updater的正确性
	public static AtomicInteger allScore = new AtomicInteger(0);

	public static void main(String[] args) throws Exception {
		final Candidate stu = new Candidate();
		Thread[] threads = new Thread[10000];
		for (int i = 0; i < 10000; i++) {
			threads[i] = new Thread(() -> {
				if (Math.random() > 0.4) {
					scoreUpdater.incrementAndGet(stu);
					allScore.incrementAndGet();
				}
			});
			threads[i].start();
		}
		for (int i = 0; i < 10000; i++) {
			threads[i].join();
		}
		System.out.println("score = " + stu.score);
		System.out.println("allScore = " + allScore);
	}
}
