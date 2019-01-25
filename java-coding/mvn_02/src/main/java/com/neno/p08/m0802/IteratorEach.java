package com.neno.p08.m0802;

import java.util.*;

public class IteratorEach {
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public static void main(String[] args) {
		// 创建集合、添加元素的代码与前一个程序相同
		Collection books = new HashSet();
		books.add("轻量级Java EE企业应用实战");
		books.add("疯狂Java讲义");
		books.add("疯狂Android讲义");

		Iterator iterator = books.iterator();
		/**
		 * java 8
		 */
//		iterator.forEachRemaining(obj -> System.out.println(obj));

		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
