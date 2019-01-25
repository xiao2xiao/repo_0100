package com.clone.mvn_04.test;

class Parent {
	public static void p() {
		System.out.println("Parent.............");
	}
}

public class TestMain1 extends Parent {
	public static void p() {
		System.out.println("Child..............");
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		/*
		 * TestMain1 t1 = new TestMain1(); TestMain1 t2 = new TestMain1();
		 * System.out.println(t1.toString()); System.out.println(t2.toString());
		 * System.out.println(t1.equals(t2)); System.out.println(t1 == t2);
		 * 
		 * System.out.println("=============================="); String str1 = new
		 * String("123456"); String str2 = str1.intern();
		 * System.out.println(str1.equals(str2)); System.out.println(str1 == str2); int
		 * f = fun(10); System.out.println(f);
		 */
		System.out.println("==============================");
		Parent parent = new TestMain1();
		parent.p();
	}

	@SuppressWarnings("finally")
	static int fun(int i) {

		try {
			i = 1;
			return i;
		} catch (Exception e) {
			// TODO: handle exception
			return 5;
		} finally {
			// TODO: handle finally clause
			i = 15;

			System.out.println("how are you .............");
			return i;
		}
	}
}
