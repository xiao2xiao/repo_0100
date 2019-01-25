package com.po.p18.p5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Person {
	void walk();

	void say(String name);

	void hello(String str1, String str2);
}

class MyInvocationHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("----正在执行invoke方法----" + method);
		int index = 1;
		if (args != null) {
			System.out.println("----输出args参数的值----");
			for (Object object : args) {
				System.out.println("第 " + index + " 个的值为：" + object);
				index++;
			}
		} else {
			System.out.println("----参数args的值为空----");
		}
		return null;
	}

}

public class ProxyTest {

	public static void main(String[] args) {
		InvocationHandler handler = new MyInvocationHandler();
		Person person = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[] { Person.class },
				handler);
		person.walk();
		System.out.println("=========================================");
		person.say("how are 有");
		System.out.println("=========================================");
		person.hello("中国", "上海");
	}

}
