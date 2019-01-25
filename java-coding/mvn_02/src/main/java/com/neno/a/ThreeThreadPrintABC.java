package com.neno.a;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreeThreadPrintABC {
	private static ReentrantLock lock = new ReentrantLock();
	private static Condition aCondition = lock.newCondition();
	private static Condition bCondition = lock.newCondition();
	private static Condition cCondition = lock.newCondition();
	private static int status = 1;

	public static void main(String[] args) {
		new Thread(new A()).start();
		new Thread(new B()).start();
		new Thread(new C()).start();
	}

	static class A implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				lock.lock();
				while (status != 1) {
					try {
						aCondition.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName() + " - A");
				status = 2;
				bCondition.signal();
				lock.unlock();
			}
		}
	}

	static class B implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				lock.lock();
				while (status != 2) {
					try {
						bCondition.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName() + " - B");
				status = 3;
				cCondition.signal();
				lock.unlock();
			}
		}

	}

	static class C implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				lock.lock();
				while (status != 3) {
					try {
						cCondition.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName() + " - C");
				status = 1;
				aCondition.signal();
				lock.unlock();
			}
		}

	}
}