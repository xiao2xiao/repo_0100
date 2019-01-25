package com.neno.designpattern.creational.factory.method;

public interface Car {
	void run();
}

class Byd implements Car {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("我是Byd................");
	}
}

class Bens implements Car {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("我是Bens................");
	}
}

class Bmw implements Car {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("我是Bmw................");
	}
}