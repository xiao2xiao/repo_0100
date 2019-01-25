package com.neno.p18.m1801;

public class ClassLoaderTest {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		classLoader.loadClass("com.neno.p18.m1801.Tester");
		System.out.println("系统加载Tester类");
		Class.forName("com.neno.p18.m1801.Tester");
	}

}

class Tester {
	static {
		System.out.println("Tester 静态初始块。。。。。。。");
	}
}