package com.action.java.m0102;

public class TwoAddAndDec {
	static int i = 1;

	public static synchronized void inc() {
		i++;
		System.out.println("Thread inc :" + Thread.currentThread().getName() + " , i = " + i);
	}

	public static synchronized void dec() {
		i--;
		System.out.println("Thread dec :" + Thread.currentThread().getName() + " , i = " + i);
	}

	class Add implements Runnable {

		@Override
		public void run() {
			TwoAddAndDec.inc();
		}

	}

	class Dec implements Runnable {

		@Override
		public void run() {
			TwoAddAndDec.dec();
		}

	}

	public static void main(String[] args) {
		TwoAddAndDec td = new TwoAddAndDec();
		Add a = td.new Add();
		Dec d = td.new Dec();
		for (int i = 0; i < 2; i++) {
			new Thread(a).start();
			new Thread(d).start();
		}
	}
}
