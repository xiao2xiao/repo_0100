package com.po.p18.p5.DynaProxy;

import java.lang.reflect.Proxy;

public class MyProxyFactory {
	public static Object getProxy(Object target) {
		MyInvocationHandler mHandler = new MyInvocationHandler();
		mHandler.setTarget(target);
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), mHandler);
	}
}
