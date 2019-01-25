package com.clone.mvn_02.concurrency.m3;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
	public static void main(String[] args) {
		int x = 123;
		System.out.println();
		Map<String, Integer> map = new HashMap<>();
		map.put("q", 12);
		System.out.println(x);
		System.out.println(new HashMapTest().hashCode());
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
}
