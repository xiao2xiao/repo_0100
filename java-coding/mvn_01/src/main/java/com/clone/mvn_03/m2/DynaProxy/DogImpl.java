package com.clone.mvn_03.m2.DynaProxy;

public class DogImpl implements Dog {

	@Override
	public void info() {
		// TODO Auto-generated method stub
		System.out.println("我是一只猎狗！");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("run............");
	}

	@Override
	public void say() {
		// TODO Auto-generated method stub
		System.out.println("汪汪的叫。。。。。。");
	}

}
