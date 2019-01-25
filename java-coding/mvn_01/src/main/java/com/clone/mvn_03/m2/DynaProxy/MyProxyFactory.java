package com.clone.mvn_03.m2.DynaProxy;

import java.lang.reflect.Proxy;

public class MyProxyFactory {
	public static Object getProxy(Object target) {
		MyInvocationHandler myInvocationHandler = new MyInvocationHandler(target);
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
				myInvocationHandler);
	}
}
