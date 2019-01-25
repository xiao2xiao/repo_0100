package com.action.java.m03;

import java.util.concurrent.locks.ReentrantLock;

public class ReenterLock implements Runnable {

	public static ReentrantLock lock = new ReentrantLock();

	public static int m = 0;

	@Override
	public void run() {
		for (int i = 0; i < 10000000; i++) {
//			lock.lock();
//			try {
//				m++;
//			} finally {
//				lock.unlock();
//			}
			synchronized (ReenterLock.class) {
				m++;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ReenterLock reenterLock = new ReenterLock();
		Thread t1 = new Thread(reenterLock);
		Thread t2 = new Thread(reenterLock);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("m = " + m);
	}
}
