package com.neno.p08.m0803;

import java.util.*;

public class LinkedHashSetTest {
	public static void main(String[] args) {
		LinkedHashSet<String> books = new LinkedHashSet<>();
		books.add("疯狂Java讲义");
		books.add("轻量级Java EE企业应用实战");
		System.out.println(books);
		// 删除 疯狂Java讲义
		books.remove("疯狂Java讲义");
		// 重新添加 疯狂Java讲义
		books.add("疯狂Java讲义");
		System.out.println(books);
	}
}
