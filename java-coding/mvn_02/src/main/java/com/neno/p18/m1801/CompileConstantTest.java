package com.neno.p18.m1801;

public class CompileConstantTest {

	public static void main(String[] args) {
		//System.out.println(MyTest.compileConstant);
		System.out.println("++++++++++++++++++++++++++");
		//System.out.println(MyTest.compileConstant1);
		System.out.println("++++++++++++++++++++++++++");
		System.out.println(MyTest.compileConstant2);
	}

}

class MyTest {
	static {
		System.out.println("静态初始块。。。。。。。");
	}
	static final String compileConstant = "疯狂java讲义";
	static final String compileConstant1 = System.currentTimeMillis() + "";
	static String compileConstant2 = "疯狂spring讲义";
}