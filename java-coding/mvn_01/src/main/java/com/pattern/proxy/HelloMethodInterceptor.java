package com.pattern.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 
 * @author root
 *         JDK代理要求被代理的类必须实现接口，有很强的局限性。而CGLIB动态代理则没有此类强制性要求。简单的说，CGLIB会让生成的代理类继承被代理类，并在代理类中对代理方法进行强化处理(前置处理、后置处理等)。在CGLIB底层，其实是借助了ASM这个非常强大的Java字节码生成框架。
 *
 *
 */
public class HelloMethodInterceptor implements MethodInterceptor {

	@Override
	public Object intercept(Object object, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		this.before(method.getName());
		Object result = methodProxy.invokeSuper(object, objects);
		this.after(method.getName());
		return result;
	}

	private void before(Object object) {
		System.out.println("before: " + object);
	}

	private void after(Object object) {
		System.out.println("after: " + object);
	}
}
