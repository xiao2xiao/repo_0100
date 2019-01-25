package com.pattern.proxy;

import net.sf.cglib.proxy.Enhancer;

public class MyCreateProxyFactory {
	public static Object createProxy(Object object) {
		Enhancer enhancer = new Enhancer();
		/**
		 * 继承被代理类
		 */
		enhancer.setSuperclass(object.getClass());
		/**
		 * 设置回调
		 */
		enhancer.setCallback(new HelloMethodInterceptor());
		/**
		 * 生成代理对象
		 */
		return enhancer.create();
	}
}
