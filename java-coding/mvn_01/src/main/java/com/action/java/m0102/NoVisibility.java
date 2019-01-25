package com.action.java.m0102;

public class NoVisibility {

	private static volatile boolean ready = false;
	private static int number;

	private static class ReaderThread extends Thread {
		@Override
		public void run() {
			while (!ready) {
			}
			System.out.println("number = " + number);
		}
	}

	public static void main(String[] args) throws Exception {
		new ReaderThread().start();
		Thread.sleep(1000);
		ready = true;
		number = 50;
		Thread.sleep(10000);
	}
}
