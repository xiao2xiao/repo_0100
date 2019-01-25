package com.po.p18.p5.DynaProxy;

public class AppTest {
	public static void main(String[] args) {
		Dog dog = (Dog) MyProxyFactory.getProxy(new GunGog());
		dog.info();
		System.out.println("========================================");
		dog.run();

	}
}
