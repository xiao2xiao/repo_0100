package com.po.p18.p3;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

public class MethodParameterTest {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Class<Tester> clazz = Tester.class;
		Method replace = clazz.getMethod("replace", String.class, List.class);
		System.out.println("relapce方法参数个数：" + replace.getParameterCount());
		Parameter[] params = replace.getParameters();
		int index = 1;
		for (Parameter parameter : params) {
			if (parameter.isNamePresent()) {

				System.out.println("---第" + index + "个参数信息---");
				System.out.println("参数名：" + parameter.getName());
				System.out.println("形参类型：" + parameter.getType());
				System.out.println("泛型类型：" + parameter.getParameterizedType());
				index++;
			}
		}

	}

}

class Tester {
	public void replace(String str, List<String> list) {

	}
}