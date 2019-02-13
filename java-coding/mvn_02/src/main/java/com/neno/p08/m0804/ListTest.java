package com.neno.p08.m0804;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ListTest {
	public static void main(String[] args) {
		fun2();
	}

	/**
	 * ListIterator有逆序功能
	 */

	static void fun2() {
		String[] books = { "疯狂Java讲义", "疯狂iOS讲义", "轻量级Java EE企业应用实战" };
		List bookList = new ArrayList();
		for (int i = 0; i < books.length; i++) {
			bookList.add(books[i]);
		}
		ListIterator iter = bookList.listIterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
			System.out.println("---------------------------");
		}
		System.out.println("=============================");
		while (iter.hasPrevious()) {
			System.out.println(iter.previous());
			System.out.println("+++++++++++++++++++++++");
		}
	}

	/**
	 * List Test
	 */
	static void fun() {
		List books = new ArrayList();
		// 向books集合中添加4个元素
		books.add(new String("轻量级Java EE企业应用实战"));
		books.add(new String("疯狂Java讲义"));
		books.add(new String("疯狂Android讲义"));
		books.add(new String("疯狂iOS讲义"));
		System.out.println(books);
		System.out.println("++++++++++++++");
		books.sort((o1, o2) -> ((String) o1).length() - ((String) o2).length());
		System.out.println(books);
		books.replaceAll(ele -> ((String) ele).length());
		System.out.println("+++++++++++++++");
		System.out.println(books);
	}
}
