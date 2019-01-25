package com.clone.mvn_03.m2.DynaProxy;

public class ProxyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dog dog = (Dog) MyProxyFactory.getProxy(new DogImpl());
		dog.info();
		dog.run();
		dog.say();
	}

}
