package com.action.java.m0102;

import java.util.Vector;

public class ArrayListMultiThread {

	static Vector<Integer> aList = new Vector<>();

	static class AddThread implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 10000000; i++) {
				aList.add(i);
			}
		}

	}

	public static void main(String[] args) throws Exception {
		Thread t1 = new Thread(new AddThread());
		Thread t2 = new Thread(new AddThread());
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(aList.size());
	}
}
