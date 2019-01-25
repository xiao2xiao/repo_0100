package com.clone.sort.valuereference;

public class ValueReferenceDemo {

	public static void main(String[] args) {

		// ----------------------demo1---------------------------//
		String str = new String("hello");
		char[] chs = { 'w', 'o', 'r', 'l', 'd' };
		change(str, chs);
		System.out.println(str + " " + new String(chs));

		// ----------------------demo2---------------------------//
		StringBuffer sb = new StringBuffer("hello");
		change(sb);
		System.out.println(sb);

		// ----------------------demo3---------------------------//
		StringBuffer a = new StringBuffer("A");
		StringBuffer b = new StringBuffer("B");
		operate(a, b);
		System.out.println(a + "." + b);

	}

	/**
	 * demo1
	 * 
	 * @param str
	 * @param chs
	 */
	public static void change(String str, char[] chs) {
		/**
		 * 由于String是不可变对象，所以修改String的值相当于重新创建一个对象
		 */
		str.replace('h', 'H');
		chs[0] = 'W';
	}

	/**
	 * demo2
	 * 
	 * @param sb
	 */
	public static void change(StringBuffer sb) {
		sb.append(" world");
		// sb.deleteCharAt(0);
	}

	/**
	 * demo3
	 * 
	 * @param x
	 * @param y
	 */
	public static void operate(StringBuffer x, StringBuffer y) {
		x.append(y);
		y = x;
	}
}