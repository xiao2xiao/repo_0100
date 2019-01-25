package com.clone.mvn_03.m1;

class MyTest {
	static {
		System.out.println("初始化MyTest类！");
	}
	public static final String compileConstant = "java讲义";

	public static final String compileConstant1 = System.currentTimeMillis() + "";
}

public class CompileConstantTest {
	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println(MyTest.compileConstant);
		ClassLoader classLoaders = ClassLoader.getSystemClassLoader();
		System.out.println(classLoaders);

		classLoaders.loadClass("com.clone.mvn_03.m1.MyTest");
		System.out.println("=====================");
		Class.forName("com.clone.mvn_03.m1.MyTest");
	}
}
