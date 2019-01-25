package com.pattern.proxy;

public class Client {
	public static void main(String[] args) {
		HelloService helloService = (HelloService) MyCreateProxyFactory.createProxy(new HelloService());
		helloService.say();
		helloService.sing();
	}
}
