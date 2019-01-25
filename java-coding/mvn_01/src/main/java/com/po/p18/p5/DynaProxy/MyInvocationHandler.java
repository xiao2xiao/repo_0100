package com.po.p18.p5.DynaProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
	private Object target;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		DogUtil dogUtil = new DogUtil();
		dogUtil.myInterceptor1();

		Object result = method.invoke(target, args);

		dogUtil.myInterceptor2();
		return result;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

}
