package com.neno.p08.m0802;

import java.util.*;
import java.util.function.Predicate;

public class PredicateTest {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		// 创建一个集合
		Collection books = new HashSet();
		books.add(new String("轻量级Java EE企业应用实战"));
		books.add(new String("疯狂Java讲义"));
		books.add(new String("疯狂iOS讲义"));
		books.add(new String("疯狂Ajax讲义"));
		books.add(new String("疯狂Android讲义"));
		// removeContion(books);
		System.out.println(callAll(books, ele -> ((String) ele).contains("Java")));
		System.out.println(callAll(books, ele -> ((String) ele).contains("疯狂")));
		System.out.println(callAll(books, ele -> ((String) ele).length() > 10));
	}

	/**
	 * 使用removeif方法对集合进行过滤
	 * 
	 * @param books
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	static void removeContion(Collection books) {
		// 使用Lambda表达式（目标类型是Predicate）过滤集合
		books.removeIf(a -> ((String) a).length() < 10);
		System.out.println(books);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	static int callAll(Collection books, Predicate p) {
		int total = 0;
		for (Object object : books) {
			if (p.test(object)) {
				total++;
			}
		}
		return total;
	}
}
