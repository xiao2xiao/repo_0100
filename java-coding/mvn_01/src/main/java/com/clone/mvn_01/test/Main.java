package com.clone.mvn_01.test;

import java.util.concurrent.ConcurrentHashMap;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
		OuterClass.NestedStaticClass aClass = new OuterClass.NestedStaticClass();
		aClass.printMessage();
		OuterClass.InnerClass bClass = new OuterClass().new InnerClass();
		bClass.display();
		new Person() {

			@Override
			public void eat() {
				// TODO Auto-generated method stub
				System.out.println("我正在吃饭");
			}
		}.eat();

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

			}
		}).start();
		System.out.println("====================================================");
		System.out.println(1 + 2 + "");
		System.out.println("" + 1 + 2);
	}
}

class OuterClass {
	private static String msg = "中国";

	public static class NestedStaticClass {
		public void printMessage() {
			System.out.println("Nested Static Class：" + msg);
		}
	}

	public class InnerClass {
		public void display() {
			System.out.println("Inner Class：" + msg);
		}
	}
}

interface Person {
	public abstract void eat();
}