package com.clone.mvn_03.m2.DynaProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

	private Object target;

	public MyInvocationHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		DogUtil dogUtil = new DogUtil();
		dogUtil.method1();
		Object result = method.invoke(target, args);
		dogUtil.method2();
		return result;
	}

}
