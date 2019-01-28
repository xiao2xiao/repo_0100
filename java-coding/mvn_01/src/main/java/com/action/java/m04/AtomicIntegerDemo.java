package com.action.java.m04;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
	static AtomicInteger i = new AtomicInteger();

	public static class AddThread implements Runnable {

		@Override
		public void run() {
			for (int k = 0; k < 10000; k++) {
				i.incrementAndGet();
			}
		}

	}

	public static void main(String[] args) throws Exception {
		Thread[] threads = new Thread[10];
		for (int k = 0; k < 10; k++) {
			threads[k] = new Thread(new AddThread());
		}
		for (int k = 0; k < 10; k++) {
			threads[k].start();
		}
		for (int k = 0; k < 10; k++) {
			threads[k].join();
		}
		System.out.println(i);
	}
}
