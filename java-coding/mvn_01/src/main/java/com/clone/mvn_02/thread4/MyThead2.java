package com.clone.mvn_02.thread4;

class MRunnable implements Runnable {
	private String name;

	public MRunnable() {

	}

	public MRunnable(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("继承Thread实现线程：" + name + "," + Thread.currentThread().getName());

	}

}

public class MyThead2 {
	public static void main(String[] args) {
		Thread thread = new Thread(new MRunnable("bbb"), "ttt");
		thread.start();
	}
}
