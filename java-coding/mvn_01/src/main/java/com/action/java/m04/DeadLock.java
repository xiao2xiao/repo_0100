package com.action.java.m04;

public class DeadLock extends Thread {

	protected Object tool;
	static Object fork1 = new Object();
	static Object fork2 = new Object();

	public DeadLock(Object object) {
		this.tool = object;
		if (tool == fork1) {
			this.setName("哲学家A");
		}
		if (tool == fork2) {
			this.setName("哲学家B");
		}
	}

	@Override
	public void run() {
		if (tool == fork1) {
			synchronized (fork1) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (fork2) {
					System.out.println("哲学家A开始吃饭了");
				}
			}
		}
		if (tool == fork2) {
			synchronized (fork2) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (fork1) {
					System.out.println("哲学家B开始吃饭了");
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		DeadLock A = new DeadLock(fork1);
		DeadLock B = new DeadLock(fork2);
		A.start();
		B.start();
		Thread.sleep(1000);
	}
}
