package com.action.java.m04;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayDemo {
	static AtomicIntegerArray arr = new AtomicIntegerArray(10);

	public static class AddThread implements Runnable {

		@Override
		public void run() {
			for (int k = 0; k < 10000; k++) {
				arr.getAndIncrement(k % arr.length());
			}
		}

	}

	public static void main(String[] args) throws Exception {
		Thread[] threads = new Thread[10];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new AddThread());
		}
		for (int i = 0; i < 10; i++) {
			threads[i].start();
		}
		for (int i = 0; i < 10; i++) {
			threads[i].join();
		}
		System.out.println(arr);
	}
}
