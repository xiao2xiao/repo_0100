package com.neno.a;

import java.util.*;

public class LRUHashMap<K, V> extends LinkedHashMap<K, V> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final float loadFactor = 0.75f;

	private int maxCapacity = 0;

	public LRUHashMap(int maxCapacity) {
		super(maxCapacity, loadFactor, true);
		this.maxCapacity = maxCapacity;
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		/**
		 * size()在hashmap中
		 */
		return size() > (int) (maxCapacity * loadFactor);
	}

	public static void print(LRUHashMap<String, Object> lruHashMap) {
		for (Map.Entry<String, Object> map : lruHashMap.entrySet()) {
			System.out.println("key = " + map.getKey() + " , value = " + map.getValue());
		}
	}

	public static void main(String[] args) {
		LRUHashMap<String, Object> lruHashMap = new LRUHashMap<>(8);

		lruHashMap.put("a", "aa");
		lruHashMap.put("b", "bb");
		lruHashMap.put("c", "cc");
		lruHashMap.put("d", "dd");
		lruHashMap.put("e", "ee");
		lruHashMap.put("f", "ff");

		print(lruHashMap);

		System.out.println("--------------------------");

		System.out.println(lruHashMap.get("d") + " , " + lruHashMap.get("b"));

		print(lruHashMap);

		System.out.println("--------------------------");

		lruHashMap.put("m", "mm");
		lruHashMap.put("n", "nn");
		lruHashMap.put("h", "hh");
		print(lruHashMap);
	}
}
