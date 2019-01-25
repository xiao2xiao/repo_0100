package com.clone.executor;

public class CASCount implements Runnable {
	private SimilatedCAS counter = new SimilatedCAS();

	private int increment() {
		int oldValue = counter.getValue();
		int newValue = oldValue + 1;
		while (!counter.compareAndSwap(oldValue, newValue)) {
			oldValue = counter.getValue();
			newValue = oldValue + 1;
		}
		return newValue;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(this.increment());
		}

	}

	public static void main(String[] args) {
		Runnable target = new CASCount();
		new Thread(target).start();
		new Thread(target).start();
		new Thread(target).start();
	}
}

class SimilatedCAS {
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public synchronized boolean compareAndSwap(int expectedValue, int newValue) {
		if (value == expectedValue) {
			value = newValue;
			return true;
		}
		return false;
	}
}