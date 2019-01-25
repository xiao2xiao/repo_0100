package com.po.p8.p2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 456, 1, 2, 4);
		Iterator<Integer> iterator = list.iterator();
		iterator.forEachRemaining(obj -> System.out.println("list元素的值为：" + obj));
		list.forEach(obj -> System.out.println(obj));
	}

}
