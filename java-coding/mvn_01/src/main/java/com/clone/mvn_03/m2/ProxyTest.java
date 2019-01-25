package com.clone.mvn_03.m2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Person {
	void walk();

	void say(String name);

	void hello(String name, String str);
}

class MyInvocationHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("正在执行的Method方法：" + method);
		if (args != null) {
			System.out.println("下面是该方法对应的实参为：");
			for (Object object : args) {
				System.out.println(object);
			}
		} else {
			System.out.println("该方法没有实参！");
		}
		return null;
	}

}

public class ProxyTest {

	public static void main(String[] args) {
		MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
		Person person = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[] { Person.class },
				myInvocationHandler);
		person.walk();
		person.say("北京");
		person.hello("上海", "杭州");
	}

}
