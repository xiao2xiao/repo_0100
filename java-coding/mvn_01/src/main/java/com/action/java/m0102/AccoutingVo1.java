package com.action.java.m0102;

public class AccoutingVo1 implements Runnable {

	static AccoutingVo1 instance = new AccoutingVo1();

	static volatile int i = 0;

	public static synchronized void increase() {
		i++;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10000000; i++) {
			increase();
		}
	}

	public static void main(String[] args) throws Exception {
		Thread t1 = new Thread(instance);
		Thread t2 = new Thread(instance);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("i = " + i);
	}
}
