package com.clone.mvn_02.thread4;

class MThead extends Thread {
	private String name;

	public MThead() {
		// TODO Auto-generated constructor stub
	}

	public MThead(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("继承Thread实现线程：" + name + "," + Thread.currentThread().getName());
	}
}

public class MyThread {
	public static void main(String[] args) {
		MThead mThead = new MThead("aaa");
		mThead.start();
	}
}
